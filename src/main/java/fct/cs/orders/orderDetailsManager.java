package fct.cs.orders;

import fct.cs.dbUtil.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class orderDetailsManager {
        private static Connection conn;

        static {
            try {
                conn = DatabaseHandler.getInstance().getConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static ArrayList<ordersInfo> getOrderDetList(int entriesPerPage, int pageNumber) {
            ResultSet rs = getOrderDetFromDatabase(entriesPerPage, pageNumber);
            return createOrderDetList(rs);
        }

    private static ResultSet getOrderDetFromDatabase(int entriesPerPage, int pageNumber) {
        int offset = entriesPerPage * (pageNumber - 1);
        String query = "SELECT order_details.order_detail_id,order_details.book_id,order_details.order_id," +
                "order_details.quantity,order_details.unit_price,order_details.price" +
                " ,book.book.title, FROM order_details JOIN book ON book.book_id=order_details.book_id WHERE order_id = ?";
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

        private static ArrayList<ordersInfo> createOrderDetList(ResultSet rs) {
            ArrayList<ordersInfo> orderList = new ArrayList<>();
            try {
                // order_detail_id INT,
                //	book_id INT,
                //	order_id INT,
                //	quantity INT,
                //	unit_price INT,
                //	price INT,
                while (rs.next()) {
                    orderList.add(new ordersInfo(
                            String.valueOf(rs.getInt("order_detail_id")),
                            String.valueOf(rs.getInt("book_id")),
                            String.valueOf(rs.getInt("order_id")),
                            rs.getString("title"),
                            rs.getInt("quantity"),
                            rs.getInt("unit_price"),
                            rs.getInt("tprice")

                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return orderList;
        }

}
