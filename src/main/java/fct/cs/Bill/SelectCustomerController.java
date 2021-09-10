package fct.cs.Bill;
import fct.cs.Bill.BillManager;
import fct.cs.data.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fct.cs.Bill.BillCustomer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectCustomerController implements Initializable {

    @FXML
    private TableView<BillCustomer> customerTable;

    @FXML
    private TableColumn<BillCustomer, Integer> id;

    @FXML
    private TableColumn<BillCustomer, String> name;

    @FXML
    private TableColumn<BillCustomer, String> mobile;

    @FXML
    private TableColumn<BillCustomer, String> email;
    @FXML
    private Label screen;

    private BillingController BillingController;

    private ObservableList<BillCustomer> customerObservableList = FXCollections.observableArrayList();

    private BillManager BillManager;
    @Override
    public void initialize(URL location , ResourceBundle resources) {

        BillManager = new BillManager();
        setColumns();
        loadDataTable();
    }
    public void loadDataTable(){
        ArrayList<BillCustomer> CustomerList = BillManager.getCustomerList(2);
        customerTable.setItems(customerObservableList);
        customerObservableList.clear();

        for (BillCustomer currentCustomer : CustomerList) {
            customerObservableList.add(currentCustomer);
        }
    }
    private void setColumns(){

        id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    @FXML
    private void displayCustomerInBill() throws IOException {
        BillCustomer customer = customerTable.getSelectionModel().getSelectedItem();
        if(customer == null){
            screen.setText("Select Customer");
        }
        else{
            String name = customer.getCustomer_name();

            sendToBillingScene(name);
            
        }
    }

    @FXML
    private void sendToBillingScene(String name) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Billing.fxml"));


        BillingController.displayCustomerName(name);
    }
}

