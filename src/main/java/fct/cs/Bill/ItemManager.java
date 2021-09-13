package fct.cs.Bill;

import fct.cs.dbUtil.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManager extends BillManager{

    private DatabaseConnector databaseConnector;
    private Connection conn;

    public ItemManager() {
        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }

    public ResultSet getCustomerFromDatabase(int viewCustomer) {

        String query = "SELECT book.book_id, book.title, book.isbn , inventory.list_price FROM book JOIN inventory ON book.book_id=inventory.book_id order by book_id LIMIT 8 ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, viewCustomer);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
