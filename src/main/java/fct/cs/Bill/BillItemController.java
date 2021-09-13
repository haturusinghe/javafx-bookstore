package fct.cs.Bill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BillItemController implements Initializable {

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

        itemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        unit_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));



     additem.setCellFactory((tableColumn) -> {
        TableCell<BillCustomer, Integer> tableCell = new TableCell<>() {
            javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/book.png"));
            final javafx.scene.control.Button btnAdd = new Button();
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

                    btnAdd.setOnAction(e ->{



                    });


                    btnAdd.setStyle("-fx-background-color: transparent;");

                    icon3.setIconColor(Color.RED);
                    icon3.setIconSize(30);

                    ImageView iv = new ImageView();
                    iv.setImage(imgSelect);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    btnAdd.setGraphic(icon3);

                    this.setGraphic(btnAdd);
                    this.setAlignment(Pos.CENTER);


                }


            }

        };
        return tableCell;
    });


}
}
