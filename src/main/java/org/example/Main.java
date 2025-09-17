package org.example;

import org.example.model.Product;
import org.example.service.Inventory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory service = new Inventory();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Remove Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int id = 0;
                    String name = null;
                    double price = 0;
                    String category = null;
                    int qty = 0;
                    try {
                        System.out.println("Enter Product ID");
                        id = sc.nextInt();
                        System.out.print("Enter name: ");
                        name = sc.next();
                        System.out.print("Enter price: ");
                        price = sc.nextDouble();
                        System.out.print("Enter category: ");
                        category = sc.next();
                        System.out.print("Enter quantity: ");
                        qty = sc.nextInt();
                    } catch (Exception e) {
                        throw new InputMismatchException("Invalid input");
                    }

                    Product p = new Product(id, name, price, category, qty);
                    service.addProduct(p);
                    break;

                case 2:
                    service.viewAllProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int sid = sc.nextInt();
                    service.searchProductById(sid);
                    break;

                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    int rid = sc.nextInt();
                    service.removeProduct(rid);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
