package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import fct.cs.Bill.BillingController;
import fct.cs.Dash.DashController;
import fct.cs.NewCustomer.NewCustomerController;
import fct.cs.inventory.NewInventoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private JFXButton bookBtn;

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
    private int currentEmployeeID;

    public void setManager(boolean manager) {
        this.isManager = manager;
        System.out.println("Is manager" + manager);
//        loadHomePage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadHomePage();
    }

    @FXML
    void loadEmployeePage(ActionEvent event) {
        if (currentPage != "employees") {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/employee/new-employee-page.fxml"));
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
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/orders/orders.fxml"));
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
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/inventory/new-inventory.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                NewInventoryController inventoryController = loader.getController();
//                NewInventoryController.setManager(isManager);
                currentPage = "inventory";
                setHeaderText("Manage Inventory");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }
    public void loadBillingPage(ActionEvent actionEvent) {
        if (!currentPage.equals("Billing")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/billing/Billing.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "Billing";
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
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/dash/dash.fxml"));
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

    public void loadCustomerPage() {
        if (!currentPage.equals("customer")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/customer/new-customer-page.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                NewCustomerController controller = loader.getController();
                controller.setManager(isManager);
                currentPage = "customer";
                setHeaderText("Manage Customers");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }

    public void loadBilling(ActionEvent actionEvent) {

        if (!currentPage.equals("billing")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/billing/Billing.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                BillingController controller = loader.getController();
                controller.setManager(isManager);
                controller.getEmployeeId(currentEmployeeID);
                currentPage = "billing";
                setHeaderText("Create New Bill");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }

    }

    public void loadCustomer(ActionEvent actionEvent) {
        loadCustomerPage();
    }

    public void reportsPage(ActionEvent actionEvent) {

        if (!currentPage.equals("reports")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/report/report-generate.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                currentPage = "reports";
                setHeaderText("View Reports");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }

    }

    public void loadBookPage(ActionEvent actionEvent) {
        if (!currentPage.equals("books")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/book/mainbook.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                setHeaderText("Manage Books");
                currentPage = "books";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }
    }


    public void setCurrentEmployeeID(int idFromLogin) {
        currentEmployeeID = idFromLogin;
        System.out.println(currentEmployeeID);
    }
}