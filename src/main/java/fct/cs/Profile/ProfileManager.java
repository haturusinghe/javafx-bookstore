package fct.cs.Profile;

import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDetails getUserDetails(int empId) {
        String query = "SELECT employee.*,login.* FROM employee,login where employee.employee_id = login.emp_id AND emp_id = ? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        UserDetails user = null;

        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, empId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                user = new UserDetails(
                        resultSet.getInt("emp_id"),
                        resultSet.getInt("salary"),
                        resultSet.getString("fname"),
                        resultSet.getString("lname"),
                        resultSet.getString("gender"),
                        resultSet.getString("location"),
                        resultSet.getString("email"),
                        resultSet.getString("telnum")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUserDetails(UserDetails user) {
//        String query = "SELECT employee.*,login.* FROM employee,login where employee.employee_id = login.emp_id AND emp_id = ? ";
        String queryEmp = "UPDATE employee SET fname = ?, lname = ?, location = ? where employee_id = ? ";
        String queryLogin = "UPDATE login SET email = ?, telnum = ? where emp_id = ? ";

        PreparedStatement empStatement = null;
        PreparedStatement loginStatement = null;

        try {
            empStatement = conn.prepareStatement(queryEmp);

            empStatement.setString(1, user.getFname());
            empStatement.setString(2, user.getLname());
            empStatement.setString(3, user.getLocation());
            empStatement.setInt(4, user.getEmp_id());
            empStatement.executeUpdate();

            loginStatement = conn.prepareStatement(queryLogin);
            loginStatement.setString(1, user.getEmail());
            loginStatement.setString(2, user.getTelnum());
            loginStatement.setInt(3, user.getEmp_id());
            loginStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static class UserDetails {
        private int emp_id, salary;
        private String fname,  lname,  gender,  location,   email,  telnum;

        public UserDetails(int emp_id, int salary, String fname, String lname, String gender, String location, String email, String telnum) {
            this.emp_id = emp_id;
            this.salary = salary;
            this.fname = fname;
            this.lname = lname;
            this.gender = gender;
            this.location = location;
            this.email = email;
            this.telnum = telnum;
        }

        public int getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(int emp_id) {
            this.emp_id = emp_id;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelnum() {
            return telnum;
        }

        public void setTelnum(String telnum) {
            this.telnum = telnum;
        }

        @Override
        public String toString() {
            return "UserDetails{" +
                    "emp_id=" + emp_id +
                    ", salary=" + salary +
                    ", fname='" + fname + '\'' +
                    ", lname='" + lname + '\'' +
                    ", gender='" + gender + '\'' +
                    ", location='" + location + '\'' +
                    ", email='" + email + '\'' +
                    ", telnum='" + telnum + '\'' +
                    '}';
        }
    }
}

