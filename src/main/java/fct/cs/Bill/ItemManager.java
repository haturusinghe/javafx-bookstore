package fct.cs.Bill;

import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemManager extends CustomerManager {

    private DatabaseConnector databaseConnector;
    private Connection conn;

    public ItemManager() throws SQLException {
        this.conn = DatabaseHandler.getInstance().getConn();
    }

    public ResultSet getItemFromDatabase() {

        String query = "SELECT book.book_id, book.title, book.isbn , inventory.list_price , inventory.qty FROM book JOIN inventory ON book.book_id=inventory.book_id order by book_id  ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);
//
//            preparedStatement.setInt(1, viewitem);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private ArrayList<BillItem> createItemList(ResultSet rs) {
        ArrayList<BillItem> ItemList = new ArrayList<>();
        try {
            while (rs.next()) {
                ItemList.add(new BillItem(
                        rs.getInt("book_id") ,
                        rs.getString("title"),
                        rs.getString("isbn") ,
                        rs.getInt("list_price"),
                        rs.getInt("qty")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ItemList;
    }

    public ArrayList<BillItem> getItemList() {
        ResultSet rs = getItemFromDatabase();
        return createItemList(rs);
    }



}
