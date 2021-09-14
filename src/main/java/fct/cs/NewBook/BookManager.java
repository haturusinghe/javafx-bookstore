package fct.cs.NewBook;

import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookManager {
    private static Connection conn;

    static {
        try {
            conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<BookData> getEmployeeList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getEmployeesFromDatabase(entriesPerPage, pageNumber);
        return createBookList(rs);
    }


    private static ResultSet getEmployeesFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM book LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }


    private static ArrayList<BookData> createBookList(ResultSet rs) {
        ArrayList<BookData> bbbList = new ArrayList<>();
        try {
            while (rs.next()) {
                bbbList.add(new BookData(
                        String.valueOf(rs.getInt("book_id")),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getString("lang"),
                        rs.getString("book_description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bbbList;
    }

    public static boolean deleteSingleBook(BookData entry){
        String updateQuery = "DELETE FROM book where book_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1, Integer.parseInt(entry.getBook_id()));
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }


}
