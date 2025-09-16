package org.example.Service;

import org.example.dao.ProductDAO;
import org.example.Model.Product;
import java.util.List;

public class Inventory {
    private final ProductDAO dao = new ProductDAO();

    public void addProduct(Product p) {
        try {
            dao.addProduct(p);
            System.out.println("✅ Product added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
