package fct.cs.Bill;
import fct.cs.Bill.BillManager;
import fct.cs.data.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fct.cs.Bill.BillCustomer;

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

    ObservableList<BillCustomer> customerObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location , ResourceBundle resources){

            BillManager customer = new BillManager();

            try {
                while (rs.next()) {
                    customerObservableList.add(new BillCustomer(rs.getInt("customer_id") , rs.getString("customer_name"),
                            rs.getString("mobile") ,  rs.getString("email")));
                }
                    id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                    name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
                    mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                    email.setCellValueFactory(new PropertyValueFactory<>("email"));
            }catch (SQLException e) {
                e.printStackTrace();
            }
        customerTable.setItems(customerObservableList);
    }
    public void loadData(){
        ArrayList<BillCustomer> CustomerList = BillManager.getCustomerList(3);
        customerTable.setItems(customerObservableList);
        customerObservableList.clear();
    }
    
    public void SetColumns(){

        id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

    }
}

