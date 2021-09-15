package fct.cs.NewCustomer;

import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException c) {
            c.printStackTrace();
        }
    }

    public static ArrayList<CustomerData> getCustomerList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getCustomerFromDatabase(entriesPerPage, pageNumber);
        return createCustomerList(rs);
    }

    private static ResultSet getCustomerFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM customer LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException c) {
            c.printStackTrace();
            return null;
        }
    }

    private static ArrayList<CustomerData> createCustomerList(ResultSet rs) {
        ArrayList<CustomerData> orderList = new ArrayList<>();
        try {
            //customer_id, customer_name ,location , mobile, email
            while (rs.next()) {
                orderList.add(new CustomerData(
                        String.valueOf(rs.getInt("customer_id")),
                        rs.getString("customer_name"),
                        rs.getString("location"),
                        rs.getString("mobile"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException c) {
            c.printStackTrace();
        }

        return orderList;
    }

    public static boolean updateCustomer(CustomerData entry) {
        String updateQuery = "UPDATE customer set customer_name = ?, location = ?, mobile = ?,email = ? = ? where customer_id = ?";
        //customer_id, customer_name ,location , mobile, email
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setString(1,entry.getcustomer_name());
            preparedStatement.setString(2,entry.getlocation());
            preparedStatement.setString(3,entry.getmobile());
            preparedStatement.setString(4,entry.getemail());
            preparedStatement.setInt(5, Integer.parseInt(entry.getcustomer_id()));
            count = preparedStatement.executeUpdate();
        } catch (SQLException c) {
            c.printStackTrace();
        }

        return count > 0;
    }

    public static boolean deleteSingleCustomer(CustomerData entry){
        String updateQuery = "DELETE FROM customer where customer_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1, Integer.parseInt(entry.getcustomer_id()));
            count = preparedStatement.executeUpdate();
        } catch (SQLException c) {
            c.printStackTrace();
        }

        return count > 0;
    }

    public static boolean addSingleCustomer(CustomerData entry){
        String addQuery = "insert into customer (customer_id, customer_name, location, mobile, email) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1, Integer.parseInt(entry.getcustomer_id()));
            preparedStatement.setString(2,entry.getcustomer_name());
            preparedStatement.setString(3,entry.getlocation());
            preparedStatement.setString(4,entry.getmobile());
            preparedStatement.setString(5,entry.getemail());
            count = preparedStatement.executeUpdate();
        } catch (SQLException c) {
            c.printStackTrace();
        }
        return count > 0;
    }
}
