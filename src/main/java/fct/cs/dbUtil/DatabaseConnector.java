package fct.cs.dbUtil;

import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseConnector {

    private static String USER = "root";
    private static String PASS = "";
    private static String DB_URL = "jdbc:mysql://localhost:3306/bookstore";

    private static Connection conn = null;


    static
    {
        try {
            System.out.println("connection yolo");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch (SQLException e) {
            errorAlert(e.getLocalizedMessage());
        }
    }

    /*public DatabaseConnector() {
        createConnection();
    }

    private void createConnection()  {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static Connection getConn() {
        return null;
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

    private static void errorAlert(String e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!.");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }
}
