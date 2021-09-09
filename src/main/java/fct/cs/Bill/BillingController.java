package fct.cs.Bill;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;


public class BillingController {


    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private Label customer;

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


}
