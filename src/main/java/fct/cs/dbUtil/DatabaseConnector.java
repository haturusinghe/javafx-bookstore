package fct.cs.dbUtil;

import java.sql.*;

public class DatabaseConnector {

    private static String USER = "root";
    private static String PASS = "";
    private static String DB_URL = "jdbc:mysql://localhost:3306/bookstore";

    private Connection conn;

    public DatabaseConnector() {
        createConnection();
    }

    private void createConnection()  {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }


    public void closeConnection(){
        try {
            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException e) {
            System.out.println("Error Closing");
            e.printStackTrace();
        }
    }
}
