package fct.cs.Bill;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.data.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public BillManager() {
        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }




    
}


