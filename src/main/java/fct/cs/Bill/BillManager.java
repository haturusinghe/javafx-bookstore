package fct.cs.Bill;

import fct.cs.data.Order;
import fct.cs.data.OrderDetailEntry;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;
import fct.cs.Bill.orderDetails ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public BillManager() {

        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }

    public boolean addSingleEntry(orderDetails entry){
        String addQuery = "insert into order_details (order_id, book_id, quantity, unit_price, total_price , order_date) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getOrder_id());
            preparedStatement.setInt(2,entry.getBook_id());
            preparedStatement.setInt(3,entry.getQuantity());
            preparedStatement.setInt(4,entry.getUnit_price());
            preparedStatement.setInt(5,entry.getTotalForItem());
            preparedStatement.setDate(6,entry.getDate());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }


        private int getLastOrderId() {
            String query = "SELECT order_id FROM Order order by order_id desc limit 1";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet;
            try {
                preparedStatement = conn.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                return resultSet.getInt("order_id");
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }

    private int getQtySingleItem( int bookId) {
        String query = "SELECT qty FROM Inventory where book_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,bookId);

            resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("qty");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    }


