package org.example.Model;

public class Product {
    private int id;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private double price;
    public Product(int id, String name, double price,  String category, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;

    }
    public String getCategory() {
        return category;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String toString() {
        return "Product [id=" + id + ", name=" + name+ ", category=" + category + ", quantity=" + quantity + ", price=" + price + "]";
    }

}
