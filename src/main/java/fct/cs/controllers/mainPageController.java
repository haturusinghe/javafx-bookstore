package fct.cs.controllers;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import fct.cs.Bill.BillingController;
import fct.cs.Dash.DashController;
import fct.cs.NewCustomer.NewCustomerController;
import fct.cs.Profile.ProfileController;
import fct.cs.commonUtil.AppUtils;
import fct.cs.inventory.NewInventoryController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    /* TODO: Hide Employees if not Manager */

    public JFXButton homePage;

    private HBox topBar;

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
    private int currentEmployeeID = -69;

    public void setManager(boolean manager) {
        this.isManager = manager;
        System.out.println("Is manager" + manager);
        if (!isManager) {
            employeePage.setDisable(true);
        } else {
            employeePage.setDisable(false);
        }
//        loadHomePage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        <HBox fx:id="topBar" alignment="CENTER_RIGHT" prefHeight="78.0" prefWidth="842.0" />
        topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPrefHeight(78);
        topBar.setPrefWidth(842);

        MFXButton btnNoti = new MFXButton("", 20, 30);
        btnNoti.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(90), Insets.EMPTY)));
        FontIcon infoIcon = new FontIcon("cil-bell-exclamation");
        infoIcon.setIconColor(Color.WHITE);
        infoIcon.setIconSize(15);


      /*  JFXBadge badge = new JFXBadge(btnNoti, Pos.TOP_RIGHT);
        badge.setText("1");
        badge.setStyle("-fx-text-fill:#fff;");
        badge.setMinHeight(10);
        badge.setMinWidth(10);
        badge.setMaxSize(10,10);
        badge.setPrefSize(10,10);
        badge.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY,Insets.EMPTY)));
        badge.setEnabled(true);*/

//        btnNoti.setGraphic(new StackPane(infoIcon,badge));
        btnNoti.setGraphic(infoIcon);
        btnNoti.setOnAction(actionEvent -> {
            AbstractMFXDialog genericDialog = MFXDialogFactory.buildGenericDialog("Group 05 - Members", "1. CS/2018/013 - GEETHANJANA K.K.D.Y.\n" +
                    "2. CS/2018/014 - GUNARATHNA A.D.D.\n" +
                    "3. CS/2018/015 - HATURUSINGHE S.\n" +
                    "4. CS/2018/017 - JAYALATH H.G.A.S.\n" +
                    "5. CS/2018/032 - PERERA W.N.B.\n" +
                    "6. CS/2018/038 - SANDAMINI W.A.H.\n" +
                    "7. CS/2018/044 - THILAKARATHNE D.G.S.P.");
            genericDialog.setCloseHandler(c -> {
                genericDialog.close();
                mainPageController.this.mainAnchor.getChildren().remove(genericDialog);
            });
            genericDialog.setVisible(false);
            this.mainAnchor.getChildren().add(genericDialog);
            genericDialog.setIsDraggable(true);
            genericDialog.show();
        });
        topBar.getChildren().addAll(btnNoti);

        loadHomePage();
    }

    @FXML
    void loadEmployeePage(ActionEvent event) {
        header_hbox.getChildren().remove(topBar);
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
        } else {
            System.out.println("already loaded");
        }
//        EmployeeController childController = loader.getController();
    }

    public void loadOrdersPage(ActionEvent actionEvent) {
        header_hbox.getChildren().remove(topBar);
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
        } else {
            System.out.println("already loaded");
        }
    }

    public void loadInventoryPage(ActionEvent actionEvent) {
        loadInventoryPage();
    }

    public void loadInventoryPage() {
        header_hbox.getChildren().remove(topBar);
        if (!currentPage.equals("inventory")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/inventory/new-inventory.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                NewInventoryController inventoryController = loader.getController();
                inventoryController.setManager(isManager);
                currentPage = "inventory";
                setHeaderText("Manage Inventory");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("already loaded");
        }
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setHeaderText(String string) {
        pageHeaderLabel.setText(string);
        pageHeaderLabel.setStyle("-fx-font-family: Montserrat;");
    }

    public void loadHome(ActionEvent actionEvent) {
        loadHomePage();

    }

    public void loadHomePage() {
        if (!currentPage.equals("home")) {
            header_hbox.getChildren().add(topBar);
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
        } else {
            System.out.println("already loaded");
        }
    }

    public void loadCustomerPage() {
        header_hbox.getChildren().remove(topBar);
        header_hbox.getChildren().remove(topBar);
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
        } else {
            System.out.println("already loaded");
        }
    }

    public void loadBilling(ActionEvent actionEvent) {
        header_hbox.getChildren().remove(topBar);
        if (!currentPage.equals("billing")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/billing/Billing.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                BillingController controller = loader.getController();
                controller.setManager(isManager);
                controller.setEmployeeId(currentEmployeeID);
                currentPage = "billing";
                setHeaderText("Create New Bill");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("already loaded");
        }

    }

    public void loadCustomer(ActionEvent actionEvent) {
        loadCustomerPage();
    }

    public void reportsPage(ActionEvent actionEvent) {
        header_hbox.getChildren().remove(topBar);
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
        } else {
            System.out.println("already loaded");
        }

    }

    public void loadBookPage(ActionEvent actionEvent) {
        header_hbox.getChildren().remove(topBar);
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
        } else {
            System.out.println("already loaded");
        }
    }

    public void setCurrentEmployeeID(int idFromLogin) {
        this.currentEmployeeID = idFromLogin;
        System.out.println("Current Employee ID : " + currentEmployeeID);
    }

    public void logout(ActionEvent actionEvent) {
        header_hbox.getChildren().remove(topBar);
        Parent view = null;
        try {
            view = FXMLLoader.load(getClass().getResource("/fct/cs/fxml/login/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(view);
        System.out.println("Load Login page");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AppUtils.enableDrag(scene,window);
        window.setScene(scene);
        window.show();
    }

    public void loadProfile(ActionEvent actionEvent) {
        header_hbox.getChildren().remove(topBar);
        if (!currentPage.equals("profile")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/profile/profile.fxml"));
            try {
                mainContent_vbox.getChildren().clear();
                mainContent_vbox.getChildren().add(loader.load());
                ProfileController controller = loader.getController();
//                controller.setManager(isManager);
                controller.setEmployeeId(currentEmployeeID);
                currentPage = "profile";
                setHeaderText("User Profile");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("already loaded");
        }
    }

}