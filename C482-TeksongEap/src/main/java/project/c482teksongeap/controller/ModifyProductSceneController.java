package project.c482teksongeap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import project.c482teksongeap.Main;
import project.c482teksongeap.classes.Inventory;
import project.c482teksongeap.classes.Part;
import project.c482teksongeap.classes.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controller for modifying product scene.
 *
 * A future modification could be the changing of associated parts to match when they are modified through
 * the modify part scene. At the moment, the associated parts do not match when the original part
 * is modified.
 *
 * @author Teksong Eap
 */
public class ModifyProductSceneController implements Initializable {
    /**
     * list containing associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();



    //----------------------------TABLES AND SEARCH---------------------------------
    /**
     * variable referring to parts table
     */
    @FXML
    private TableView<Part> partTable;

    /**
     * variable referring to part ID column
     */
    @FXML
    private TableColumn<Part, Integer> partID;

    /**
     * variable referring to part name column
     */
    @FXML
    private TableColumn<Part, String> partName;

    /**
     * variable referring to part inventory amount column
     */
    @FXML
    private TableColumn<Part, Integer> partInventory;

    /**
     * variable referring to part price column
     */
    @FXML
    private TableColumn<Part, Double> partPrice;

    /**
     * variable referring to parts table
     */
    @FXML
    private TableView<Part> associatedPartTable;

    /**
     * variable referring to part ID column
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartID;

    /**
     * variable referring to part name column
     */
    @FXML
    private TableColumn<Part, String> associatedPartName;

    /**
     * variable referring to part inventory amount column
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInventory;

    /**
     * variable referring to part price column
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPrice;

    /**
     * text field for part search
     */
    @FXML
    private TextField partSearchField;

    /**
     * Searches for part according to ID or full name. Checks if it actually exists, then shows on to table.
     *
     * @param event search button pressed
     * @throws IOException
     */
    @FXML
    private void partSearchButton(ActionEvent event) throws IOException {
        String textField = partSearchField.getText();
        ObservableList<Part> foundPart = FXCollections.observableArrayList();
        if (MainController.isInt(textField)) {
            int index = Integer.parseInt(textField);
            System.out.print(index);
            if (Inventory.getAllParts().size() >= index) {
                //The Part ID starts at 1, but in the AllParts list of Inventory the index starts zero.
                foundPart.add(Inventory.lookUpPart(index - 1));
            }
        } else {
            foundPart = Inventory.lookUpPart(textField);
        }
        if (foundPart.size() == 0) {
            MainController.superAlert("inform", "ERROR", "Part not found!", "Please try again.");
        } else {
            partTable.setItems(foundPart);
        }
    }

    /**
     * Checks to see if part search field is empty, then repopulates table
     * @param event part search field currently selected
     */
    @FXML
    private void partSearchFieldEmpty(KeyEvent event) {
        if (partSearchField.getText().isEmpty()) {
            partTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * checks if part is selected, then adds the part to associatedParts, updates the table, also removes
     * the part from the part table and removes it from unassociatedParts.
     * @param event add part button pressed
     * @throws IOException
     */
    @FXML
    private void addPartButton(ActionEvent event) throws IOException {
        if (partTable.getSelectionModel().getSelectedItem() == null){
            MainController.superAlert("inform", "ERROR", "Part not selected!", "Please select part.");
        } else {
            associatedParts.add(partTable.getSelectionModel().getSelectedItem());
            associatedPartTable.setItems(associatedParts);
        }
    }

    /**
     * checks if part is selected, then removes the part from associatedParts, while also adding it to unassociatedParts.
     * @param event remove part button pressed
     * @throws IOException
     */
    @FXML
    private void removePartButton(ActionEvent event) throws IOException {
        if (associatedPartTable.getSelectionModel().getSelectedItem() == null){
            MainController.superAlert("inform", "ERROR", "Part not selected!", "Please select part.");
        } else {
            if (MainController.superAlert("confirm", "Remove Part", "Part selected.", "You sure you want to remove this part?")) {
                associatedParts.remove(associatedPartTable.getSelectionModel().getSelectedItem());
                associatedPartTable.setItems(associatedParts);
            }
        }
    }


    //----------------------------------TEXT FIELDS---------------------------------
    /**
     * text field for product ID
     */
    @FXML
    private TextField productIDField;

    /**
     * text field for product name
     */
    @FXML
    private TextField productNameField;

    /**
     * text field for stock
     */
    @FXML
    private TextField productInventoryField;

    /**
     * text field for product price
     */
    @FXML
    private TextField productPriceField;

    /**
     * text field for product min
     */
    @FXML
    private TextField productMinField;

    /**
     * text field for product max
     */
    @FXML
    private TextField productMaxField;

    /**
     * Product to modify that is accessed in MainController
     */
    public static Product productModified;

    /**
     * Elegantly uses IF statements to check if data was properly and fully entered into the text fields,
     * notifies the user if anything is not right. If everything is correct, a Product is made and added
     * to the inventory. Returns to main scene if everything is successful.
     * @param event part save button pressed
     * @throws IOException
     */
    @FXML
    private void productSaveButton(ActionEvent event) throws IOException {
        boolean productAdded = false;
        String productName = productNameField.getText();
        if (productName.isEmpty()) {
            MainController.superAlert("inform", "ERROR", "product name empty!", "Please try again.");
            return;
        }
        String productInventoryString = productInventoryField.getText();
        if (productInventoryString.isEmpty() || !MainController.isInt(productInventoryString)) {
            MainController.superAlert("inform", "ERROR", "Inventory is empty or was entered incorrectly!", "Please enter an integer.");
            return;
        }
        String productPriceString = productPriceField.getText();
        if (productPriceString.isEmpty() || !MainController.isDouble(productPriceString)) {
            MainController.superAlert("inform", "ERROR", "Price is empty or was entered incorrectly!", "Please enter a double.");
            return;
        }
        String productMinString = productMinField.getText();
        if (productMinString.isEmpty() || !MainController.isInt(productMinString)) {
            MainController.superAlert("inform", "ERROR", "Min is empty or was entered incorrectly!", "Please enter an integer.");
            return;
        }
        String productMaxString = productMaxField.getText();
        if (productMaxString.isEmpty() || !MainController.isInt(productMaxString)) {
            MainController.superAlert("inform", "ERROR", "Max is empty or was entered incorrectly!", "Please enter an integer.");
            return;
        }
        int productInventory = Integer.parseInt(productInventoryString);
        double productPrice = Double.parseDouble(productPriceString);
        int productMin = Integer.parseInt(productMinString);
        int productMax = Integer.parseInt(productMaxString);
        if (numbersMakeSense(productMin, productMax, productInventory)) {
            Product newProduct = new Product(productModified.getId(), productName, productPrice, productInventory, productMin, productMax);
            for (Part part : associatedParts) {
                newProduct.addAssociatedPart(part);
            }
            Inventory.updateProduct(productModified.getId() - 1, newProduct);
            productAdded = true;
        }

        if (productAdded) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainScene.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * checks to see if the min is not negative or more than the max,
     * and that the inventory level (stock) is not below the min or above the max.
     * Will notify user if anything is incorrect, and returns false if anything is.
     * @param min minimum of parts
     * @param max maximum of parts
     * @param stock available inventory
     * @return true if numbers make sense, false if numbers don't.
     */
    public boolean numbersMakeSense (int min, int max, int stock) {
        if (min <= 0) {
            MainController.superAlert("inform", "ERROR", "Min is negative or zero!", "Please correct.");
            return false;
        }
        if (min >= max) {
            MainController.superAlert("inform", "ERROR", "Min is more than or equal to max!", "Please correct.");
            return false;
        }
        if (stock < min || stock > max) {
            MainController.superAlert("inform", "ERROR", "Inventory is not within min-max range!", "Please correct.");
            return false;
        }
        return true;
    }

    /**
     * Cancels product modifying and goes back to main scene.
     * @param event cancel button clicked
     * @throws IOException
     */
    @FXML
    private void productCancelButton(ActionEvent event) throws IOException {
        if (MainController.superAlert("confirm", "Canceling Product Modification", "Are you sure you want to cancel?", ":(")) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainScene.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * initializes and sets data in tables
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //not currently associated parts table
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedParts = productModified.getAllAssociatedParts();
        partTable.setItems(Inventory.getAllParts());

        //associated parts table
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartTable.setItems(productModified.getAllAssociatedParts());

        //text fields
        productIDField.setText(String.valueOf(productModified.getId()));
        productNameField.setText(productModified.getName());
        productInventoryField.setText(String.valueOf(productModified.getStock()));
        productPriceField.setText(String.valueOf(productModified.getPrice()));
        productMaxField.setText(String.valueOf(productModified.getMax()));
        productMinField.setText(String.valueOf(productModified.getMin()));
    }
}
