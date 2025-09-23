package org.example.service;

import org.example.dao.ProductDAO;
import org.example.exception.DatabaseException;
import org.example.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.util.CSVHelp;

import java.util.List;

public class Inventory {
    private final ProductDAO dao = new ProductDAO();

    public void addProduct(Product p) {
        try {
            dao.addProduct(p);
            System.out.println("✅ Product added successfully!");
        } catch (DatabaseException e) {
            System.err.println("❌ Failed to add product. Reason: " + e.getMessage());
        }
        }


    public void viewAllProducts() {
        try {
            List<Product> products = dao.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("⚠ No products available.");
            } else {
                System.out.println("---- Product List ----");
                products.forEach(System.out::println);
            }
        } catch (DatabaseException e) {
            System.err.println("❌ Failed to fetch products. Reason: " + e.getMessage());
        }
    }

    public void searchProductById(int id) {
        try {
            Product p = dao.getProductById(id);
            if (p != null) {
                System.out.println("Product Found: " + p);
            } else {
                System.out.println("⚠ Product not found!");
            }
        } catch (ProductNotFoundException e) {
            System.err.println("⚠ " + e.getMessage());
        } catch (DatabaseException e) {
            System.err.println("❌ Error searching product. Reason: " + e.getMessage());
        }
    }

    public void removeProduct(int id) {
        try {
            boolean deleted = dao.deleteProduct(id);
            if (deleted) {
                System.out.println(" Product removed successfully!");
            } else {
                System.out.println("No product found with that ID!");
            }
        } catch (ProductNotFoundException e) {
            System.err.println("⚠ " + e.getMessage());
        } catch (DatabaseException e) {
            System.err.println("❌ Failed to remove product. Reason: " + e.getMessage());
        }
    }
    public static void exportInventoryToCSV(String filePath) {
        CSVHelp csv = new CSVHelp();
        try {
            csv.exportProductsToCSV(filePath);
        } catch (Exception e) {
            System.out.println("⚠️ Could not export products: " + e.getMessage());
        }
    }


}
