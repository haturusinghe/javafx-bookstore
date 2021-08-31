package fct.cs.dbUtil;

import java.sql.*;

public class DatabaseHandler {
    private static String USER = "root";
    private static String PASS = "";
    private static String DB_URL = "jdbc:mysql://localhost:3306/book_store";

    private Connection conn;
    private Statement stmt;

    public DatabaseHandler() throws SQLException {
        createConnection();
    }

    public void createConnection()  {
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

    public ResultSet excecuteQuery(String query)  {
        ResultSet resultSet;
        try {
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
