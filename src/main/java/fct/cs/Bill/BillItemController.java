package fct.cs.Bill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class BillItemController {

    @FXML
    private TableView<BillItem> itemTable;
    @FXML
    private TableColumn<BillItem, Integer> itemId;

    @FXML
    private TableColumn<BillItem, String> itemName;

    @FXML
    private TableColumn<BillItem, String>  isbn;

    @FXML
    private TableColumn<BillItem, String>  unit_price;

    @FXML
    private Label name;

    @FXML
    private TableColumn additem;


    private ObservableList<BillItem> customerObservableList = FXCollections.observableArrayList();
    private BillManager BillManager;
    private BillItemController thisController = this;

    @Override
    public void initialize(URL location , ResourceBundle resources) {

        BillManager = new BillManager();
        setColumns();
        loadDataItemTable();


    }





}
