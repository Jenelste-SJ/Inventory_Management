package org.example.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private int quantity;

    public Product(int id, String name, double price, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Id="+id + " | " +"Name="+ name + " | "   +"Category="+ category + " | " +"Quantity="+ quantity+ " | "+"Price="+ price;
    }
}
