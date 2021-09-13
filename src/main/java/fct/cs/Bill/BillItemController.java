package fct.cs.Bill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
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
    private TableColumn<BillItem, Integer>  unit_price;

    @FXML
    private Label name;

    @FXML
    private TableColumn additem;

    private ItemManager ItemManager ;


    private ObservableList<BillItem> itemObservableList = FXCollections.observableArrayList();
    private BillItemController thisController = this;

    @Override
    public void initialize(URL location , ResourceBundle resources) {

        ItemManager = new ItemManager();
        setColumns();
        loadDataItemTable();

    }
    public void loadDataItemTable() {
        ArrayList<BillItem> ItemList = ItemManager.getItemList(8);
       itemTable.setItems(itemObservableList);
        itemObservableList.clear();

        for (BillItem currentItem : ItemList) {
            itemObservableList.add(currentItem);
        }
    }

    private void setColumns() {

        itemId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        unit_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

    }




}
