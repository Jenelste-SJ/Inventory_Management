package org.example.dao;

import org.example.model.Product;
import org.example.util.DBConnection;
import org.example.exception.*;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Add product
    public void addProduct(Product p) throws DatabaseException {
        String sql = "INSERT INTO inventory (id, name, category, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getCategory());
            stmt.setInt(4, p.getQuantity());
            stmt.setDouble(5, p.getPrice());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error while adding product", e);
        }
    }

    // Get all products
    public List<Product> getAllProducts() throws DatabaseException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
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
        } catch (Exception e) {
            throw new DatabaseException("Error while fetching all products", e);
        }
        return list;
    }


    // Search by ID
    public Product getProductById(int id) throws DatabaseException, ProductNotFoundException {
        String sql = "SELECT * FROM inventory WHERE id=?";
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
            } else {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while searching product by ID", e);
        }
    }

    // Delete by ID
    public boolean deleteProduct(int id) throws DatabaseException, ProductNotFoundException {
        String sql = "DELETE FROM inventory WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting product", e);
        }
        return false;
    }


    public void exportProductsToCSV(String filePath) throws Exception {
        List<Product> products = getAllProducts();

        // Wrap PrintWriter around FileWriter
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Header row
            writer.println("id,name,category,quantity,price");

            // Data rows
            for (Product p : products) {
                writer.println(
                        p.getId() + "," +
                                p.getName() + ","  +
                                p.getCategory() + "," +
                                p.getQuantity()+"," +
                                p.getPrice()
                );
            }
            System.out.println("✅ Products exported to " + filePath);
        }
    }
}
