import java.util.Scanner;

/**
 * Command Line Interface for interacting with the inventory.
 */
public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Find Product by ID");
            System.out.println("4. Display Products by Category");
            System.out.println("5. Display All Products");
            System.out.println("6. Sort Products by Name");
            System.out.println("7. Sort Products by Price");
            System.out.println("8. Display Low Stock Products");
            System.out.println("9. Filter Products");
            System.out.println("10. Increase Prices by Percentage");
            System.out.println("11. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Product Category: ");
                    String category = scanner.nextLine();
                    System.out.println("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    System.out.println("Enter Product Quantity: ");
                    int quantity = scanner.nextInt();
                    Product product = new Product(id, name, category, price, quantity);
                    inventory.addProduct(product);
                    break;

                case 2:
                    System.out.println("Enter Product ID to Remove: ");
                    int removeId = scanner.nextInt();
                    boolean removed = inventory.removeProduct(removeId);
                    System.out.println("Product Removed: " + removed);
                    break;

                case 3:
                    System.out.println("Enter Product ID to Find: ");
                    int findId = scanner.nextInt();
                    Product foundProduct = inventory.findProductById(findId);
                    System.out.println("Found Product: " + foundProduct);
                    break;

                case 4:
                    System.out.println("Enter Product Category: ");
                    String findCategory = scanner.nextLine();
                    System.out.println("Products in Category: " + inventory.findProductsByCategory(findCategory));
                    break;

                case 5:
                    System.out.println("All Products: " + inventory.getAllProducts());
                    break;

                case 6:
                    inventory.sortProductsByName();
                    System.out.println("Products Sorted by Name: " + inventory.getAllProducts());
                    break;

                case 7:
                    inventory.sortProductsByPrice();
                    System.out.println("Products Sorted by Price: " + inventory.getAllProducts());
                    break;

                case 8:
                    System.out.println("Enter Low Stock Threshold: ");
                    int threshold = scanner.nextInt();
                    System.out.println("Low Stock Products: " + inventory.getLowStockProducts(threshold));
                    break;

                case 9:
                    // Custom filter example: products with price > 100
                    System.out
                            .println("Products with price > 100: " + inventory.filterProducts(p -> p.getPrice() > 100));
                    break;

                case 10:
                    System.out.println("Enter Percentage to Increase Prices: ");
                    double percentage = scanner.nextDouble();
                    inventory.applyToProducts(p -> p.setPrice(p.getPrice() * (1 + percentage / 100)));
                    System.out.println("Prices Increased.");
                    break;

                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid Choice.");
                    break;
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }
}
