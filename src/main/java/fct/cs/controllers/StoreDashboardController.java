package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import fct.cs.commonUtil.AppUtils;
import fct.cs.data.Category;
import fct.cs.data.Inventory;
import fct.cs.data.User;
import fct.cs.dbUtil.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StoreDashboardController implements Initializable {

    public TableColumn colInvId;
    public TableColumn colIsbn;
    public TableColumn colListedPrice;
    public TableColumn colQty;
    public TableColumn colMin;
    public TableColumn colWarn;
    public MenuItem deleteEntry;
    public MenuItem viewEntry;
    public MenuItem updateEntry;
    public MenuItem addEntry;
    public ComboBox filterComboBox;
    public ComboBox categoryCombo;

    @FXML
    private VBox tableContainer;

    @FXML
    private HBox headerHBox;

    @FXML
    private HBox tableOptionsHBox;

    @FXML
    private JFXTextField searchField;


    @FXML
    private JFXButton SearchBtn;

    @FXML
    private TableView inventoryTable;

    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
    private ObservableList<Category> categoryList = FXCollections.observableArrayList();
    private FilteredList<Inventory> filteredList = new FilteredList<>(inventoryList);

//    DatabaseHandler databaseHandler;

    AppUtils appUtils;
    private static Inventory selectedItem;

    public void filter(KeyEvent keyEvent) {
        filterList();
        inventoryTable.setItems(filteredList);
    }


    public void filterList() {
        System.out.println("Searching ...");
        filteredList.setPredicate(inventory -> {
            // check that the series title, author, or artist contains the
            // text filter
            String filter = searchField.getText().toLowerCase();
            boolean title_matches = inventory.getIsbn().toLowerCase().contains(filter);
            /*
            boolean creator_matches = (inventory.getTitle() != null
                    && inventory.getPrice().toLowerCase().contains(filter))
                    || (inventory.getAuthor() != null && inventory.getPublisher.toLowerCase().contains(filter));*/

            // check that the series has the selected category
            boolean category_matches = false;
            /*
            if (fliterComboBox.getSelectionModel().getSelectedItem() != null) {
                JFXComboBoxItem<?> selectedTreeCell =
                        fliterComboBox.getSelectionModel().getSelectedItem();
                Category category = selectedTreeCell.getValue();
                category_matches = inventory.getStringCategories().stream()
                        .anyMatch(stringCategory -> stringCategory.equals(category.getName()))
                        || category.equals(library.getRootCategory());
            }*/

            return (title_matches); // || creator_matches) && category_matches;
        });
    }

    public void setCategories() throws SQLException {
        String qu = "select * from category";
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet rs = databaseHandler.excecuteQuery(qu);

        while (rs.next()){
            categoryList.add(new Category(
                    rs.getString("category_id"),
                    rs.getString("category_name")
            ));
        }

        categoryCombo.setItems(categoryList);


    }

    public void getResults(MouseEvent mouseEvent) throws SQLException {
        String qu = "select * from inventory";
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet rs = databaseHandler.excecuteQuery(qu);

        inventoryList.clear();
        while (rs.next()){
            inventoryList.add(new Inventory(
                    rs.getInt("inv_id"),
                    rs.getString("isbn"),
                    rs.getInt("list_price"),
                    rs.getInt("qty"),
                    rs.getInt("min_qty")
                    ));
        }

        loadInventoryDetails();


    }

    private void loadInventoryDetails() {
        inventoryTable.setItems(inventoryList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventoryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        try {
            setCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setColumnProperties() {
        colInvId.setCellValueFactory(new PropertyValueFactory<>("inv_id"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colListedPrice.setCellValueFactory(new PropertyValueFactory<>("list_price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colMin.setCellValueFactory(new PropertyValueFactory<>("min_qty"));
        colWarn.setCellFactory((tableColumn) -> {
            TableCell<User, Boolean> tableCell = new TableCell<>() {
                /*Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();*/

                @Override
                protected void updateItem(Boolean isLow, boolean empty) {
                    super.updateItem(isLow, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        /*btnEdit.setOnAction(e ->{
                            System.out.println("Clicked");
                        });

                        btnEdit.setStyle("-fx-background-color: #fff;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);*/

                        /*this.setGraphic(btnEdit);
                        this.setAlignment(Pos.CENTER);*/
                        
                    }

                }
            };

            return tableCell;
        });
    }

    public void deleteEntry(ActionEvent actionEvent) {
        selectedItem = (Inventory) inventoryTable.getSelectionModel().getSelectedItem();
    }

    public void viewEntry(ActionEvent actionEvent) {
        selectedItem = (Inventory) inventoryTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.toString());

    }

    public void updateEntry(ActionEvent actionEvent) {
        selectedItem = (Inventory) inventoryTable.getSelectionModel().getSelectedItem();
        appUtils = new AppUtils();
        appUtils.createDynamicStage();
    }

    public void addEntry(ActionEvent actionEvent) {
        selectedItem = (Inventory) inventoryTable.getSelectionModel().getSelectedItem();
    }


}