package fct.cs.NewEmployee;

import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<EmployeeData> getEmployeeList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getEmployeesFromDatabase(entriesPerPage, pageNumber);
        return createEmployeeList(rs);
    }

    private static ResultSet getEmployeesFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM employee LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<EmployeeData> createEmployeeList(ResultSet rs) {
        ArrayList<EmployeeData> orderList = new ArrayList<>();
        try {
            //
            //employee_id
            //first_name
            //last_name
            //email
            //gender
            //phone_number
            //salary
            while (rs.next()) {
                orderList.add(new EmployeeData(
                        String.valueOf(rs.getInt("employee_id")),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getInt("salary")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public static boolean updateEmployee(EmployeeData entry) {
        String updateQuery = "UPDATE employee set first_name = ?, last_name = ?, email = ?,gender = ?,phone_number = ?,salary = ? where employee_id = ?";
        //first_name
        //last_name
        //email
        //gender
        //phone_number
        //salary
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setString(1,entry.getFirst_name());
            preparedStatement.setString(2,entry.getLast_name());
            preparedStatement.setString(3,entry.getLast_name());
            preparedStatement.setString(3,entry.getEmail());
            preparedStatement.setString(4,entry.getGender());
            preparedStatement.setString(5,entry.getPhone_number());
            preparedStatement.setInt(6,entry.getSalary());
            preparedStatement.setInt(7, Integer.parseInt(entry.getEmployee_id()));
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public static boolean deleteSingleEmployee(EmployeeData entry){
        String updateQuery = "DELETE FROM employee where employee_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1, Integer.parseInt(entry.getEmployee_id()));
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public static boolean addSingleEmployee(EmployeeData entry){
        String addQuery = "insert into employee (employee_id, first_name, last_name, email, gender, phone_number, salary) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1, Integer.parseInt(entry.getEmployee_id()));
            preparedStatement.setString(2,entry.getFirst_name());
            preparedStatement.setString(3,entry.getLast_name());
            preparedStatement.setString(4,entry.getEmail());
            preparedStatement.setString(5,entry.getGender());
            preparedStatement.setString(6,entry.getPhone_number());
            preparedStatement.setInt(7,entry.getSalary());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }


}
