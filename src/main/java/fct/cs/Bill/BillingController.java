package fct.cs.Bill;

import fct.cs.data.OrderDetailEntry;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BillingController {


    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private Label customerName;

    @FXML
    private TableColumn<?, ?> billTable;

    @FXML
    private Label total;

    @FXML
    private TextField discount;

    @FXML
    private Label finalTotal;

    @FXML
    private Button chargeCustomer;

    @FXML
    private Button cancel;
    @FXML
    private VBox content;
    @FXML
    private AnchorPane Bill;

    @FXML
    private AnchorPane Bill;

    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private Button button;

    @FXML
    private VBox content;

    @FXML
    private Label customerName;

    @FXML
    private TableView<Billdetails> BillTable;

    @FXML
    private TableColumn<Billdetails, Integer> bookID;

    @FXML
    private TableColumn<Billdetails, String> item;

    @FXML
    private TableColumn<Billdetails, String> unitPrice;

    @FXML
    private TableColumn<Billdetails, String> qty;

    @FXML
    private TableColumn<Billdetails, String> totalPrice;

    private FXMLLoader loader = null;


    private String currentPage = "";
    public static Label static_label;
    public static FXMLLoader load;

    private ObservableList<Billdetails> BillingObservableList = FXCollections.observableArrayList();

    private SelectCustomerController parentController;

    private static int orderDetailId ;
//    private ArrayList<Billdetails> BillitemsList;
    private  boolean alreadyAdded = false;

    public void initialize() {

        displaySelectCustomer();
        setColumns();
        loadDataTable();
    }

    public void loadDataTable() {
        BillTable.setItems(BillingObservableList);
    }

    private void setColumns() {

        bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    public void displayCustomerName(String name) {
        customerName.setText(name);
        System.out.printf(name);

    }

    public void MovetoBooks() {
        if (!currentPage.equals("selectItemsBill")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/selectItemsForBill.fxml"));
            try {
                content.getChildren().clear();
                content.getChildren().add(loader.load());
                currentPage = "selectItemsBill";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("already loaded");
        }
    }

    public void setParentController(SelectCustomerController parentController) {
        this.parentController = parentController;
    }


    public void displaySelectCustomer(){
            if (!currentPage.equals("selectCustomerBills")) {

                loader = new FXMLLoader(getClass().getResource("/fct/cs/SelectCustomerBill.fxml"));

                try {
                    content.getChildren().clear();
                    content.getChildren().add(loader.load());
                    currentPage = "selectCustomerBills";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("already loaded");
            }

            static_label = customerName;
        }

    public void getOrderDetails(int book_id , String book_name , int unit_price ){

        for (Billdetails currentItem : BillingObservableList) {

            if(currentItem.getBook_id() == book_id){
                currentItem.setQuantity(currentItem.getQuantity() + 1);
                currentItem.setTotalForItem(currentItem.getQuantity()*currentItem.getUnit_price());
                alreadyAdded = true ;
                break;

            }
        }
        if (alreadyAdded  == false){

            Billdetails getOrderDetail  = new Billdetails(orderDetailId , book_id , book_name , 1,unit_price,unit_price);
            getOrderDetail.setOrder_id(orderDetailId);
            orderDetailId++ ;
            getOrderDetail.setBook_id(book_id);
            getOrderDetail.setBook_name(book_name);
            getOrderDetail.setQuantity(1);
            getOrderDetail.setUnit_price(unit_price);

            BillingObservableList.add(getOrderDetail);

        }







    }




    }




