package fct.cs.NewEmployee;

import fct.cs.Login.PasswordSecure;
import fct.cs.dbUtil.DatabaseHandler;
import fct.cs.commonUtil.NotificationCreator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
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
        String query = "SELECT employee.*,login.* FROM employee,login where employee.employee_id = login.emp_id;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

//            preparedStatement.setInt(1, entriesPerPage);
//            preparedStatement.setInt(2, offset);
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
                        String.valueOf(rs.getInt("emp_id")),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("telnum"),
                        rs.getInt("salary"),
                        rs.getString("location"),
                        rs.getBoolean("isManager")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public static boolean updateEmployee(EmployeeData entry) {
        String updateQuery = "UPDATE employee set salary = ? where employee_id = ?";
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
            preparedStatement.setInt(1, (entry.getSalary()));
            preparedStatement.setInt(2, Integer.parseInt(entry.getEmployee_id()));
            count = preparedStatement.executeUpdate();
            NotificationCreator.showSuccessBottomRight("Operation Successfully Completed","Employee Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Updating Employee",e.getMessage());
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
            NotificationCreator.showSuccessBottomRight("Operation Successfully Completed","Employee Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Deleting Employee",e.getMessage());
        }

        return count > 0;
    }

    public static boolean addSingleEmployee(EmployeeData entry, String passGet, String question, String answer){
        int count = 0;
        PasswordSecure encrypt = new PasswordSecure();
        String passwordEncrypt = null, answerEncrypt = null;

        try {
            passwordEncrypt = PasswordSecure.encryptString(passGet);
            System.out.println("Password Encrypted");
            answerEncrypt = PasswordSecure.encryptString(answer);
            System.out.println("Answer Encrypted");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        try {

            String emp_sql = "INSERT INTO employee (gender,location,fname,lname) values(?,?,?,?)";
            PreparedStatement employeeStatement = conn.prepareStatement(emp_sql, Statement.RETURN_GENERATED_KEYS);

            employeeStatement.setString(1, entry.getGender());
            employeeStatement.setString(2, entry.getLocation());
            employeeStatement.setString(3, entry.getFirst_name());
            employeeStatement.setString(4, entry.getLast_name());

            employeeStatement.executeUpdate();
            ResultSet keys = employeeStatement.getGeneratedKeys();
            int last_inserted_id = 0;
            if (keys.next()) {
                last_inserted_id = keys.getInt(1);
            }

            String sql = "INSERT INTO login (telnum, email, password, ques, ans,emp_id) values(?,?,?,?,?,?)";
            PreparedStatement ps_3 = conn.prepareStatement(sql);


            ps_3.setString(1, entry.getPhone_number());
            ps_3.setString(2, entry.getEmail());
            ps_3.setString(3, passwordEncrypt);
            ps_3.setString(4, question);
            ps_3.setString(5, answerEncrypt);
            ps_3.setInt(6, last_inserted_id);

            ps_3.execute();

            NotificationCreator.showSuccessBottomRight("Success", "New Employee Account Created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count > 0;
    }

    /*public static boolean addSingleEmployee(EmployeeData entry){
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
            NotificationCreator.showSuccessBottomRight("Operation Successfully Completed","Employee Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Adding New Employee",e.getMessage());
        }
        return count > 0;
    }*/


}
