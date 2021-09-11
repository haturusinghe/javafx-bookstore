package fct.cs.Bill;
import fct.cs.Bill.BillManager;
import fct.cs.controllers.mainPageController;
import fct.cs.data.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fct.cs.Bill.BillCustomer;
import javafx.stage.Stage;
import net.sf.jasperreports.web.actions.Action;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectCustomerController extends BillingController implements Initializable {

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
    private mainPageController mainController;

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
            screen.setText("name:" + name);
            displayCustomerName(name);
        }
    }
    @FXML
    private void sendToBillingScene(String name) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/selectItemsForBill.fxml"));
        Parent root = loader.load();
        BillingController = loader.getController();



    }

    public void displayCustomerName(String name){
        static_label.setText(name);
        System.out.printf(name);

    }



    public void switchBilling(ActionEvent action ) throws IOException {


    }
}

