package fct.cs.Bill;
import fct.cs.controllers.mainPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField ;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCustomerController implements Initializable {


    @FXML
    private TableView<BillCustomer> customerTable;

    @FXML
    private TableColumn <BillCustomer,Integer>id;

    @FXML
    private TableColumn <BillCustomer,String> name;

    @FXML
    private TableColumn <BillCustomer,String> mobile;

    @FXML
    private TableColumn <BillCustomer,String>  email;
    @FXML
    private Label screen;

    private BillingController BillingController;
    private mainPageController mainController;

    private ObservableList<BillCustomer> customerObservableList = FXCollections.observableArrayList();

    private CustomerManager CustomerManager;
    private SelectCustomerController thisController = this;
    private BillingController parentController;
    private FilteredList<BillCustomer> customerFilteredList = new FilteredList<>(customerObservableList);

    @FXML
    private TextField searchCustomer;


    @FXML
    private TableColumn SelectCustomer;

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL location , ResourceBundle resources) {

        CustomerManager = new CustomerManager();
        setColumns();
        loadDataTable();

    }
    public void loadDataTable() {
        ArrayList<BillCustomer> CustomerList = CustomerManager.getCustomerList();
        customerObservableList.clear();

        for (BillCustomer currentCustomer : CustomerList) {
            customerObservableList.add(currentCustomer);
        }

        customerTable.setItems(customerObservableList);
    }




    private void setColumns(){

        id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));


        SelectCustomer.setCellFactory((tableColumn) -> {
            TableCell<BillCustomer, Integer> tableCell = new TableCell<>() {
                javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/book.png"));
                final javafx.scene.control.Button btnSelect = new Button();
//                FontIcon icon3 = new FontIcon("antf-book");


                @Override
                protected void updateItem(Integer customer_id, boolean empty) {
                    super.updateItem(customer_id, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{

                        btnSelect.setOnAction(e ->{
                                    System.out.println("Clicked Select");
                                    BillCustomer entry = getTableView().getItems().get(getIndex());
                                    parentController.displayCustomerDetails(entry);
                                    parentController.MovetoBooks();
                        });


                        btnSelect.setStyle("-fx-background-color: \n" +
                                "        #090a0c,\n" +
                                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                                "        linear-gradient(#20262b, #191d22),\n" +
                                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                                "    -fx-background-radius: 5,4,3,5;\n" +
                                "    -fx-background-insets: 0,1,2,0;\n" +
                                "    -fx-text-fill: white;\n" +
                                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                                "    -fx-font-family: \"Arial\";\n" +
                                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                                "    -fx-font-size: 12px;\n" +
                                "    -fx-padding: 10 20 10 20;");

                        btnSelect.setText("Select");
//                        icon3.setIconColor(Color.RED);
//                        icon3.setIconSize(30);

//                        ImageView iv = new ImageView();
//                        iv.setImage(imgSelect);
//                        iv.setPreserveRatio(true);
//                        iv.setSmooth(true);
//                        iv.setCache(true);
//                        btnSelect.setGraphic(icon3);

                        this.setGraphic(btnSelect);
                        this.setAlignment(Pos.CENTER);


                    }


                }

            };
            return tableCell;
        });


    }
    public void setParentController(BillingController parentController) {
        this.parentController = parentController;
    }




    @FXML
    private void sendToBillingScene(String name) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/billing/selectItemsForBill.fxml"));
        Parent root = loader.load();
        BillingController = loader.getController();


    }

    public void searchTable(String key) {
        System.out.println("Searching ...");
        customerFilteredList.setPredicate(billCustomer-> {
            String filter = key.toLowerCase();

            if(CustomerManager.StringOrNot( filter)){
                boolean id_matches = String.valueOf(billCustomer.getCustomer_id()).toLowerCase().contains(filter);
                return id_matches;
            }else{
                boolean title_matches = String.valueOf(billCustomer.getCustomer_name()).toLowerCase().contains(filter);
                return title_matches ;
            }

        });
    }


    @FXML
    void searchCustomer(ActionEvent actionEvent) {
        String key = searchCustomer.getText();
        searchTable(key);
        customerTable.setItems(customerFilteredList);
    }
//
//    public void switchBilling(ActionEvent action ) throws IOException {
//    }

    public void addCustomer(ActionEvent action){
        parentController.newCustomer();


    }

//    public void getCustomerData() {
//        ArrayList<BillCustomer> cList = CustomerManager.getCustomerList(100, 1);
//        customerObservableList.clear();
//        for (BillCustomer c :
//                cList) {
//            customerObservableList.add(c);
//        }
//        customerTable.setItems(customerObservableList);
//    }
}


