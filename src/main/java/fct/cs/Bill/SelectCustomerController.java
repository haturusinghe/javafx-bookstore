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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fct.cs.Bill.BillCustomer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.sf.jasperreports.web.actions.Action;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
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

    private BillManager BillManager;
    private SelectCustomerController thisController = this;


    @FXML
    private TableColumn SelectCustomer;

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL location , ResourceBundle resources) {

        BillManager = new BillManager();
        setColumns();
        loadDataTable();


    }
    public void loadDataTable() {
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


        SelectCustomer.setCellFactory((tableColumn) -> {
            TableCell<BillCustomer, Integer> tableCell = new TableCell<>() {
                javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/book.png"));
                final javafx.scene.control.Button btnSelect = new Button();
                FontIcon icon3 = new FontIcon("antf-book");


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
                                    displayCustomerName(entry.getCustomer_name());

                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/selectItemsForBill.fxml"));

                            try {
                                rootPane.getChildren().clear();
                                rootPane.getChildren().add(loader.load());

                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }


                        });


                        btnSelect.setStyle("-fx-background-color: transparent;");
                        icon3.setIconColor(Color.RED);
                        icon3.setIconSize(30);

                        ImageView iv = new ImageView();
                        iv.setImage(imgSelect);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnSelect.setGraphic(icon3);

                        this.setGraphic(btnSelect);
                        this.setAlignment(Pos.CENTER);


                    }


                }

            };
            return tableCell;
        });


    }


    @FXML
    private void displayCustomerInBill() throws IOException {
        BillCustomer customer = customerTable.getSelectionModel().getSelectedItem();
        if (customer == null) {
            screen.setText("Select Customer");
        } else {
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

