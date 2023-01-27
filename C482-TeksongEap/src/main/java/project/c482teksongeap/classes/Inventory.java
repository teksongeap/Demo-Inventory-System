package project.c482teksongeap.classes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for inventory
 *
 * @author Teksong Eap
 */
public class Inventory {
    /**
     * ObservableList of all products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * ObservableList of all parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * unique part ID
     */
    private static int partID = 0;

    /**
     * unique product ID
     */
    private static int productID = 0;

    /**
     * Generates a new part ID.
     *
     * @return A unique part ID.
     */
    public static int getNewPartId() {
        return ++partID;
    }

    /**
     * Generates a new product ID.
     *
     * @return A unique product ID.
     */
    public static int getNewProductId() {
        return ++productID;
    }

    /**
     * add part to AllParts list
     *
     * @param newPart to add to allParts list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * delete part from AllParts list
     *
     * @param selectedPart to delete
     * @return true if selected part was removed
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * update part in AllParts list
     *
     * @param index of part
     * @param selectedPart to update
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * add product to AllProducts list
     *
     * @param newProduct to add to allProducts list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * delete product from AllProducts list
     *
     * @param selectedProduct to delete
     * @return true if selected product was removed
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * update product in AllProducts list
     *
     * @param index of product
     * @param selectedProduct to update
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * look up part by id in AllParts list
     *
     * @param partID to lookup
     * @return part associated with part ID
     */
    public static Part lookUpPart(int partID) {
        return allParts.get(partID);
    }

    /**
     * look up product by id in AllProducts list
     *
     * @param productID to lookup
     * @return product associated with product ID
     */
    public static Product lookUpProduct(int productID) {
        return allProducts.get(productID);
    }

    /**
     * look up part name in AllParts list
     *
     * @param partName to lookup inside allParts list
     * @return partsFound
     */
    public static ObservableList<Part> lookUpPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                partsFound.add(part);
            }
        }

        return partsFound;
    }

    /**
     * look up product name in AllProducts list 
     *
     * @param productName to lookup inside allProducts list
     * @return productsFound
     */
    public static ObservableList<Product> lookUpProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                productsFound.add(product);
            }
        }

        return productsFound;
    }

    /**
     * gets all the parts
     * @return allParts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * gets all the products
     * @return allProducts list
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
