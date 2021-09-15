package fct.cs.orders;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class orderDetailsController implements Initializable {

    @FXML
    private Button cancelBtn;

    private ordersController parentController;
    private JFXDrawer JFXDrawerdrawer;


    @FXML
    void close(ActionEvent event) {
        JFXDrawerdrawer.close();
    }

    public void setParentController(ordersController thisController) {
        this.parentController = thisController;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.JFXDrawerdrawer = drawer;
    }

    public MFXTableView MFXtblOrderDet;
    private orderDetailsController thisController = this;
//    private ObservableList<ordersInfo> orderDetObservableList = FXCollections.observableArrayList();
//    private FilteredList<ordersInfo> orderDetFilteredList = new FilteredList<>(orderDetObservableList);
//    private static orderDetailsData selectedOrderDet = null;
//
//    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setDetColumnProps();
//        getOrderDetData();
    }

//    private void setDetColumnProps() {
//        MFXTableColumn<orderDetailsData> orderDetIdColumn = new MFXTableColumn<>("Index", Comparator.comparing(orderDetailsData::getOrder_detail_id));
//        MFXTableColumn<orderDetailsData> bookIdColumn = new MFXTableColumn<>("Item ID", Comparator.comparing(orderDetailsData::getBook_id));
//        MFXTableColumn<orderDetailsData> bookTitleColumn = new MFXTableColumn<>("Book Name", Comparator.comparing(orderDetailsData::getTitle));
//        MFXTableColumn<orderDetailsData> quantityColumn = new MFXTableColumn<>("Quantity", Comparator.comparing(orderDetailsData::getQuantity));
//        MFXTableColumn<orderDetailsData> unitPriceColumn = new MFXTableColumn<>("Unit Price", Comparator.comparing(orderDetailsData::getUnit_price));
//        MFXTableColumn<orderDetailsData> priceColumn = new MFXTableColumn<>("Price", Comparator.comparing(orderDetailsData::getPrice));
//
//        orderDetIdColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getOrder_detail_id())));
//        bookIdColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getBook_id())));
//        bookTitleColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getTitle())));
//        quantityColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getUnit_price())));
//        unitPriceColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getQuantity())));
//        priceColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getPrice())));
//
//        MFXtblOrderDet.getTableColumns().addAll(orderDetIdColumn, bookIdColumn,bookTitleColumn, quantityColumn,unitPriceColumn,priceColumn);
//
//    }

//    private void getOrderDetData() {
//        MFXtblOrderDet.setItems(orderDetObservableList);
//    }

}
