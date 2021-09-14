package fct.cs.Bill;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

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

    @FXML
    private TableColumn remove;

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
        moveToSelectCustomer();
        setColumns();
        loadBillTable();
        finalTotal.setText(String.valueOf(getFinalTotal(Integer.parseInt(total.getText()))));

    }

    public void loadBillTable()  {

        BillingObservableList.clear();
        if(billDetails.size() > 0) {
            for (Billdetails currentItem : billDetails) {
                BillingObservableList.add(currentItem);
            }
        }
        total.setText(String.valueOf(getTotal(billDetails)));
       finalTotal.setText(String.valueOf(getFinalTotal(Integer.parseInt(total.getText()))));
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

        remove.setCellFactory((tableColumn) -> {
            TableCell<Billdetails, Integer> tableCell = new TableCell<>() {
                javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/remove.png"));
                final javafx.scene.control.Button btnRemove = new Button();
                FontIcon icon3 = new FontIcon("antf-close-square");


                @Override
                protected void updateItem(Integer customer_id, boolean empty) {
                    super.updateItem(customer_id, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{

                        btnRemove.setOnAction(e ->{
                            System.out.println("Clicked Remove");
                            Billdetails entry = getTableView().getItems().get(getIndex());
                            getTableView().getSelectionModel().select(getIndex());
                            System.out.println(getTableView().getSelectionModel().getSelectedItem().toString());

                            for ( Billdetails currentItem: billDetails){
                                if(entry.getBook_id() == currentItem.getBook_id()){

                                    billDetails.remove(currentItem);
                                }
                            }

                            loadBillTable();

                        });


                        btnRemove.setStyle("-fx-background-color: transparent;");
                        icon3.setIconColor(Color.BLACK);
                        icon3.setIconSize(30);

                        ImageView iv = new ImageView();
                        iv.setImage(imgSelect);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnRemove.setGraphic(icon3);

                        this.setGraphic(btnRemove);
                        this.setAlignment(Pos.CENTER);


                    }


                }

            };
            return tableCell;
        });



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

    public void moveToSelectCustomer(){
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
    public int getTotal(ArrayList<Billdetails> array) {
        int subTotal = 0;
        for (Billdetails currentItem : array) {

            subTotal += currentItem.getTotalForItem();
        }
        return subTotal;
    }
    public int getFinalTotal(int total){
        int disc = Integer.parseInt(discount.getText()) ;

        return (total - (total*disc)/100 ) ;


    }


public void addDiscount(ActionEvent action){

    finalTotal.setText(String.valueOf(getFinalTotal(Integer.parseInt(total.getText()))));

}

public void cancelOrder(ActionEvent action){
    billDetails.clear();
    loadBillTable();
    moveToSelectCustomer();

}



    }












