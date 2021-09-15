package fct.cs.orders;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.NewEmployee.EmployeeFormController;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getOrderData();
        setColumnProps();
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
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/order-details.fxml"));
                    VBox box = loader.load();
                    orderDetailsController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(JFXDrawerdrawer);
                    JFXDrawerdrawer.setSidePane(box);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (JFXDrawerdrawer.isHidden()) {
                    JFXDrawerdrawer.open();
                    JFXDrawerdrawer.toFront();
                    System.out.println("open");
                } else {
                    JFXDrawerdrawer.toBack();
                    JFXDrawerdrawer.close();

                    System.out.println("close");
                }

            });
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

}

