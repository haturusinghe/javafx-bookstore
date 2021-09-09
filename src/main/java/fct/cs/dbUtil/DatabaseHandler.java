package fct.cs.dbUtil;

import java.sql.*;

public class DatabaseHandler {
    private static String USER = "root";
    private static String PASS = "";
    private static String DB_URL = "jdbc:mysql://localhost:3306/bookstore";

    private static DatabaseHandler instance;

    private Connection conn;
    private Statement stmt;

    private DatabaseHandler() throws SQLException {
        createConnection();
    }

    public static DatabaseHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseHandler();
        } else if (instance.getConn().isClosed()) {
            instance = new DatabaseHandler();
        }

        return instance;
    }

    public void createConnection()  {
        try {
            this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection Created xoxo");
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
