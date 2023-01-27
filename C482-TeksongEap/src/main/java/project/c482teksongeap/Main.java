package project.c482teksongeap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.c482teksongeap.classes.InHouse;
import project.c482teksongeap.classes.Inventory;
import project.c482teksongeap.classes.Outsourced;
import project.c482teksongeap.classes.Product;
import java.io.IOException;

/**
 * The Inventory Management System program is designed to manage an inventory of products
 * and their associated parts.
 *
 * A logic error that occurred while running Main class was that the products weren't showing on the products table.
 * This was a result of me forgetting to actually add the product to Inventory using addProduct().
 *
 * @author Teksong Eap
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000,600);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method for creating sample data and launching the app.
     * @param args
     */
    public static void main(String[] args) {

        //add sample parts
        int id = Inventory.getNewPartId();
        InHouse bowString = new InHouse(id, "Bow String", 20.99, 300, 1, 500, 47);
        id = Inventory.getNewPartId();
        InHouse upperLimb = new InHouse(id, "Upper Limb", 100.99, 100, 1, 105, 47);
        id = Inventory.getNewPartId();
        InHouse lowerLimb = new InHouse(id, "Lower Limb", 100.99, 100, 1, 105, 47);
        id = Inventory.getNewPartId();
        InHouse bowGrip = new InHouse(id, "Bow Grip", 150.99, 100, 1, 105, 47);
        id = Inventory.getNewPartId();
        Outsourced arrow = new Outsourced(id, "Arrow", 5.99, 1000, 20, 10000, "Straight & True");
        Inventory.addPart(bowString);
        Inventory.addPart(upperLimb);
        Inventory.addPart(lowerLimb);
        Inventory.addPart(bowGrip);
        Inventory.addPart(arrow);

        //add sample product
        id = Inventory.getNewProductId();
        Product bowAndArrow = new Product(id, "Bow and Arrow", 799.95, 100, 1, 200);
        bowAndArrow.addAssociatedPart(bowString);
        bowAndArrow.addAssociatedPart(bowGrip);
        bowAndArrow.addAssociatedPart(lowerLimb);
        bowAndArrow.addAssociatedPart(upperLimb);
        bowAndArrow.addAssociatedPart(arrow);
        Inventory.addProduct(bowAndArrow);
        launch(args);
    }
}