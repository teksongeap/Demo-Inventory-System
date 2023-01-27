package project.c482teksongeap.classes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for a product
 * @author Teksong Eap
 */
public class Product {

    /**
     * Product ID
     */
    private int id;

    /**
     * Product name
     */
    private String name;

    /**
     * Product price
     */
    private double price;

    /**
     * Product stock
     */
    private int stock;

    /**
     * Product min
     */
    private int min;

    /**
     * Product max
     */
    private int max;
    /**
     * Parts associated with product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Constructor for product
     *
     * @param id product ID
     * @param name product name
     * @param price product price
     * @param stock product stock
     * @param min product min
     * @param max product max
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds part to list of associated parts
     * @param part part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * @param part to remove
     * @return true if part was in list
     */
    public boolean deleteAssociatedPart(Part part) {
        return associatedParts.remove(part);
    }

    /**
     * @return associatedParts of the product
     */
    public ObservableList<Part> getAllAssociatedParts() {return associatedParts;}

}



