package org.example.util;

import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class CSVHelp {
    public void exportProductsToCSV(String filePath) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();

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
