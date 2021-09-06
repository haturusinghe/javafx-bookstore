package fct.cs.Books;


import fct.cs.dbUtil.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookManager {
//    private DatabaseConnector databaseConnector;
    private static Connection conn = DatabaseConnector.getConn();

    public static ArrayList<Book> getBookList(int entriesPerPage, int pageNumber){
        ResultSet rs = getBooksFromDatabase(entriesPerPage, pageNumber);
        ArrayList<Book> bookList = new ArrayList<>();
        //book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description
        try {
             while (rs.next()){
                bookList.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("isbn"),
                        rs.getInt("category_id"),
                        rs.getString("publisher"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getShort("b_year"),
                        rs.getInt("mrp"),
                        rs.getInt("num_pages"),
                        rs.getString("lang"),
                        rs.getString("book_description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }



    public static ResultSet getBooksFromDatabase(int entriesPerPage, int pageNumber)  {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM book LIMIT ?  OFFSET  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, entriesPerPage);
            preparedStatement.setInt(2, offset);
            resultSet= preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
