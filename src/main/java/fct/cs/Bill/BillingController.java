package fct.cs.Bill;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


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

    private FXMLLoader loader = null;


    private String currentPage = "";
    public static Label static_label ;
    public static FXMLLoader load ;

    private SelectCustomerController parentController ;


    public void initialize(){

        if (!currentPage.equals("selectCustomerBills")) {

          loader = new FXMLLoader(getClass().getResource("/fct/cs/SelectCustomerBill.fxml"));

            try {
                content.getChildren().clear();
                content.getChildren().add(loader.load());
                currentPage = "selectCustomerBills";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("already loaded");
        }

        static_label = customerName ;
}

public void displayCustomerName(String name){
   customerName.setText(name);
    System.out.printf(name);

}

public void MovetoBooks(){
    if (!currentPage.equals("selectItemsBill")) {
        loader = new FXMLLoader(getClass().getResource("/fct/cs/selectItemsForBill.fxml"));
        try {
            content.getChildren().clear();
            content.getChildren().add(loader.load());
            currentPage = "selectItemsBill";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }else{
        System.out.println("already loaded");
    }
}

    public void setParentController(SelectCustomerController parentController) {
        this.parentController = parentController;
    }

}



