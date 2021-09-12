package fct.cs.Bill;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class CustomerBoxController {

        @FXML
        private Label id;

        @FXML
        private Label name;

        @FXML
        private Label mobile;

        @FXML
        private Label email;


    public void setData(BillCustomer Customer){

        id.setText(Integer.toString(Customer.getCustomer_id()));
        name.setText(Customer.getCustomer_name());
        mobile.setText(Customer.getMobile());
        email.setText(Customer.getEmail());
    }



}

