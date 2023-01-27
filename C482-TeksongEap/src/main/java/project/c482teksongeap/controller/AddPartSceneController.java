package project.c482teksongeap.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import project.c482teksongeap.Main;
import project.c482teksongeap.classes.InHouse;
import project.c482teksongeap.classes.Inventory;
import project.c482teksongeap.classes.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for adding parts scene.
 *
 * @author Teksong Eap
 */
public class AddPartSceneController implements Initializable {
    /**
     * In house radio button
     */
    @FXML
    private RadioButton inHouseRadio;

    /**
     * sets last label to "Machine ID"
     * @param event outsourced Radio Button pressed
     */
    @FXML
    private void inHouseRadioButton(ActionEvent event) {
        machineIDCompanyNameLabel.setText("Machine ID");
        partType = "inHouse";
    };

    /**
     * Outsourced radio button
     */
    @FXML
    private RadioButton outsourcedRadio;

    /**
     * sets last label to "Company"
     * @param event outsourced Radio Button pressed
     */
    @FXML
    private void outsourcedRadioButton(ActionEvent event) {
        machineIDCompanyNameLabel.setText("Company");
        partType = "outsourced";
    };

    /**
     * toggle group for radio buttons
     */
    @FXML
    private ToggleGroup togglePartType;

    /**
     * part type either "inHouse" or "outsourced"
     */
    private static String partType;

    /**
     * text field for part ID
     */
    @FXML
    private TextField partIDField;

    /**
     * text field for part name
     */
    @FXML
    private TextField partNameField;

    /**
     * text field for stock
     */
    @FXML
    private TextField partInventoryField;

    /**
     * text field for part price
     */
    @FXML
    private TextField partPriceField;

    /**
     * text field for part min
     */
    @FXML
    private TextField partMinField;

    /**
     * text field for part max
     */
    @FXML
    private TextField partMaxField;

    /**
     * text field for machine ID or company name
     */
    @FXML
    private TextField machineIDCompanyNameField;

    /**
     * label for machine ID or company name
     */
    @FXML
    private Label machineIDCompanyNameLabel;

    /**
     * Elegantly uses IF statements to check if data was properly and fully entered into the text fields,
     * notifies the user if anything is not right. If everything is correct, a Part object of either inHouse
     * or outsourced type is created and added to Inventory. Returns to main scene if everything is successful.
     *
     * A logic error that occurred was when I entered information incorrectly in partSaveButton(),
     * the unique partID in Inventory still kept incrementing. I solved this by only calling getNewPartID()
     * when all the conditions for a correct part were met.
     *
     * @param event part save button pressed
     * @throws IOException
     */
    @FXML
    private void partSaveButton(ActionEvent event) throws IOException {
//        int partID = Inventory.getNewPartId();
        boolean partAdded = false;
        String partName = partNameField.getText();
        if (partName.isEmpty()) {
            MainController.superAlert("inform", "ERROR", "Part name empty!", "Please try again.");
            return;
        }
        String partInventoryString = partInventoryField.getText();
        if (partInventoryString.isEmpty() || !MainController.isInt(partInventoryString)) {
            MainController.superAlert("inform", "ERROR", "Inventory is empty or was entered incorrectly!", "Please enter an integer.");
            return;
        }
        String partPriceString = partPriceField.getText();
        if (partPriceString.isEmpty() || !MainController.isDouble(partPriceString)) {
            MainController.superAlert("inform", "ERROR", "Price is empty or was entered incorrectly!", "Please enter a double.");
            return;
        }
        String partMinString = partMinField.getText();
        if (partMinString.isEmpty() || !MainController.isInt(partMinString)) {
            MainController.superAlert("inform", "ERROR", "Min is empty or was entered incorrectly!", "Please enter an integer.");
            return;
        }
        String partMaxString = partMaxField.getText();
        if (partMaxString.isEmpty() || !MainController.isInt(partMaxString)) {
            MainController.superAlert("inform", "ERROR", "Max is empty or was entered incorrectly!", "Please enter an integer.");
            return;
        }
        String machineIDCompanyName = machineIDCompanyNameField.getText();
        if (Objects.equals(partType, "inHouse")) {
            if (machineIDCompanyName.isEmpty() || !MainController.isInt(machineIDCompanyName)) {
                MainController.superAlert("inform", "ERROR", "Machine ID is empty or was entered incorrectly!", "Please enter an integer.");
                return;
            }
            int partInventory = Integer.parseInt(partInventoryString);
            double partPrice = Double.parseDouble(partPriceString);
            int partMin = Integer.parseInt(partMinString);
            int partMax = Integer.parseInt(partMaxString);
            int machineID = Integer.parseInt(machineIDCompanyName);
            if (numbersMakeSense(partMin, partMax, partInventory)) {
                InHouse newPart = new InHouse(Inventory.getNewPartId(), partName, partPrice, partInventory, partMin, partMax, machineID);
                Inventory.addPart(newPart);
                partAdded = true;
            }
        }
        if (Objects.equals(partType, "outsourced")) {
            if (machineIDCompanyName.isEmpty() || MainController.isInt(machineIDCompanyName)) {
                MainController.superAlert("inform", "ERROR", "Company Name is empty or was entered incorrectly!", "Please enter a string.");
                return;
            }
            int partInventory = Integer.parseInt(partInventoryString);
            double partPrice = Double.parseDouble(partPriceString);
            int partMin = Integer.parseInt(partMinString);
            int partMax = Integer.parseInt(partMaxString);
            if (numbersMakeSense(partMin, partMax, partInventory)) {
                Outsourced newPart = new Outsourced(Inventory.getNewPartId(), partName, partPrice, partInventory, partMin, partMax, machineIDCompanyName);
                Inventory.addPart(newPart);
                partAdded = true;
            }
        }
        if (partAdded) {
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
     *
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
     * Cancels part modifying and goes back to main scene
     * @param event cancel button pressed
     * @throws IOException
     */
    @FXML
    private void partCancelButton(ActionEvent event) throws IOException {
        if (MainController.superAlert("confirm", "Canceling Part Addition", "Are you sure you want to cancel?", ":(")) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MainScene.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Initializes and sets values for text fields and radio buttons
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inHouseRadio.setSelected(true);
        partType = "inHouse";
    }
}
