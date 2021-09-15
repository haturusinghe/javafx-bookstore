package fct.cs.orders;

import fct.cs.data.Order;
import fct.cs.data.OrderDetailEntry;
import fct.cs.orders.OrderManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ordersController implements Initializable {

    public TextField orderID_txtField;
    @FXML
    private TextField pageCount_txtField;

    @FXML
    private TextField numEntries_txtField;

    private OrderManager orderManager;

    @FXML
    void getOrdersList(ActionEvent event) {
        int page = Integer.parseInt(pageCount_txtField.getText());
        int entries = Integer.parseInt(numEntries_txtField.getText());
        ArrayList<Order> ol = orderManager.getOrdersList(entries, page);

        for (Order current: ol) {
            System.out.println(current.toString());
        }
    }

    @FXML
    void getTotalCount(ActionEvent event) throws SQLException {
        int totalCount = orderManager.getTotalSalesCount();
        System.out.println(totalCount);
    }

    @FXML
    void getTotalSales(ActionEvent event) throws SQLException {
        int totalSales = orderManager.getTotalSalesPrice();
        System.out.println(totalSales);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderManager = new OrderManager();
    }

    public void getCurrentOrderDetails(ActionEvent actionEvent) {
        ArrayList<OrderDetailEntry> ol_d = orderManager.getOrderDetails(Integer.parseInt(orderID_txtField.getText()));

        for (OrderDetailEntry current: ol_d) {
            System.out.println(current.toString());
        }
    }
}
