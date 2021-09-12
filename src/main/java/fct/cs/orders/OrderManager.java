package fct.cs.orders;

import fct.cs.data.Order;
import fct.cs.data.OrderDetailEntry;
import fct.cs.dbUtil.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;

public class OrderManager {

    private Connection conn;
    public OrderManager() {

        try {
            this.conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalSalesPrice() throws SQLException {
        String sumQuery = "SELECT SUM(total_price) as sum_sales FROM orders";
        int totalSales = -1;
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sumQuery);
        while (resultSet.next()){
            totalSales = resultSet.getInt("sum_sales");
        }
        return totalSales;
    }

    public int getTotalSalesCount() throws SQLException {
        String sumQuery = "SELECT SUM(total_quantity) as sum_sales FROM orders";
        int totalCount = -1;
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sumQuery);
        while (resultSet.next()){
            totalCount = resultSet.getInt("sum_sales");
        }
        return totalCount;
    }

    public ArrayList<OrderDetailEntry> getOrderDetails(int orderId){
        ResultSet rs = getOrderDetailsFromDatabase(orderId);
        ArrayList<OrderDetailEntry> orderDetails = new ArrayList<>();

        try {
            //order_detail_id, book_id, order_id, quantity, unit_price, discount
            while (rs.next()){
                orderDetails.add(new OrderDetailEntry(
                        rs.getInt("order_id"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("unit_price"),
                        rs.getInt("discount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    private ResultSet getOrderDetailsFromDatabase(int orderId){
        String query = "SELECT * FROM order_details WHERE order_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, orderId);
            resultSet= preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Order> getOrdersList(int entriesPerPage, int pageNumber){
        ResultSet rs = getOrdersFromDatabase(entriesPerPage, pageNumber);
        ArrayList<Order> orderList = new ArrayList<>();

        try {
            //order_id, customer_id, employee_id, order_date, total_quantity, total_price, total_discount
            while (rs.next()){
                orderList.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("order_date"),
                        rs.getInt("total_quantity"),
                        rs.getInt("total_price"),
                        rs.getInt("total_discount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }



    private ResultSet getOrdersFromDatabase(int entriesPerPage, int pageNumber)  {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM orders LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet= preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
