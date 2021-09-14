package fct.cs.Bill;

import fct.cs.data.Order;
import fct.cs.data.OrderDetailEntry;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public BillManager() {

        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }

    public boolean addSingleEntry(OrderDetailEntry entry){
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
            preparedStatement.setInt(5,entry.getTotalForItem());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

}
