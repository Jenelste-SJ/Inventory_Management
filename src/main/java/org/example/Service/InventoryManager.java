package org.example.Service;
import org.example.Model.Product;
import java.util.*;
import org.example.Exceptions.*;
public class InventoryManager {
    private Map<Integer, Product> productMap = new HashMap<>();
    private List<Product> productList = new ArrayList<>();

    // 1. Add product
    public void addProduct(Product product) throws DuplicateProductException {
        if (productMap.containsKey(product.getId())) {
            throw new DuplicateProductException("Product with ID " + product.getId() + " already exists!");
        }
        productMap.put(product.getId(), product);
        productList.add(product);
        System.out.println(" Product added: " + product.getName());
    }

    // 2. View all products
    public void viewAllProducts() {
        if (productList.isEmpty()) {
            System.out.println(" No products in inventory.");
        } else {
            System.out.println("\n Inventory:");
            for (Product p : productList) {
                System.out.println(p);
            }
        }
    }

    // 3. Search product by ID
    public Product searchProductById(int id) throws ProductNotFoundException {
        Product p = productMap.get(id);
        if (p == null) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        return p;
    }

    // 4. Remove product
    public void removeProduct(int id) throws ProductNotFoundException {
        Product removed = productMap.remove(id);
        if (removed == null) {
            throw new ProductNotFoundException("Cannot remove. No product with ID: " + id);
        }
        productList.remove(removed);
        System.out.println(" Product removed: " + removed.getName());
    }
}
