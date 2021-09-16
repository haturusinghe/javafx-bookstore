package fct.cs.NewCustomer;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
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

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import java.util.ArrayList;

public class NewCustomerController implements Initializable {
    public MFXTableView customerTable;
    public JFXDrawer drawer;
    private fct.cs.NewCustomer.NewCustomerController thisController = this;
    private ObservableList<CustomerData> customerObservableList = FXCollections.observableArrayList();
    private FilteredList<CustomerData> customerFilteredList = new FilteredList<>(customerObservableList);
    private static CustomerData selectedCustomer = null;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCustomerData();
        setDataCustomer();
        setColumnProps();
        setColumnSize();
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


        customerTable.getTableColumns().addAll(customer_idColumn, customer_nameColumn, locationColumn, mobileColumn, emailColumn, updateColumn, deleteColumn);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewEntry(ActionEvent action){
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
}