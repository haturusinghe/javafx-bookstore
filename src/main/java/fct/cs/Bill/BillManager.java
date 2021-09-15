package fct.cs.Bill;

import fct.cs.data.Order;
import fct.cs.data.OrderDetailEntry;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;
import fct.cs.Bill.orderDetails ;

import java.sql.*;
import java.util.ArrayList;

public class BillManager {
    private DatabaseConnector databaseConnector;
    private Connection conn;

    public BillManager() {

        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();
    }
    public boolean updateOrderEntry(Order entry){
        String addQuery = "insert into orders(   customer_id , employee_id,order_date,total_quantity, total_price, total_discount ) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getCustomer_id());
            preparedStatement.setInt(2,entry.getEmployee_id());
            preparedStatement.setDate(3, (Date) entry.getOrder_date());
            preparedStatement.setInt(4,entry.getTotal_quantity());
            preparedStatement.setInt(5,entry.getTotal_price());
            preparedStatement.setInt(6,entry.getTotal_discount());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public boolean updateOrderDetailsEntry(orderDetails entry){
        String addQuery = "insert into order_details (order_details_id ,  order_id, book_id, quantity, unit_price,price ) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getOrder_detailedID());
            preparedStatement.setInt(2,entry.getOrder_id());
            preparedStatement.setInt(3,entry.getBook_id());
            preparedStatement.setInt(4,entry.getQuantity());
            preparedStatement.setInt(5,entry.getUnit_price());
            preparedStatement.setInt(6,entry.getTotalForItem());


            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }
    public ArrayList<orderDetails> getOrderDetailsEntryFromBill(ArrayList<Billdetails> array){
        ArrayList<orderDetails> OrderDetailsList = new ArrayList<>();
        for(Billdetails currentItem : array){

            OrderDetailsList.add(new orderDetails(currentItem.getOrder_id(),
                    currentItem.getBook_id(),
                    getLastOrderId(),
                    currentItem.getQuantity(),
                    currentItem.getUnit_price()));


//          int price   = currentItem.getTotalForItem();

        }

        return OrderDetailsList ;

    }
    public void updateOrderDetailsByArray(ArrayList<orderDetails> OrderDetailsList){

        for(orderDetails currentItem:OrderDetailsList ){
            updateOrderDetailsEntry(currentItem);

        }


    }

       public int getLastOrderId() {
            String query = "SELECT order_id FROM Order order by order_id desc limit 1";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet;
            try {
                preparedStatement = conn.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                return resultSet.getInt("order_id");
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }

    public boolean updateQuantity(Billdetails entry){
        String updateQuery = "UPDATE inventory set qty = ? - qty,  where book_id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1,entry.getQuantity());
            preparedStatement.setInt(2,entry.getBook_id());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    int getQtySingleItem(int bookId) {
        String query = "SELECT qty FROM Inventory where book_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        int qty = 0 ;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, bookId);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                qty = resultSet.getInt("qty");
            }
            return qty ;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    }


