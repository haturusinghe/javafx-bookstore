package fct.cs.Bill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    private BillingController parentController;

    private ObservableList<BillItem> itemObservableList = FXCollections.observableArrayList();
    private BillItemController thisControl = this;

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
    public void setParentController(BillingController parentController) {
        this.parentController = parentController;
    }
    private void setColumns() {

        itemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        unit_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));



     additem.setCellFactory((tableColumn) -> {
        TableCell<BillItem, Integer> tableCell = new TableCell<>() {
//            javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/book.png"));
            final javafx.scene.control.Button btnAdd = new Button();
//            FontIcon icon3 = new FontIcon("antf-book");


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
                        System.out.println("Clicked Select");
                        BillItem entry = getTableView().getItems().get(getIndex());
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/Billing.fxml"));
//
//                        BillingController controller = loader.getController() ;
//                        controller.setParentController(thisControl);

                        parentController.getOrderDetails(entry.getItem_id(), entry.getItem_name() , entry.getUnit_price());
                        parentController.loadBillTable();


                    });


                    btnAdd.setStyle(" -fx-background-color: \n" +
                            "        #090a0c,\n" +
                            "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                            "        linear-gradient(#20262b, #191d22),\n" +
                            "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-background-insets: 0,1,2,0;\n" +
                            "    -fx-text-fill: white;\n" +
                            "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                            "    -fx-font-family: \"Arial\";\n" +
                            "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                            "    -fx-font-size: 12px;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    btnAdd.setText("ADD");
//                    icon3.setIconColor(Color.RED);
//                    icon3.setIconSize(30);
//
//                    ImageView iv = new ImageView();
//                    iv.setImage(imgSelect);
//                    iv.setPreserveRatio(true);
//                    iv.setSmooth(true);
//                    iv.setCache(true);
//                    btnAdd.setGraphic(icon3);

                    this.setGraphic(btnAdd);
                    this.setAlignment(Pos.CENTER);

                }
            }

        };
        return tableCell;
    });


}
public void goToSelectCustomer(ActionEvent action){
        parentController.moveToSelectCustomer();

}
}
