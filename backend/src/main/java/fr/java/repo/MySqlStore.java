package fr.java.repo;

import fr.java.dto.CreateOrderRequest;
import fr.java.model.Category;
import fr.java.model.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlStore {

    // --- CATEGORIES ---
    public List<Category> getCategories() {
        String sql = "SELECT id, name FROM categories ORDER BY id";
        List<Category> out = new ArrayList<>();
        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            return out;
        } catch (SQLException e) {
            throw new RuntimeException("DB error getCategories", e);
        }
    }

    // --- DISHES ---
    public List<Dish> getDishes(Integer categoryId) {
        String sql = (categoryId == null)
                ? "SELECT id, category_id, name, description, price, available, icon FROM dishes ORDER BY id"
                : "SELECT id, category_id, name, description, price, available, icon FROM dishes WHERE category_id=? ORDER BY id";
        List<Dish> out = new ArrayList<>();
        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (categoryId != null) ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Dish(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"),
                            rs.getString("description"), rs.getDouble("price"), rs.getBoolean("available"), rs.getString("icon")));
                }
            }
            return out;
        } catch (SQLException e) {
            throw new RuntimeException("DB error getDishes", e);
        }
    }

    // --- CREATE ORDER (LA PARTIE QUE TU AS EFFACÉE) ---
    public int createOrder(CreateOrderRequest req) {
        System.out.println("Début création commande pour : " + req.customerRef);
        
        if (req.items == null || req.items.isEmpty()) {
            System.err.println("ERREUR : La liste des produits est vide !");
            return -1;
        }

        int orderId = -1;
        String sqlOrder = "INSERT INTO orders (customer_ref, status, total, created_at) VALUES (?, 'PENDING', 0, NOW())";
        String sqlItem = "INSERT INTO order_items (order_id, dish_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        String sqlUpdateTotal = "UPDATE orders SET total = ? WHERE id = ?";

        try (Connection con = Db.getConnection()) {
            con.setAutoCommit(false); // Sécurité transaction

            // 1. Création de la commande
            try (PreparedStatement psOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setString(1, req.customerRef);
                psOrder.executeUpdate();
                ResultSet rs = psOrder.getGeneratedKeys();
                if (rs.next()) orderId = rs.getInt(1);
            }

            // 2. Insertion des produits et calcul du prix total
            double totalFinal = 0;
            try (PreparedStatement psItem = con.prepareStatement(sqlItem)) {
                for (CreateOrderRequest.Item item : req.items) {
                    double price = getDishPrice(con, item.dishId);
                    psItem.setInt(1, orderId);
                    psItem.setInt(2, item.dishId);
                    psItem.setInt(3, item.quantity);
                    psItem.setDouble(4, price);
                    psItem.addBatch();
                    totalFinal += (price * item.quantity);
                }
                psItem.executeBatch();
            }

            // 3. Mise à jour du total final
            try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdateTotal)) {
                psUpdate.setDouble(1, totalFinal);
                psUpdate.setInt(2, orderId);
                psUpdate.executeUpdate();
            }

            con.commit(); // On valide tout
            System.out.println("Commande réussie ! ID : " + orderId);
            return orderId;

        } catch (SQLException e) {
            System.err.println("ERREUR SQL : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private double getDishPrice(Connection con, int dishId) throws SQLException {
        String sql = "SELECT price FROM dishes WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dishId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("price");
            }
        }
        return 0;
    }
}