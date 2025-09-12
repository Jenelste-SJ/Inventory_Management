package org.example;
import  org.example.Model.Product;
import org.example.Service.*;
import org.example.Exceptions.*;
import java.util.*;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();

        while (true) {
            System.out.println("\n==== INVENTORY MENU ====");
            System.out.println("1. Add a product");
            System.out.println("2. View all products");
            System.out.println("3. Search product by ID");
            System.out.println("4. Remove product by ID");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        inventory.addProduct(new Product(id, name, price, category, qty));
                        break;
                    case 2:
                        inventory.viewAllProducts();
                        break;
                    case 3:
                        System.out.print("Enter ID to search: ");
                        int searchId = sc.nextInt();
                        Product found = inventory.searchProductById(searchId);
                        System.out.println(" Found: " + found);
                        break;
                    case 4:
                        System.out.print("Enter ID to remove: ");
                        int removeId = sc.nextInt();
                        inventory.removeProduct(removeId);
                        break;
                    case 5:
                        System.out.println("Exiting... Bye!");
                        sc.close();
                        sc.nextLine();
                        return;
                    default:
                        System.out.println(" Invalid choice.");
                }
            } catch (DuplicateProductException | ProductNotFoundException e) {
                System.out.println("!" + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please try again.");
                sc.nextLine();
            }
        }
    }
}
