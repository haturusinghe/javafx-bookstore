package fct.cs.orders;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.NewEmployee.EmployeeData;
import fct.cs.NewEmployee.EmployeeFormController;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.enums.Styles;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import fct.cs.orders.orderDetailsController;
import fct.cs.orders.orderDetailsData;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ordersController implements Initializable {
    public MFXTableView MFXtblOrderTable;
    public JFXDrawer JFXDrawerdrawer;
    private ordersController thisController = this;
    private ObservableList<ordersInfo> orderObservableList = FXCollections.observableArrayList();
    private FilteredList<ordersInfo> orderFilteredList = new FilteredList<>(orderObservableList);
    private static ordersInfo selectedOrder = null;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();


    private final MFXTextField searchField = new MFXTextField();
    private final MFXComboBox<String> searchCombo = new MFXComboBox<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getOrderData();
        setColumnProps();
        setColumnSize();

        MFXtblOrderTable.setHeaderSupplier(() -> {
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
            searchField.setPromptText("Search By Date");
            searchField.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
            searchField.setIconInsets(new Insets(0,0,10,0));
            searchField.setMinHeight(50);
            searchField.setStyle("-fx-font-size: 18px;");

            searchField.setOnKeyTyped(actionEvent -> {
                orderFilteredList = new FilteredList<ordersInfo>(orderObservableList);
                thisController.searchTableFromText(searchField.getText());
                MFXtblOrderTable.setItems(orderFilteredList);
            });

            /*MFXLabel testLabel = new MFXLabel("Header");
            HBox holderHbox = new HBox();
            holderHbox.getChildren().addAll(testLabel);
            holderHbox.setMaxWidth(Region.USE_PREF_SIZE);
            VBox box = new VBox(holderHbox);
            box.setAlignment(Pos.CENTER_RIGHT);*/

            searchContainer.getChildren().addAll(searchField);
            mainContainer.getChildren().addAll(searchContainer,spanMid,spanEnd);
            return mainContainer;
        });

    }

    public void getOrderData() {
        ArrayList<ordersInfo> eList = OrderManager.getOrderList(100, 1);
        orderObservableList.clear();
        for (ordersInfo e:
                eList) {
            orderObservableList.add(e);

        }
        setDataData();
    }

    private void setDataData() {
        MFXtblOrderTable.setItems(orderObservableList);
    }

    @SuppressWarnings("unchecked")
    private void setColumnProps() {

        MFXTableColumn<ordersInfo> orderIdColumn = new MFXTableColumn<>("Order ID", Comparator.comparing(ordersInfo::getOrder_id));
        MFXTableColumn<ordersInfo> cusIdColumn = new MFXTableColumn<>("Customer ID", Comparator.comparing(ordersInfo::getCustomer_id));
        MFXTableColumn<ordersInfo> empIdColumn = new MFXTableColumn<>("Employee ID", Comparator.comparing(ordersInfo::getEmployee_id));
        MFXTableColumn<ordersInfo> orderDateColumn = new MFXTableColumn<>("Order Date", Comparator.comparing(ordersInfo::getOrder_date));
        MFXTableColumn<ordersInfo> tQtyColumn = new MFXTableColumn<>("Total Quantity", Comparator.comparing(ordersInfo::getTotal_quantity));
        MFXTableColumn<ordersInfo> tPriceColumn = new MFXTableColumn<>("Total Price", Comparator.comparing(ordersInfo::getTotal_price));
        MFXTableColumn<ordersInfo> tDiscountColumn = new MFXTableColumn<>("Total Discount", Comparator.comparing(ordersInfo::getTotal_discount));
        MFXTableColumn<ordersInfo> viewColumn = new MFXTableColumn<>("Order Details", Comparator.comparing(ordersInfo::getOrder_id));

        orderIdColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getOrder_id())));
        cusIdColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getCustomer_id())));
        empIdColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getEmployee_id())));
        orderDateColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getOrder_date())));
        tQtyColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getTotal_quantity())));
        tPriceColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getTotal_price())));
        tDiscountColumn.setRowCellFunction(order -> new MFXTableRowCell(String.valueOf(order.getTotal_discount())));

        viewColumn.setMinWidth(70);
        viewColumn.setShowLockIcon(false);

        viewColumn.setRowCellFunction(orderData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Order Details");
            rowCell.setGraphicTextGap(1);
//            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/order-details.fxml"));
//                    VBox box = loader.load();
//                    orderDetailsController controller = loader.getController();
//
//                    controller.setParentController(thisController);
//                    controller.setDrawer(JFXDrawerdrawer);
//                    JFXDrawerdrawer.setSidePane(box);
//
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//
//                if (JFXDrawerdrawer.isHidden()) {
//                    JFXDrawerdrawer.open();
//                    JFXDrawerdrawer.toFront();
//                    System.out.println("open");
//                } else {
//                    JFXDrawerdrawer.toBack();
//                    JFXDrawerdrawer.close();
//
//                    System.out.println("close");
//                }
//
//            });
            MFXFontIcon icon = new MFXFontIcon("mfx-file", 25);

            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.LIMEGREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
            rowCell.setPadding(new Insets(2, 2, 2, 2));
            return rowCell;
        });

        MFXtblOrderTable.getTableColumns().addAll(orderIdColumn, cusIdColumn,empIdColumn, orderDateColumn,tPriceColumn,tQtyColumn,tDiscountColumn,viewColumn);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        JFXDrawerdrawer.toBack();
    }

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        orderFilteredList.setPredicate(orderData -> {
            String filter = key.toLowerCase();
            boolean nameMatches = /*String.valueOf(orderData.getOrder_id()).toLowerCase().contains(filter) ||*/
                    String.valueOf(orderData.getOrder_date()).toLowerCase().contains(filter);
            return nameMatches;
        });
    }

    private void setColumnSize() {
        ObservableList<MFXTableColumn> cols =  MFXtblOrderTable.getTableColumns();
        for (MFXTableColumn col : cols) {
            if (col.getText() != "") {
                col.setShowLockIcon(false);
                col.setMinWidth(30);
            }
        }
    }

}

