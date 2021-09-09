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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectCustomerController implements Initializable {

    @FXML
    private TableView<BillCustomer> customerTable;

    @FXML
    private TableColumn<BillCustomer,String> c1;

    @FXML
    private TableColumn<?, ?> c2;

    ObservableList<BillCustomer> customerObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location , ResourceBundle resources){

            BillManager customer = new BillManager();
            ResultSet rs = customer.getCustomerFromDatabase(2);
            try {
                while (rs.next()) {
                    customerObservableList.add(new BillCustomer(rs.getString("customer_name")));
                }

                c1.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            }catch (SQLException e) {
                e.printStackTrace();
            }
        customerTable.setItems(customerObservableList);
    }

}
