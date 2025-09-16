package org.example.dao;

import org.example.Model.Product;
import org.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Add product
    public void addProduct(Product p) throws Exception {
        String sql = "INSERT INTO products (id,name, price, category, quantity) VALUES (?,?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setDouble(3, p.getPrice());
            stmt.setString(4, p.getCategory());
            stmt.setInt(5, p.getQuantity());
            stmt.executeUpdate();
        }
    }

    // Get all products
    public List<Product> getAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getInt("quantity")
                );
                list.add(p);
            }
        }
        return list;
    }

    // Search by ID
    public Product getProductById(int id) throws Exception {
        String sql = "SELECT * FROM products WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getInt("quantity")
                );
            }
        }
        return null;
    }

    // Delete by ID
    public boolean deleteProduct(int id) throws Exception {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
