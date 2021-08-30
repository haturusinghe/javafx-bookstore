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
    private JFXButton orderBtn;

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

    private String currentPage = "";

    @FXML
    void loadEmployeePage(ActionEvent event) {
        if (currentPage != "employees") {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/employee.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "employees";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
//        EmployeeController childController = loader.getController();
    }



    public void loadOrdersPage(ActionEvent actionEvent) {
        if (!currentPage.equals("orders")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/orders.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "orders";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }

    public void loadInventoryPage(ActionEvent actionEvent) {
        if (!currentPage.equals("inventory")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/inventory.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "inventory";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }

    public void loadReportsPage(ActionEvent actionEvent) {
        if (!currentPage.equals("reports")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/reports.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "reports";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }
}