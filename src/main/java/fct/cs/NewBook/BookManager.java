package fct.cs.NewBook;

import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class BookManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
