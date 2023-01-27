package project.c482teksongeap.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import project.c482teksongeap.Main;
import project.c482teksongeap.classes.InHouse;
import project.c482teksongeap.classes.Part;
import project.c482teksongeap.classes.Inventory;
import project.c482teksongeap.classes.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for main scene.
 *
 * @author Teksong Eap
 */
public class MainController implements Initializable {
    //------------------------------SEARCH TEXT FIELDS--------------------------
    /**
     * text field for part search
     */
    @FXML
    private TextField partSearchField;

    /**
     * text field for product search
     */
    @FXML
    private TextField productSearchField;

    /**
     * Searches for part according to ID or full name. Checks if it actually exists, then shows on to table.
     *
     * A logic error that occurred was a bunch of red errors showing up in the console
     * in partSearchButton() and the error information alert not showing when I entered a part ID
     * that wasn't present in the AllParts list of Inventory. Also, the part shown on the
     * table in the main scene was one index higher than what I inputted.
     * I fixed this by subtracting 1 from the index variable after parsing it into an integer.
     *
     * @param event search button pressed
     * @throws IOException
     */
    @FXML
    private void partSearchButton(ActionEvent event) throws IOException {
        String textField = partSearchField.getText();
        ObservableList<Part> foundPart = FXCollections.observableArrayList();
        if (isInt(textField)) {
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
            superAlert("inform", "ERROR", "Part not found!", "Please try again.");
        } else {
            partTable.setItems(foundPart);
        }
    }

    /**
     * Searches for product according to ID or full name. Checks if it actually exists, then shows on to table.
     *
     * @param event search button pressed
     * @throws IOException
     */
    @FXML
    private void productSearchButton(ActionEvent event) throws IOException {
        String textField = productSearchField.getText();
        ObservableList<Product> foundProduct = FXCollections.observableArrayList();
        if (isInt(textField)) {
            int index = Integer.parseInt(textField);
            System.out.print(index);
            if (Inventory.getAllProducts().size() >= index) {
                foundProduct.add(Inventory.lookUpProduct(index - 1));
            }
        } else {
            foundProduct = Inventory.lookUpProduct(textField);
        }
        if (foundProduct.size() == 0) {
            superAlert("inform", "ERROR", "Product not found!", "Please try again.");
        } else {
            productTable.setItems(foundProduct);
        }
    }

    /**
     * checks if string is actually an integer
     * @param strNum string to check
     * @return true if integer or false if not
     */
    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * checks if string is actually a double
     * @param strNum string to check
     * @return true if double or false if not
     */
    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
     * Checks to see if product search field is empty, then repopulates table
     * @param event product search field currently selected
     */
    @FXML
    private void productSearchFieldEmpty(KeyEvent event) {
        if (productSearchField.getText().isEmpty()) {
            productTable.setItems(Inventory.getAllProducts());
        }
    }


    //-------------------------------TABLE VARIABLES----------------------------
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
     * variable referring to products table
     */
    @FXML
    private TableView<Product> productTable;

    /**
     * variable referring to product ID column
     */
    @FXML
    private TableColumn<Product, Integer> productID;

    /**
     * variable referring to product name column
     */
    @FXML
    private TableColumn<Product, String> productName;

    /**
     * variable referring to product inventory stock column
     */
    @FXML
    private TableColumn<Product, Integer> productInventory;

    /**
     * variable referring to product price column
     */
    @FXML
    private TableColumn<Product, Double> productPrice;

    //-----------------------------BUTTONS-----------------------------------

    /**
     * Brings the user to the Add Part scene.
     *
     * @param event add button on main scene pressed for parts
     * @throws IOException
     */
    @FXML
    private void partAddButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("AddPartScene.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Brings the user to the Add Product scene.
     *
     * @param event add button on main scene pressed for products
     * @throws IOException
     */
    @FXML
    private void productAddButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("AddProductScene.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes part from table so long as a part is selected.
     *
     * @param event delete button pressed on part table side
     * @throws IOException
     */
    @FXML
    private void partDeleteButton(ActionEvent event) throws IOException {
        if (partTable.getSelectionModel().getSelectedItem() == null){
            superAlert("inform", "ERROR", "Part not selected!", "Please select part.");
        } else {
            Part selected = partTable.getSelectionModel().getSelectedItem();
            if (superAlert("confirm", "Delete Part", selected.getName() + " selected.", "You sure you want to delete this part?")) {
                Inventory.deletePart(selected);
            }
        }
    }

    /**
     * Deletes product from table so long as a product is selected. Checks to make sure there are no associated
     * parts before deleting.
     *
     * @param event delete button pressed on product table side
     * @throws IOException
     */
    @FXML
    private void productDeleteButton(ActionEvent event) throws IOException {
        if (productTable.getSelectionModel().getSelectedItem() == null){
            superAlert("inform", "ERROR", "Product not selected!", "Please select product.");
        } else {
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if (superAlert("confirm", "Delete Product", selected.getName() + " selected.", "You sure you want to delete this product?")) {
                if (selected.getAllAssociatedParts().size() > 0) {
                    superAlert("inform", "ERROR", "Associated parts present!", "Cannot delete " + selected.getName() + ".");
                } else {
                    Inventory.deleteProduct(selected);
                }
            }
        }
    }

    /**
     * Checks for any selected parts and then sets partModified in ModifyPartSceneController, after which
     * the modifyPartScene is loaded.
     *
     * @param event modify part button clicked
     * @throws IOException
     */
    @FXML
    private void partModifyButton(ActionEvent event) throws IOException {
        if (partTable.getSelectionModel().getSelectedItem() == null){
            superAlert("inform", "ERROR", "Part not selected!", "Please select part.");
        } else {
            ModifyPartSceneController.partModified = partTable.getSelectionModel().getSelectedItem();
            if (ModifyPartSceneController.partModified instanceof InHouse) {
                ModifyPartSceneController.partType = "inHouse";
            } else {
                ModifyPartSceneController.partType = "outsourced";
            }
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("ModifyPartScene.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Checks for any selected products and then sets productModified in ModifyProductSceneController, after which
     * the modifyProductScene is loaded.
     *
     * @param event modify product button clicked
     * @throws IOException
     */
    @FXML
    private void productModifyButton(ActionEvent event) throws IOException {
        if (productTable.getSelectionModel().getSelectedItem() == null){
            superAlert("inform", "ERROR", "Product not selected!", "Please select product.");
        } else {
            ModifyProductSceneController.productModified = productTable.getSelectionModel().getSelectedItem();
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("ModifyProductScene.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Puts out prompt asking for confirmation, which when received, exits the program.
     *
     * @param event exit button on main scene pressed
     * @throws IOException
     */
    @FXML
    private void exitButton(ActionEvent event) throws IOException {
        if (superAlert("confirm", "Exiting Program", "Don't go!", "Do you want to quit?")) {
            System.exit(0);
        }
    }

    /**
     * A "super alert" that can take care of both info alerts and confirmation alerts.
     *
     * @param alertType the type of alert "inform" or "confirm"
     * @param title the title of the alert
     * @param header the header of the alert
     * @param content the content of the alert
     * @return true if it is a confirmation alert and the user presses OK, false otherwise
     */
    public static boolean superAlert(String alertType, String title, String header, String content) {
        if (Objects.equals(alertType, "inform")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
            return false;
        } else if (Objects.equals(alertType, "confirm")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            Optional<ButtonType> confirmation = alert.showAndWait();
            return confirmation.get() == ButtonType.OK;
        }
        return false;
    }

    /**
     * initializes and sets cell values for both tables
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setting cell values for parts table
        partTable.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //setting cell values for products table
        productTable.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}