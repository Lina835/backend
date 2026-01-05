package fr.java.repo;

import fr.java.model.Category;
import fr.java.model.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlStore {

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
                    out.add(new Dish(
                            rs.getInt("id"),
                            rs.getInt("category_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getBoolean("available"),
                            rs.getString("icon")
                    ));
                }
            }
            return out;

        } catch (SQLException e) {
            throw new RuntimeException("DB error getDishes", e);
        }
    }
}
