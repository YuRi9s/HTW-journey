import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Manages a collection of products in the inventory.
 */
public class Inventory {
    private Map<Integer, Product> productMap;

    /**
     * Constructs a new Inventory.
     */
    public Inventory() {
        productMap = new HashMap<>();
    }

    /**
     * Adds a product to the inventory.
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        productMap.put(product.getProductId(), product);
    }

    /**
     * Removes a product from the inventory by its ID.
     *
     * @param productId the ID of the product to remove
     * @return true if the product was removed, false otherwise
     */
    public boolean removeProduct(int productId) {
        return productMap.remove(productId) != null;
    }

    /**
     * Finds a product in the inventory by its ID.
     *
     * @param productId the ID of the product to find
     * @return the product if found, null otherwise
     */
    public Product findProductById(int productId) {
        return productMap.get(productId);
    }

    /**
     * Finds products in the inventory by category.
     *
     * @param category the category to search for
     * @return a list of products in the specified category
     */
    public List<Product> findProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : productMap.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Gets all products in the inventory.
     *
     * @return a list of all products
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    /**
     * Sorts the products in the inventory by name.
     */
    public void sortProductsByName() {
        List<Product> products = getAllProducts();
        products.sort(Comparator.comparing(Product::getName));
    }

    /**
     * Sorts the products in the inventory by price.
     */
    public void sortProductsByPrice() {
        List<Product> products = getAllProducts();
        products.sort(Comparator.comparingDouble(Product::getPrice));
    }

    /**
     * Gets products with quantity below a specified threshold.
     *
     * @param threshold the quantity threshold
     * @return a list of products with low stock
     */
    public List<Product> getLowStockProducts(int threshold) {
        List<Product> result = new ArrayList<>();
        for (Product product : productMap.values()) {
            if (product.getQuantity() < threshold) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Filters products based on a given predicate.
     *
     * @param predicate the condition to filter products
     * @return a list of products that match the predicate
     */
    public List<Product> filterProducts(Predicate<Product> predicate) {
        List<Product> result = new ArrayList<>();
        for (Product product : productMap.values()) {
            if (predicate.test(product)) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Applies a given operation to all products in the inventory.
     *
     * @param consumer the operation to apply to each product
     */
    public void applyToProducts(Consumer<Product> consumer) {
        for (Product product : productMap.values()) {
            consumer.accept(product);
        }
    }
}
