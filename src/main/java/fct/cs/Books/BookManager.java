package fct.cs.Books;


import fct.cs.Author.Author;
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

    public static Book getBookDetails(int bookId) {
        ResultSet rs = getBookDetailsFromDatabase(bookId);
        Book bookInfo = null;

        try {
            //book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                int author_id = rs.getInt("author_id");
                bookInfo = new Book(
                        rs.getInt("book_id"),
                        rs.getString("isbn"),
                        category_id,
                        rs.getString("publisher"),
                        author_id,
                        rs.getString("title"),
                        rs.getShort("b_year"),
                        rs.getInt("mrp"),
                        rs.getInt("num_pages"),
                        rs.getString("lang"),
                        rs.getString("book_description"),
                        getAuthor(author_id),
                        getCategoryName(category_id)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookInfo;
    }

    private static ResultSet getBookDetailsFromDatabase(int bookId) {
        String query = "SELECT * FROM book WHERE book_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCategoryName(int categoryId) {
        String query = "SELECT category_name FROM book_category where category_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getString("category_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return "";
    }

    public static Author getAuthor(int authorId) {
        String query = "SELECT * FROM author where author_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, authorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new Author(
                        resultSet.getInt("author_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("website"),
                        resultSet.getString("gender")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
