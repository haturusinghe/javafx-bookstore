package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class mainPageController {

    public HBox header_hbox;

    public JFXButton inventoryPage;
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
    private VBox contentAnchor;

    private FXMLLoader loader = null;

    private String currentPage = "";

    @FXML
    void loadEmployeePage(ActionEvent event) {
        if (currentPage != "employees") {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/new-employee-page.fxml"));
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

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}