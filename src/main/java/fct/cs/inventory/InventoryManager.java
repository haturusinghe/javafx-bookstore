package fct.cs.inventory;

import fct.cs.Author.Author;
import fct.cs.Books.Book;
import fct.cs.dbUtil.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class InventoryManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public InventoryManager() {
        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }

    public int getTotalCountInStock() throws SQLException {
        String sumQuery = "SELECT SUM(qty) as sum_qty FROM inventory";
        int totalQty = -1;
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sumQuery);
        while (resultSet.next()) {
            totalQty = resultSet.getInt("sum_qty");
        }
        return totalQty;
    }

    public ArrayList<String> getBookIdsForCategory(int categoryId) {
        String query = "SELECT book_id FROM book where category_id = ?";
        PreparedStatement preparedStatement = null;
        ArrayList<String> idList = new ArrayList<>();
        ResultSet resultSet;
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idList.add(resultSet.getString("book_id"));
            }

            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCategoryName(int categoryId) {
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

    public Author getAuthor(int authorId) {
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

    private ArrayList<StockEntry> createOrderList(ResultSet rs) {
        ArrayList<StockEntry> orderList = new ArrayList<>();
        try {
            //inv_id, book_id, list_price, qty, min_qty
            while (rs.next()) {
                orderList.add(new StockEntry(
                        rs.getInt("book_id"),
                        rs.getInt("list_price"),
                        rs.getInt("qty"),
                        rs.getInt("min_qty")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public ArrayList<StockEntry> getStockItemList(int entriesPerPage, int pageNumber) {
        ResultSet rs = getInventoryFromDatabase(entriesPerPage, pageNumber);
        return createOrderList(rs);
    }

    private ResultSet getInventoryFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT * FROM inventory LIMIT ?  OFFSET  ?";
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

    public boolean updateSingleEntry(StockEntry entry){
        String updateQuery = "UPDATE inventory set list_price = ?, qty = ?, min_qty = ? where book_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1,entry.getList_price());
            preparedStatement.setInt(2,entry.getQty());
            preparedStatement.setInt(3,entry.getMin_qty());
            preparedStatement.setInt(4,entry.getBook_id());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public boolean deleteSingleEntry(StockEntry entry){
        String updateQuery = "DELETE FROM INVENTORY where book_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1,entry.getBook_id());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public boolean addSingleEntry(StockEntry entry){
        String addQuery = "insert into INVENTORY (book_id, list_price, qty, min_qty) values (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getBook_id());
            preparedStatement.setInt(2,entry.getList_price());
            preparedStatement.setInt(3,entry.getQty());
            preparedStatement.setInt(4,entry.getMin_qty());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public Book getBookDetails(int bookId) {
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

    private ResultSet getBookDetailsFromDatabase(int bookId) {
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

}
