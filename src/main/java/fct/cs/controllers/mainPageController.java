package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import fct.cs.Dash.DashController;
import fct.cs.inventory.InventoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    public JFXButton homePage;
    @FXML
    private Label pageHeaderLabel;

    @FXML
    private HBox header_hbox;

    @FXML
    private JFXButton inventoryPage;

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

    private boolean isManager = true;

    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadHomePage();
    }

    @FXML
    void loadEmployeePage(ActionEvent event) {
        if (currentPage != "employees") {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/new-employee-page.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "employees";
                setHeaderText("Manage Employees");
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
                setHeaderText("Manage Orders");
                currentPage = "orders";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }

    public void loadInventoryPage(ActionEvent actionEvent) {
       loadInventoryPage();
    }

    public void loadInventoryPage() {
        if (!currentPage.equals("inventory")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/inventory.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                InventoryController inventoryController = loader.getController();
                inventoryController.setManager(isManager);
                currentPage = "inventory";
                setHeaderText("Manage Inventory");
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

    public void setHeaderText(String string){
        pageHeaderLabel.setText(string);
        pageHeaderLabel.setStyle("-fx-font-family: Montserrat;");
    }

    public void loadHome(ActionEvent actionEvent) {
        loadHomePage();

    }

    public void loadHomePage() {
        if (!currentPage.equals("home")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/dash.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                DashController controller = loader.getController();
                controller.setManager(isManager);
                currentPage = "home";
                setHeaderText("Welcome");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }

    public void loadBilling(ActionEvent actionEvent) {
    }
}