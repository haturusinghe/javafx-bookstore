package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class mainPageController {

    @FXML
    private VBox mainContent_vbox;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private JFXButton employeePage;

    @FXML
    private VBox contentPanel_vbox;

    @FXML
    private AnchorPane contentAnchor;

    private FXMLLoader loader = null;

    @FXML
    void loadEmployeePage(ActionEvent event) {
        loader = new FXMLLoader(getClass().getResource("/fct/cs/employee.fxml"));
        try {
            mainContent_vbox.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        EmployeeController childController = loader.getController();
    }

}