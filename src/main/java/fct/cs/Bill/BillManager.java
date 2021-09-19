package fct.cs.Bill;

import fct.cs.data.Order;
import fct.cs.dbUtil.DatabaseHandler;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillManager {
    private Connection conn;

    public BillManager() {

        try {
            this.conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean updateOrderEntry(Order entry){
        String addQuery = "insert into orders(customer_id , employee_id,order_date,total_quantity, total_price, total_discount,discount_perc ) values (?,?,?,?,?,?,?)";
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
            preparedStatement.setInt(7,entry.getDiscount_perc());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

    public boolean updateOrderDetailsEntry(orderDetails entry){
        String addQuery = "insert into order_details (order_detail_id ,order_id, book_id, quantity, unit_price) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(addQuery);
            preparedStatement.setInt(1,entry.getOrder_detailedID());
            preparedStatement.setInt(2,entry.getOrder_id());
            preparedStatement.setInt(3,entry.getBook_id());
            preparedStatement.setInt(4,entry.getQuantity());
            preparedStatement.setInt(5,entry.getUnit_price());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }
    public ArrayList<orderDetails> getOrderDetailsEntryArray(ArrayList<Billdetails> array){
        ArrayList<orderDetails> OrderDetailsList = new ArrayList<>();
        int lastOrder = getLastOrderId();
        for(Billdetails currentItem : array){

            OrderDetailsList.add(new orderDetails(
                    currentItem.getOrder_id(),
                    lastOrder ,
                    currentItem.getBook_id(),
                    currentItem.getQuantity(),
                    currentItem.getUnit_price()));


//          int price   = currentItem.getTotalForItem();
        }

        return OrderDetailsList ;

    }
    public void updateOrderDetailsByArray(ArrayList<Billdetails> billDetailsList){
        ArrayList<orderDetails>  array = new ArrayList<>();
       array = getOrderDetailsEntryArray(billDetailsList);

        for(orderDetails currentItem:array ){
            updateQuantity(currentItem);
            updateOrderDetailsEntry(currentItem);

        }

        System.out.println(array);

    }

    public int getLastOrderId() {
            String query = "SELECT order_id FROM orders order by order_id desc limit 1";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet;
           int lastOrder = 0 ;
            try {
                preparedStatement = conn.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                  lastOrder = resultSet.getInt("order_id");
                    System.out.println(lastOrder);
                }
                return lastOrder;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }

    public boolean updateQuantity(orderDetails entry){
        String updateQuery = "UPDATE inventory set qty = qty - ?   where book_id = ?";
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


    public void jasperInvoice(){
        JasperPrint jp ;
        Map param = new HashMap();

        try{
            jp = JasperFillManager.fillReport("jasper_files/invoice.jasper" ,
                    param,
                    conn);

            JasperViewer viewer = new JasperViewer(jp,false);

            viewer.setTitle("Report");
//            JasperExportManager.exportReportToPdfFile(jp, "jasper_files/invoice.pdf");
            viewer.setSize(600 , 800);
            viewer.setZoomRatio(0.5F);

            viewer.setVisible(true);


        }catch(JRException e){
            System.out.println(e.getMessage());
        }


        // Creation of the HTML Jasper Reports


    }
    }


