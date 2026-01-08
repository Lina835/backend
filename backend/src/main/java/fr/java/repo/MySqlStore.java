package fr.java.repo;

import fr.java.model.Category;
import fr.java.model.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlStore {

    // ---------- CATEGORIES ----------
    public List<Category> getCategories() {
        String sql = "SELECT id, name FROM category ORDER BY id";
        List<Category> out = new ArrayList<>();

        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category cat = new Category();
                cat.id = rs.getInt("id");
                cat.name = rs.getString("name");
                out.add(cat);
            }
            return out;

        } catch (SQLException e) {
            throw new RuntimeException("DB getCategories error: " + e.getMessage(), e);
        }
    }

    // ---------- DISHES par catégorie ----------
    public List<Dish> getDishesByCategory(int categoryId) {
        String sql = """
                SELECT id, category_id, name, description, price, available, icon
                FROM dish
                WHERE category_id = ?
                ORDER BY id
                """;

        List<Dish> out = new ArrayList<>();

        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Dish d = new Dish();
                    d.id = rs.getInt("id");
                    d.categoryId = rs.getInt("category_id");
                    d.name = rs.getString("name");
                    d.description = rs.getString("description");
                    d.price = rs.getDouble("price");
                    d.available = rs.getBoolean("available");
                    d.icon = rs.getString("icon");
                    out.add(d);
                }
            }
            return out;

        } catch (SQLException e) {
            throw new RuntimeException("DB getDishesByCategory error: " + e.getMessage(), e);
        }
    }

    // Pour calculer les totaux et vérifier dishId
    public Dish getDishById(int dishId) {
        String sql = """
                SELECT id, category_id, name, description, price, available, icon
                FROM dish
                WHERE id = ?
                """;

        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, dishId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Dish d = new Dish();
                d.id = rs.getInt("id");
                d.categoryId = rs.getInt("category_id");
                d.name = rs.getString("name");
                d.description = rs.getString("description");
                d.price = rs.getDouble("price");
                d.available = rs.getBoolean("available");
                d.icon = rs.getString("icon");
                return d;
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB getDishById error: " + e.getMessage(), e);
        }
    }

    // ---------- CREATION COMMANDE ----------
    public int createOrder(String customerRef, List<OrderLine> lines) {
        String insertOrder = "INSERT INTO orders(customer_ref, total, created_at) VALUES (?, ?, ?)";
        String insertItem  = "INSERT INTO order_item(order_id, dish_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        String insertOpt   = "INSERT INTO order_item_option(order_item_id, name, value) VALUES (?, ?, ?)";

        try (Connection c = Db.getConnection()) {
            c.setAutoCommit(false);

            // total
            double total = 0.0;
            for (OrderLine l : lines) total += l.unitPrice * l.quantity;

            int orderId;
            try (PreparedStatement ps = c.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, customerRef);
                ps.setDouble(2, total);
                ps.setTimestamp(3, new Timestamp(new Date().getTime()));
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) throw new RuntimeException("No generated key for orders");
                    orderId = keys.getInt(1);
                }
            }

            // produit (item)
            for (OrderLine l : lines) {
                int orderItemId;
                try (PreparedStatement ps = c.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, l.dishId);
                    ps.setInt(3, l.quantity);
                    ps.setDouble(4, l.unitPrice);
                    ps.executeUpdate();

                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (!keys.next()) throw new RuntimeException("No generated key for order_item");
                        orderItemId = keys.getInt(1);
                    }
                }

                // options 
                for (Option o : l.options) {
                    try (PreparedStatement ps = c.prepareStatement(insertOpt)) {
                        ps.setInt(1, orderItemId);
                        ps.setString(2, o.name);
                        ps.setString(3, o.value);
                        ps.executeUpdate();
                    }
                }
            }

            c.commit();
            return orderId;

        } catch (Exception e) {
            throw new RuntimeException("DB createOrder error: " + e.getMessage(), e);
        }
    }

    public static class OrderLine {
        public int dishId;
        public int quantity;
        public double unitPrice;
        public List<Option> options = new ArrayList<>();
    }

    public static class Option {
        public String name;
        public String value;

        public Option(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
