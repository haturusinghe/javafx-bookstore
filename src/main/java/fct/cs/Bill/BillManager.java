package fct.cs.Bill;
import fct.cs.dbUtil.DatabaseConnector;

import java.sql.Connection;

public class BillManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public BillManager() {
        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }

}
