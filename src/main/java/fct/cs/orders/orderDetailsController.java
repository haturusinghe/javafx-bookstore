package fct.cs.orders;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.utils.ColorUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import fct.cs.orders.ordersInfo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class orderDetailsController implements Initializable {

    /* TODO : Improve Order Details Drawer UI with more styling */
    /* TODO : Add success/error Notifications */

//    @FXML
//    private VBox infoContainerVBox;

    @FXML
    private MFXListView<VBox> mfxList;

    private ObservableList<VBox> orderDetObservableList = FXCollections.observableArrayList();
//    @FXML
//    private TextField orderId;
@FXML
private Label orderId;

    @FXML
    private Label orderDate;

    @FXML
    private Label cusName;

    @FXML
    private Label empName;


    @FXML
    private Label total;

    @FXML
    private Label discount;

    @FXML
    private Label subTotal;



    private ordersController parentController;
    private JFXDrawer JFXDrawerdrawer;
    private ordersInfo currentOrder = null;

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
    private static orderDetailsData selectedOrderDet = null;

    //    private ObservableList<Category> categoryList = FXCollections.observableArrayList();
    private orderDetailsManager orderDetailsManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailsManager = new orderDetailsManager();
//        infoContainerVBox.getChildren().add(mfxList);
//        setDetColumnProps();
//        getOrderDetData();

    }

    ArrayList<orderDetailsData> list = new ArrayList<>();

    private void initOrderItemsList() {
//        ArrayList<orderDetailsData> list = new ArrayList<>();
        System.out.println("init order item list");
        ArrayList<orderDetailsData> list = orderDetailsManager.getOrderDetList(Integer.parseInt(currentOrder.getOrder_id()));
//        list = orderDetailsManager.getOrderDetList(1);
        orderDetObservableList.clear();
        System.out.println(list);
        for (orderDetailsData e : list) {
            orderDetObservableList.add(createOrderListItem(e));
            System.out.println(e.toString());
        }
        mfxList.setItems(orderDetObservableList);
//        System.out.println(orderItemsList);
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

    private VBox createOrderListItem(orderDetailsData e) {
        VBox vBox = new VBox();
//        vBox.setStyle("-fx-padding: 10px 15px;");
//        vBox.setStyle("-fx-background-radius: 10px;");
        vBox.setBackground(new Background(new BackgroundFill(ColorUtils.getRandomColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setPadding(new Insets(5, 0, 5, 10));
        HBox titleHbox = new HBox();
        HBox unitPriceHbox = new HBox();
        HBox quantityHbox = new HBox();
        HBox priceHbox = new HBox();


        FontIcon fileIcon = new FontIcon("cil-file");
        fileIcon.setIconColor(Color.BLACK);
        fileIcon.setIconSize(18);

        Label titleLabel = new Label("Title: " + e.getTitle(), fileIcon);
        titleHbox.getChildren().addAll(titleLabel);

        Label unitPriceLabel = new Label("Unit Price: " + e.getUnit_price());
        unitPriceHbox.getChildren().addAll(unitPriceLabel);

        Label quantityLabel = new Label("Quanitity: " + e.getQuantity());
        quantityHbox.getChildren().addAll(quantityLabel);

        Label priceLabel = new Label("Total: " + e.getPrice());
        priceHbox.getChildren().addAll(priceLabel);

        titleLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 20;");
        unitPriceLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 16;");
        quantityLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 14;");
        priceLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 16;");
        vBox.getChildren().addAll(titleHbox, unitPriceHbox, quantityHbox, priceHbox);
        return vBox;
    }


    public void setCurrentOrder(ordersInfo currentOrder) {
        System.out.println("Order Passed");
        this.currentOrder = currentOrder;
        initOrderItemsList();
    }
    private void resetForm() {
        orderId.setText("");
        orderDate.setText("");
        cusName.setText("");
        empName.setText("");
        total.setText("");
        discount.setText("");
        subTotal.setText("");
    }

    public void setEntry(ordersInfo currentOrder) {
        resetForm();
        orderId.setText(currentOrder.getOrder_id());
        orderDate.setText(currentOrder.getOrder_date());
        cusName.setText(currentOrder.getCustomer_name());
        empName.setText(currentOrder.getFirst_name());
        total.setText(String.valueOf(currentOrder.getTotal_price()));
        discount.setText(String.valueOf(currentOrder.getTotal_discount()));
        subTotal.setText(String.valueOf(currentOrder.getTotal_price()+currentOrder.getTotal_discount()));
    }
}
