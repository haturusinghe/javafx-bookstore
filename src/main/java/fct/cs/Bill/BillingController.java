package fct.cs.Bill;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;


public class BillingController {


    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private Label customerName;

    @FXML
    private Label  total;

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

//    @FXML
//    private AnchorPane Bill;
//
//    @FXML
//    private Button search;
//
//    @FXML
//    private TextField searchText;

    @FXML
    private Button button;

//    @FXML
//    private VBox content;
//
//    @FXML
//    private Label customerName;

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

    private BillingController thisController = this;
    private String currentPage = "";
    public  static Label static_label;
    public static FXMLLoader load;

    private ObservableList<Billdetails> BillingObservableList = FXCollections.observableArrayList();
    private ArrayList<Billdetails> billDetails ;
    private BillItemController parentController;

//    private int subTotal;

    private  int orderDetailId = 1 ;
//    private ArrayList<Billdetails> BillitemsList;
    private  boolean alreadyAdded = false;

    public void initialize() {
        static_label = customerName;
        billDetails = new ArrayList<Billdetails>();
        displaySelectCustomer();
        setColumns();
        loadBillTable();

    }

    public void loadBillTable()  {

        BillingObservableList.clear();
        if(billDetails.size() > 0) {
            for (Billdetails currentItem : billDetails) {
                BillingObservableList.add(currentItem);
            }
        }
        total.setText(String.valueOf(getTotal(billDetails)));
        BillTable.setItems(BillingObservableList);
    }

    public void refreshTable(){
      BillTable.refresh();
    }

    private void setColumns() {

        bookID.setCellValueFactory(new PropertyValueFactory<Billdetails, Integer>("book_id"));
        item.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("book_name"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("unit_price"));
        qty.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("quantity"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("totalForItem"));
    }

    public void displayCustomerName(String name) {
        static_label.setText(name);
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
        BillItemController controller = loader.getController();
        controller.setParentController(this);
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
        SelectCustomerController controller = loader.getController();
        controller.setParentController(this);


        }

    public void getOrderDetails(int book_id , String book_name , int unit_price ){
        alreadyAdded  = false ;

        if(billDetails.size() > 0 ) {
            for (Billdetails currentItem : billDetails) {

                if (currentItem.getBook_id() == book_id) {
                    currentItem.setQuantity(currentItem.getQuantity() + 1);
                    currentItem.setTotalForItem(currentItem.getQuantity() * currentItem.getUnit_price());
                    alreadyAdded = true;
                    System.out.println("1");
                    break;
                }
            }
        }
        if (!alreadyAdded){

            Billdetails getOrderDetail  = new Billdetails(orderDetailId , book_id , book_name , 1,unit_price,unit_price);
            getOrderDetail.setOrder_id(orderDetailId);
            orderDetailId++ ;
            getOrderDetail.setBook_id(book_id);
            getOrderDetail.setBook_name(book_name);
            getOrderDetail.setQuantity(1);
            getOrderDetail.setUnit_price(unit_price);

            billDetails.add(getOrderDetail);

        }

    }
    public int getTotal(ArrayList<Billdetails> array){
            int subTotal = 0;
            for (Billdetails currentItem : array) {

                subTotal += currentItem.getTotalForItem();
                }
           return subTotal ;
            }





    }











