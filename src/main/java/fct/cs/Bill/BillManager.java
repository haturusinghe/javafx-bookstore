package fct.cs.Bill;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.data.Customer;

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


    private ResultSet getCustomerFromDatabase(int viewCustomer) {
        
        String query = "SELECT * FROM customer Order customer_id DESC LIMIT ?  ";
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

