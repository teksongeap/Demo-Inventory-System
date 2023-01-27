module project.c482teksongeap {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.c482teksongeap to javafx.fxml;
    exports project.c482teksongeap;
    exports project.c482teksongeap.controller;
    opens project.c482teksongeap.controller to javafx.fxml;
    exports project.c482teksongeap.classes;
    opens project.c482teksongeap.classes to javafx.fxml;
}