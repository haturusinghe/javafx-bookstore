package fct.cs.Bill;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.data.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public CustomerManager() {
        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }

    public ResultSet getCustomerFromDatabase() {

        String query = "SELECT customer_id,customer_name,mobile,email FROM customer Order by customer_id DESC   ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

//            preparedStatement.setInt(1, viewCustomer);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private ArrayList<BillCustomer> createCustomerList(ResultSet rs) {
        ArrayList<BillCustomer> CustomerList = new ArrayList<>();
        try {
            while (rs.next()) {
                CustomerList.add(new BillCustomer(
                        rs.getInt("customer_id") ,
                        rs.getString("customer_name"),
                        rs.getString("mobile") ,
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CustomerList;
    }
    public ArrayList<BillCustomer> getCustomerList() {
        ResultSet rs = getCustomerFromDatabase();
        return createCustomerList(rs);
    }



}


