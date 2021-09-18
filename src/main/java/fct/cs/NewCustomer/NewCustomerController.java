package fct.cs.NewCustomer;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.NewEmployee.EmployeeData;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.enums.Styles;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import java.util.ArrayList;

public class NewCustomerController implements Initializable {

    /* TODO : Add error/success notifications */

    public MFXTableView customerTable;
    public JFXDrawer drawer;
    private fct.cs.NewCustomer.NewCustomerController thisController = this;
    private ObservableList<CustomerData> customerObservableList = FXCollections.observableArrayList();
    private FilteredList<CustomerData> customerFilteredList;
    private static CustomerData selectedCustomer = null;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();
    private final MFXTextField searchField = new MFXTextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCustomerData();
        setDataCustomer();
        setColumnProps();
        setColumnSize();

        customerTable.setHeaderSupplier(() -> {
            HBox mainContainer = new HBox();
            mainContainer.setPrefHeight(50);
            mainContainer.setPrefWidth(1030);
            mainContainer.setAlignment(Pos.CENTER_LEFT);
            mainContainer.setPadding(new Insets(10,0,5,5));

            HBox spanMid = new HBox();
            spanMid.setMinWidth(610);
            spanMid.setMinHeight(50);

            HBox spanEnd = new HBox();
            spanEnd.setMinHeight(50);
            spanEnd.setMaxWidth(10);

            HBox searchContainer = new HBox(10);
            searchContainer.setMinHeight(50);
            searchContainer.setAlignment(Pos.CENTER_RIGHT);
            searchField.setPromptText("Search Customers");
            searchField.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
            searchField.setIconInsets(new Insets(0,0,10,0));
            searchField.setMinHeight(50);
            searchField.setStyle("-fx-font-size: 18px;");

            searchField.setOnKeyTyped(actionEvent -> {
                customerFilteredList = new FilteredList<CustomerData>(customerObservableList);
                thisController.searchTableFromText(searchField.getText());
                customerTable.setItems(customerFilteredList);
            });


            FontIcon addIcon = new FontIcon("anto-plus-circle");
            addIcon.setIconColor(Color.WHITE);
            addIcon.setIconSize(25);

            MFXButton addBtn = new MFXButton();
            addBtn.setText("Add Customer");
            addBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            addBtn.setGraphic(addIcon);
            addBtn.setOnAction(actionEvent -> {
                thisController.addNewEntry();
            });


            /*MFXLabel testLabel = new MFXLabel("Header");
            HBox holderHbox = new HBox();
            holderHbox.getChildren().addAll(testLabel);
            holderHbox.setMaxWidth(Region.USE_PREF_SIZE);
            VBox box = new VBox(holderHbox);
            box.setAlignment(Pos.CENTER_RIGHT);*/

            searchContainer.getChildren().addAll(searchField);
            mainContainer.getChildren().addAll(searchContainer,spanMid,addBtn,spanEnd);
            return mainContainer;
        });
    }

    private void setColumnSize() {
        ObservableList<MFXTableColumn> cols = customerTable.getTableColumns();
        for (MFXTableColumn col : cols) {
//            col.setResizable(false);

            if (col.getText() != "") {
                col.setShowLockIcon(false);
                col.setMinWidth(50);
            }
        }
    }

    public void getCustomerData() {
        ArrayList<CustomerData> cList = CustomerManager.getCustomerList(100, 1);
        customerObservableList.clear();
        for (CustomerData c :
                cList) {
            customerObservableList.add(c);
        }

    }

    private void setDataCustomer() {
        customerTable.setItems(customerObservableList);

    }

    @SuppressWarnings("unchecked")
    private void setColumnProps() {

        MFXTableColumn<CustomerData> customer_idColumn = new MFXTableColumn<>("Customer ID", Comparator.comparing(CustomerData::getcustomer_id));
        MFXTableColumn<CustomerData> customer_nameColumn = new MFXTableColumn<>("Customer Name", Comparator.comparing(CustomerData::getcustomer_name));
        MFXTableColumn<CustomerData> locationColumn = new MFXTableColumn<>("Location", Comparator.comparing(CustomerData::getlocation));
        MFXTableColumn<CustomerData> mobileColumn = new MFXTableColumn<>("Mobile", Comparator.comparing(CustomerData::getmobile));
        MFXTableColumn<CustomerData> emailColumn = new MFXTableColumn<>("Email", Comparator.comparing(CustomerData::getemail));
        MFXTableColumn<CustomerData> updateColumn = new MFXTableColumn<>("", Comparator.comparing(CustomerData::getcustomer_id));
        MFXTableColumn<CustomerData> deleteColumn = new MFXTableColumn<>("", Comparator.comparing(CustomerData::getcustomer_id));

        customer_idColumn.setRowCellFunction(customer -> new MFXTableRowCell(String.valueOf(customer.getcustomer_id())));
        customer_nameColumn.setRowCellFunction(customer -> new MFXTableRowCell(String.valueOf(customer.getcustomer_name())));
        locationColumn.setRowCellFunction(customer -> new MFXTableRowCell(String.valueOf(customer.getlocation())));
        mobileColumn.setRowCellFunction(customer -> new MFXTableRowCell(String.valueOf(customer.getmobile())));
        emailColumn.setRowCellFunction(customer -> new MFXTableRowCell(String.valueOf(customer.getemail())));


                updateColumn.setMinWidth(100);
                updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(customerData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Update");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Update");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/customer/customer-form.fxml"));
                    VBox box = loader.load();
                    CustomerFormController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(drawer);
//                    controller.setInventoryManager(inventoryManager);
                    controller.setEntry(customerData);
                    controller.setAddingNew(false);
                    drawer.setSidePane(box);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (drawer.isHidden()) {
                    drawer.open();
                    drawer.toFront();
                    System.out.println("open");
                } else {
                    drawer.toBack();
                    drawer.close();

                    System.out.println("close");
                }

            });
            MFXFontIcon icon = new MFXFontIcon("mfx-file", 25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.LIMEGREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> customerData.setState(customerData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });

        deleteColumn.setMinWidth(100);
        deleteColumn.setShowLockIcon(false);
        deleteColumn.setRowCellFunction(customerData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Delete");
            rowCell.setGraphicTextGap(5);
//            rowCell.setStyle("-fx-background-color:grey");
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Delete");
                CustomerManager.deleteSingleCustomer(customerData);
                getCustomerData();
                setDataCustomer();
            });
            MFXFontIcon icon = new MFXFontIcon("mfx-minus-circle", 25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> customerData.setState(customerData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });
//        updateColumn.setResizable(false);
//        updateColumn.setMaxWidth(20);

     /*  FontIcon addIcon = new FontIcon("anto-plus-circle");
        addIcon.setIconColor(Color.WHITE);
        addIcon.setIconSize(25);

        MFXButton addBtn = new MFXButton();
        addBtn.setText("Add Book");
        addBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
        addBtn.setGraphic(addIcon);
        addBtn.setOnAction(actionEvent -> {
            thisController.addNewCustomer();
        });*/


        customerTable.getTableColumns().addAll(customer_idColumn, customer_nameColumn, locationColumn, mobileColumn, emailColumn, updateColumn, deleteColumn);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewEntry(){
        addNewCustomer(drawer);
    }

    public void addNewCustomer(JFXDrawer drawer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/customer/customer-form.fxml"));
            VBox box = loader.load();
            CustomerFormController controller = loader.getController();

            controller.setParentController(thisController);
            controller.setDrawer(drawer);
//                    controller.setInventoryManager(inventoryManager);
//            controller.setEntry(employeeData);
            controller.setAddingNew(true);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (drawer.isHidden()) {
            drawer.open();
            drawer.toFront();
            System.out.println("open");
        } else {
            drawer.toBack();
            drawer.close();

            System.out.println("close");
        }
    }

    public void setManager(boolean isManager) {
    }

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        customerFilteredList.setPredicate(customerData -> {
            String filter = key.toLowerCase();
            boolean nameMatches = String.valueOf(customerData.getcustomer_name()).toLowerCase().contains(filter);
            return nameMatches;
        });
    }
}