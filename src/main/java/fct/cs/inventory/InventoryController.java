package fct.cs.inventory;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.Books.Book;
import fct.cs.data.Category;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class InventoryController implements Initializable {

    @FXML
    private MFXFilterComboBox category_combo;

    @FXML
    private MFXTextField m_search_txt;

    @FXML
    private MFXCheckbox lowStock_checkbox;

    @FXML
    private MFXCheckbox outStock_checkbox;

    @FXML
    private VBox drawerBox;

    @FXML
    private StackPane stackPane;

    @FXML
    private Button addNewEntryBtn;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private TableColumn colDelete;

    @FXML
    private TextField search_txt;

    @FXML
    private ComboBox category_combo_old;

    @FXML
    private Button outStockBtn;

    @FXML
    private TableView inventoryTable;

    @FXML
    private TableColumn colBookId;

    @FXML
    private TableColumn colListedPrice;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colMin;

    @FXML
    private TableColumn colUpdate;

    @FXML
    private TableColumn colViewDet;

    private boolean isManager = false;

    private InventoryManager inventoryManager;

    private ObservableList<StockEntry> stockEntryObservableList = FXCollections.observableArrayList();
    private FilteredList<StockEntry> stockEntryFilteredList = new FilteredList<>(stockEntryObservableList);

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    private InventoryController thisController = this;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventoryManager = new InventoryManager();
        setColumnProperties();
        loadTableData();

        /*try {
            *//*
            *FXMLLoader loader =   new FXMLLoader(getClass().getResource("/fct/cs/edit-inventory.fxml"));
            *VBox box = loader.load();
            *controller = loader.getController();
            *
            * *//*

            editPanelLoader = new FXMLLoader(getClass().getResource("/fct/cs/edit-inventory.fxml"));
            boxStock = editPanelLoader.load();

            bookPanelLoader = new FXMLLoader(getClass().getResource("/fct/cs/book-details.fxml"));
            boxBook = editPanelLoader.load();

//            drawer.setSidePane(box);

            // Inventory Panel
            editInventoryController = editPanelLoader.getController();
            editInventoryController.setParentController(this);
            editInventoryController.setDrawer(drawer);
            editInventoryController.setInventoryManager(inventoryManager);
            System.out.println("loaded");
            // Book Panel
            bookPanelController = bookPanelLoader.getController();
            bookPanelController.setParentController(this);
            bookPanelController.setDrawer(drawer);


        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/book-details.fxml"));
            VBox box = loader.load();
            BookPanelController controller = loader.getController();
            controller.loadBookDetails();
            drawer.setSidePane(box);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        try {
            setCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        printInventoryList();
    }

    public void loadTableData() {

        ArrayList<StockEntry> stockItemList = inventoryManager.getStockItemList(100, 1);
        ArrayList<StockEntry> lowItems = new ArrayList<>();
        stockEntryObservableList.clear();
        for (StockEntry currentStockItem : stockItemList) {
            stockEntryObservableList.add(currentStockItem);
            if (currentStockItem.getQty() < currentStockItem.getMin_qty()){
                lowItems.add(currentStockItem);
            }
        }
        inventoryTable.setItems(stockEntryObservableList);
        if(lowItems.size() > 0){
            showNotification(lowItems);
        }

    }

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        stockEntryFilteredList.setPredicate(stockEntry -> {
            String filter = key.toLowerCase();
            boolean title_matches = String.valueOf(stockEntry.getBook_id()).toLowerCase().contains(filter);
            return title_matches;
        });
    }

    public void findOutOfStockItems() {
        System.out.println("Finding Out of Stock ...");
        stockEntryFilteredList.setPredicate(stockEntry -> {
            boolean isOutOfStock = stockEntry.getQty() == 0;
            return isOutOfStock;
        });
    }

    public void findLowOnStockItems() {
        System.out.println("Finding Low on of Stock ...");
        stockEntryFilteredList.setPredicate(stockEntry -> {
            boolean isOutOfStock = stockEntry.getQty() <= stockEntry.getMin_qty();
            return isOutOfStock;
        });
    }

    public void filterTableFromCategories(ArrayList<String> bookIdListForGivenCategory) {
        System.out.println("Searching from Ids ...");
        stockEntryFilteredList.setPredicate(stockEntry -> {
            boolean title_matches = bookIdListForGivenCategory.contains(String.valueOf(stockEntry.getBook_id()));
            return title_matches;
        });


    }

    public void setCategories() throws SQLException {
        String qu = "select * from book_category";
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        ResultSet rs = databaseHandler.excecuteQuery(qu);

        while (rs.next()){
            categoryList.add(new Category(
                    rs.getString("category_id"),
                    rs.getString("category_name"),
                    -1,-1
            ));
        }

        category_combo.setItems(categoryList);
    }

    private void setColumnProperties() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colListedPrice.setCellValueFactory(new PropertyValueFactory<>("list_price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colMin.setCellValueFactory(new PropertyValueFactory<>("min_qty"));


        colViewDet.setCellFactory((tableColumn) -> {
            TableCell<StockEntry, Integer> tableCell = new TableCell<>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/book.png"));
                final Button btnEdit = new Button();
                FontIcon icon3 = new FontIcon("antf-book");


                @Override
                protected void updateItem(Integer book_id, boolean empty) {
                    super.updateItem(book_id, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        btnEdit.setOnAction(e ->{
                            System.out.println("Clicked View Book");
                            StockEntry entry = getTableView().getItems().get(getIndex());
                            System.out.println(entry.toString());
                            System.out.println(book_id);
                            Book currentBook = inventoryManager.getBookDetails(entry.getBook_id());
                            System.out.println(currentBook.toString());

                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/show-book.fxml"));
                                VBox box = loader.load();
                                BookPanelController controller = loader.getController();

//                            controller.setParentController(this);
                                controller.loadBookDetails(currentBook);
                                controller.setDrawer(drawer);
//                            controller.setInventoryManager(inventoryManager);
//                            controller.setAddingNew(true);
                                drawer.setSidePane(box);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


/*                            TranslateTransition openNav = new TranslateTransition(new Duration(350), drawerBox);
                            openNav.setToX(0);
                            TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawerBox);

                            if (drawerBox.getTranslateX() != 0) {
                                openNav.play();
                            } else {
                                closeNav.setToX(-(drawerBox.getWidth()));
                                closeNav.play();
                            }*/
                            if (drawer.isHidden()) {
                                drawer.open();
                                drawer.toFront();
                                System.out.println("open");
                            } else {
                                drawer.toBack();
                                drawer.close();

                                System.out.println("close");
                            }
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");

                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        icon3.setIconColor(Color.WHEAT);
                        icon3.setIconSize(30);
                        btnEdit.setGraphic(icon3);

                        this.setGraphic(btnEdit);
                        this.setAlignment(Pos.CENTER);

                    }

                }
            };

            return tableCell;
        });

        colUpdate.setCellFactory((tableColumn) -> {
            TableCell<StockEntry, Integer> tableCell = new TableCell<>() {
                Image imgRemove = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnRemove = new Button();
                FontIcon icon3 = new FontIcon("antf-edit");


                @Override
                protected void updateItem(Integer inv_id, boolean empty) {
                    super.updateItem(inv_id, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        btnRemove.setOnAction(e ->{
                            System.out.println("Clicked Update");
                            StockEntry entry = getTableView().getItems().get(getIndex());
                            getTableView().getSelectionModel().select(getIndex());
                            System.out.println(getTableView().getSelectionModel().getSelectedItem().toString());

                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/edit-inventory.fxml"));
                                VBox box = loader.load();
                                EditInventoryController controller = loader.getController();

                                controller.setParentController(thisController);
                                controller.setDrawer(drawer);
                                controller.setInventoryManager(inventoryManager);
                                controller.setEntry(entry);
                                controller.setAddingNew(false);
                                drawer.setSidePane(box);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            /*editInventoryController.setAddingNew(false);
                            editInventoryController.setEntry(entry);
                            drawer.setSidePane(boxStock);*/

                            if (drawer.isHidden()) {
                                drawer.open();
                                drawer.toFront();
                                System.out.println("open");
                            } else {
                                drawer.toBack();
                                drawer.close();

                                System.out.println("close");
                            }

                            /*try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/book-details.fxml"));
                                VBox box = null;
                                box = loader.load();
                                BookPanelController controller = loader.getController();

                                controller.loadBookDetails(currentBook);
                                drawer.setSidePane(box);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

*//*                            TranslateTransition openNav = new TranslateTransition(new Duration(350), drawerBox);
                            openNav.setToX(0);
                            TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawerBox);

                            if (drawerBox.getTranslateX() != 0) {
                                openNav.play();
                            } else {
                                closeNav.setToX(-(drawerBox.getWidth()));
                                closeNav.play();
                            }*//*

                            if (drawer.isHidden()) {
                                drawer.open();
                                drawer.toFront();
                                System.out.println("open");
                            } else {
                                drawer.toBack();
                                drawer.close();

                                System.out.println("close");
                            }
                        });*/

                        });

                        btnRemove.setStyle("-fx-background-color: transparent;");
                        icon3.setIconColor(Color.BLACK);
                        icon3.setIconSize(30);
                        ImageView iv = new ImageView();
                        iv.setImage(imgRemove);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnRemove.setGraphic(icon3);

                        this.setGraphic(btnRemove);
                        this.setAlignment(Pos.CENTER);

                    }

                }
            };

            return tableCell;
        });

        colDelete.setCellFactory((tableColumn) -> {
            TableCell<StockEntry, Integer> tableCell = new TableCell<>() {
                Image imgRemove = new Image(getClass().getResourceAsStream("/images/remove.png"));
                final Button btnRemove = new Button();
                FontIcon icon3 = new FontIcon("antf-delete");


                @Override
                protected void updateItem(Integer inv_id, boolean empty) {
                    super.updateItem(inv_id, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        btnRemove.setOnAction(e ->{
                            System.out.println("Clicked Remove");
                            StockEntry entry = getTableView().getItems().get(getIndex());
                            getTableView().getSelectionModel().select(getIndex());
                            System.out.println(getTableView().getSelectionModel().getSelectedItem().toString());

                            inventoryManager.deleteSingleEntry(entry);
                            loadTableData();

                        });

                        btnRemove.setStyle("-fx-background-color: transparent;");
                        icon3.setIconColor(Color.RED);
                        icon3.setIconSize(30);

                        ImageView iv = new ImageView();
                        iv.setImage(imgRemove);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnRemove.setGraphic(icon3);

                        this.setGraphic(btnRemove);
                        this.setAlignment(Pos.CENTER);

                    }

                }
            };

            return tableCell;
        });
    }

    private void printInventoryList() {
        ArrayList<StockEntry> ls = inventoryManager.getStockItemList(5, 1);

        for (StockEntry i : ls) {
            System.out.println(i.toString());
        }
    }

    @FXML
    void filterTableCategory(ActionEvent event) {

        Category category = (Category)category_combo.getSelectionModel().getSelectedItem();

        if (category != null) {
            System.out.println("Category Name: " + ((Category) category_combo.getSelectionModel().getSelectedItem()).getCategory_name());
            System.out.println("Category ID: " +category.getCategory_id());
            ArrayList<String> idList = inventoryManager.getBookIdsForCategory(Integer.parseInt(category.getCategory_id()));
            for (String i:idList
                 ) {
                System.out.println(i);
            }
            filterTableFromCategories(idList);
            inventoryTable.setItems(stockEntryFilteredList);
        }

    }

    @FXML
    void searchTable(KeyEvent keyEvent) {
        String key = search_txt.getText();
        searchTableFromText(key);
        inventoryTable.setItems(stockEntryFilteredList);
    }

    @FXML
    void selectItem(MouseEvent event) {

    }

    @FXML
    void viewOutOfStock(ActionEvent event) {
        findOutOfStockItems();
        inventoryTable.setItems(stockEntryFilteredList);
    }

    @FXML
    void viewLowOnStock(ActionEvent event) {
        findLowOnStockItems();
        inventoryTable.setItems(stockEntryFilteredList);
    }

    public void toggleLowStock(ActionEvent actionEvent) {
        if(lowStock_checkbox.isSelected()){
            outStock_checkbox.setSelected(false);
        }
        findLowOnStockItems();
        inventoryTable.setItems(stockEntryFilteredList);
    }

    public void toggleOutStock(ActionEvent actionEvent) {
        if(outStock_checkbox.isSelected()){
            lowStock_checkbox.setSelected(false);
        }
        findOutOfStockItems();
        inventoryTable.setItems(stockEntryFilteredList);
    }

    public void resetFilters(ActionEvent actionEvent) {
        searchTableFromText("");
        category_combo.setSelectedValue(null);
        outStock_checkbox.setSelected(false);
        lowStock_checkbox.setSelected(false);
        inventoryTable.setItems(stockEntryFilteredList);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewEntry(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/edit-inventory.fxml"));
            VBox box = loader.load();
            EditInventoryController controller = loader.getController();

            controller.setParentController(this);
            controller.setDrawer(drawer);
            controller.setInventoryManager(inventoryManager);
            controller.setAddingNew(true);
            drawer.setSidePane(box);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (drawer.isHidden()) {
            drawer.open();
            drawer.toFront();
            System.out.println("open");
        } else {
            drawer.toBack();
            drawer.close();

            System.out.println("close");
        }
    }

    public void showNotification(ActionEvent actionEvent) {
        NotificationManager notificationManager = new NotificationManager();
        notificationManager.showBottomRight();
    }

    private void showNotification(ArrayList<StockEntry> lowItems) {
        NotificationManager notificationManager = new NotificationManager();
        notificationManager.showBottomRight(lowItems);
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}
