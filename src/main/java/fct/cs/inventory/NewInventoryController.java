package fct.cs.inventory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.Books.Book;
import fct.cs.NewEmployee.EmployeeData;
import fct.cs.NewEmployee.EmployeeFormController;
import fct.cs.NewEmployee.EmployeeManager;
import fct.cs.commonUtil.NotificationCreator;
import fct.cs.data.Category;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.enums.Styles;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class NewInventoryController implements Initializable {


    MFXTableColumn<StockEntry> bookIdCol;
    MFXTableColumn<StockEntry> bookTitleCol;
    MFXTableColumn<StockEntry> listedPriceCol;
    MFXTableColumn<StockEntry> qtyCol;
    MFXTableColumn<StockEntry> minQtyCol;
    MFXTableColumn<StockEntry> viewColumn;
    MFXTableColumn<StockEntry> updateColumn;
    MFXTableColumn<StockEntry> deleteColumn;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private MFXTableView inventoryTable;

    private boolean isManager = false;

    private InventoryManager inventoryManager;

    private ObservableList<StockEntry> stockEntryObservableList = FXCollections.observableArrayList();
    private FilteredList<StockEntry> stockEntryFilteredList;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    private NewInventoryController thisController = this;

    private final MFXTextField searchField = new MFXTextField();
    private final MFXCheckbox lowCheck = new MFXCheckbox();
    private final MFXCheckbox outCheck = new MFXCheckbox();
    private final MFXComboBox<Category> searchCombo = new MFXComboBox<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventoryManager = new InventoryManager();
        setColumnProps();
        loadTableData();
        setColumnSize();

        inventoryTable.setHeaderSupplier(() -> {
            HBox mainContainer = new HBox();

            mainContainer.setPrefHeight(50);
            mainContainer.setPrefWidth(1030);
            mainContainer.setAlignment(Pos.CENTER_LEFT);
            mainContainer.setPadding(new Insets(10, 0, 5, 5));

            HBox spanMid = new HBox(10);
            spanMid.setMinWidth(580);
            spanMid.setMinHeight(50);
            spanMid.setAlignment(Pos.CENTER_LEFT);
            spanMid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            HBox spanEnd = new HBox();
            spanEnd.setMinHeight(50);
            spanEnd.setMaxWidth(10);
            spanEnd.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            HBox searchContainer = new HBox(10);
            searchContainer.setMinHeight(50);
            searchContainer.setAlignment(Pos.CENTER_RIGHT);
            HBox.setMargin(searchContainer,new Insets(0,40,0,0));
            searchField.setPromptText("Search Inventory");
            searchField.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
            searchField.setIconInsets(new Insets(0, 0, 10, 0));
            searchField.setMinHeight(50);
            searchField.setStyle("-fx-font-size: 18px;");

            searchField.setOnKeyTyped(actionEvent -> {
                stockEntryFilteredList = new FilteredList<StockEntry>(stockEntryObservableList);
                thisController.searchTableFromText(searchField.getText());
                inventoryTable.setItems(stockEntryFilteredList);
            });

            try {
                setCategories();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            searchCombo.setPromptText("Select Category");
            searchCombo.setMinHeight(50);
            searchCombo.setComboStyle(Styles.ComboBoxStyles.STYLE2);
            searchCombo.setStyle("-fx-font-size: 30px;");

            FontIcon filterIcon = new FontIcon("anto-filter");
            filterIcon.setIconColor(Color.WHITE);
            filterIcon.setIconSize(25);

            FontIcon resetIcon = new FontIcon("anto-reload");
            resetIcon.setIconColor(Color.WHITE);
            resetIcon.setIconSize(25);

            MFXButton filterBtn = new MFXButton();
            filterBtn.setText("");
            filterBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            filterBtn.setGraphic(filterIcon);
            filterBtn.setOnAction(actionEvent -> {
                stockEntryFilteredList = new FilteredList<StockEntry>(stockEntryObservableList);
                thisController.filterTableCategory(actionEvent);
            });

            lowCheck.setText("Low on Stock");
            lowCheck.setOnAction(actionEvent -> {
                stockEntryFilteredList = new FilteredList<StockEntry>(stockEntryObservableList);
                toggleLowStock(actionEvent);
            });

            outCheck.setText("Out of Stock");
            outCheck.setOnAction(actionEvent -> {
                stockEntryFilteredList = new FilteredList<StockEntry>(stockEntryObservableList);
                toggleOutStock(actionEvent);
            });

            MFXButton resetBtn = new MFXButton();
            resetBtn.setText("");
            resetBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            resetBtn.setGraphic(resetIcon);
            resetBtn.setOnAction(actionEvent -> {
                thisController.resetFilters(actionEvent);
            });


            FontIcon addIcon = new FontIcon("anto-plus-circle");
            addIcon.setIconColor(Color.WHITE);
            addIcon.setIconSize(25);

            MFXButton addBtn = new MFXButton();
            addBtn.setText("Add New Entry");
            addBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            addBtn.setGraphic(addIcon);
            addBtn.setOnAction(actionEvent -> {
                thisController.addNewEntry(actionEvent);
            });


            /*MFXLabel testLabel = new MFXLabel("Header");
            HBox holderHbox = new HBox();
            holderHbox.getChildren().addAll(testLabel);
            holderHbox.setMaxWidth(Region.USE_PREF_SIZE);
            VBox box = new VBox(holderHbox);
            box.setAlignment(Pos.CENTER_RIGHT);*/

            searchContainer.getChildren().addAll(searchField);
            spanMid.getChildren().addAll(searchCombo,filterBtn,lowCheck,outCheck,resetBtn);
            mainContainer.getChildren().addAll(searchContainer, spanMid, addBtn, spanEnd);
            return mainContainer;
        });
    }

    public void resetFilters(ActionEvent actionEvent) {
        searchCombo.setSelectedValue(null);
        outCheck.setSelected(false);
        lowCheck.setSelected(false);
        inventoryTable.setItems(stockEntryObservableList);
    }

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        stockEntryFilteredList.setPredicate(stockEntry -> {
            String filter = key.toLowerCase();
            boolean nameMatches = String.valueOf(stockEntry.getBook_title()).toLowerCase().contains(filter)
                    || String.valueOf(stockEntry.getBook_id()).toLowerCase().contains(filter);
            return nameMatches;
        });
    }

    public void addNewEntry(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/inventory/edit-inventory.fxml"));
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

    @SuppressWarnings("unchecked")
    private void setColumnProps() {

        bookIdCol = new MFXTableColumn<>("Book ID", Comparator.comparing(StockEntry::getBook_id));
        bookTitleCol = new MFXTableColumn<>("Book Title", Comparator.comparing(StockEntry::getBook_title));
        listedPriceCol = new MFXTableColumn<>("Listed Price", Comparator.comparing(StockEntry::getList_price));
        qtyCol = new MFXTableColumn<>("Quantity", Comparator.comparing(StockEntry::getQty));
        minQtyCol = new MFXTableColumn<>("Minimum Quantity", Comparator.comparing(StockEntry::getQty));
        viewColumn = new MFXTableColumn<>("", Comparator.comparing(StockEntry::getInv_id));
        updateColumn = new MFXTableColumn<>("", Comparator.comparing(StockEntry::getInv_id));
        deleteColumn = new MFXTableColumn<>("", Comparator.comparing(StockEntry::getInv_id));

        bookIdCol.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getBook_id())));
        bookTitleCol.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getBook_title())));
        listedPriceCol.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getList_price())));
        qtyCol.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getQty())));
        minQtyCol.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getMin_qty())));

        viewColumn.setMinWidth(100);
        viewColumn.setShowLockIcon(false);
        viewColumn.setRowCellFunction(stockEntry -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("View Book");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Clicked View Book");
                Book currentBook = inventoryManager.getBookDetails(stockEntry.getBook_id());
                System.out.println(currentBook.toString());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/inventory/show-book.fxml"));
                    VBox box = loader.load();
                    BookPanelController controller = loader.getController();

                    controller.setParentController(this);
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
            FontIcon icon = new FontIcon("antf-book");
            icon.setIconSize(25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> employeeData.setState(employeeData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });

        updateColumn.setMinWidth(100);
        updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(stockEntry -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Update");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Update: " + stockEntry.toString());

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/inventory/edit-inventory.fxml"));
                    VBox box = loader.load();
                    EditInventoryController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(drawer);
                    controller.setInventoryManager(inventoryManager);
                    controller.setEntry(stockEntry);
                    controller.setAddingNew(false);
                    drawer.setSidePane(box);
                } catch (IOException ex) {
                    ex.printStackTrace();
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

            });
            MFXFontIcon icon = new MFXFontIcon("mfx-file", 25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.LIMEGREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> employeeData.setState(employeeData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });

        deleteColumn.setMinWidth(100);
        deleteColumn.setShowLockIcon(false);
        deleteColumn.setRowCellFunction(stockEntry -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Delete");
            rowCell.setGraphicTextGap(5);
//            rowCell.setStyle("-fx-background-color:grey");
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                inventoryManager.deleteSingleEntry(stockEntry);
                loadTableData();
            });
            MFXFontIcon icon = new MFXFontIcon("mfx-minus-circle", 25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> employeeData.setState(employeeData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });
//        updateColumn.setResizable(false);
//        updateColumn.setMaxWidth(20);


        inventoryTable.getTableColumns().addAll(bookIdCol, bookTitleCol, listedPriceCol, qtyCol, minQtyCol, viewColumn, updateColumn, deleteColumn);
    }

    public void loadTableData() {

        ArrayList<StockEntry> stockItemList = inventoryManager.getStockItemList(100, 1);
        ArrayList<StockEntry> lowItems = new ArrayList<>();

        Platform.runLater(() -> {
            System.out.println("Later");
            stockEntryObservableList.clear();
            for (StockEntry currentStockItem : stockItemList) {
                stockEntryObservableList.add(currentStockItem);
                if (currentStockItem.getQty() < currentStockItem.getMin_qty()) {
                    lowItems.add(currentStockItem);
                }
            }
            inventoryTable.setItems(stockEntryObservableList);
            if (lowItems.size() > 0) {
                showNotification(lowItems);
            }
        });
    }

    private void showNotification(ArrayList<StockEntry> lowItems) {
        NotificationCreator.showBottomRight(lowItems);
    }

    private void setUiForRole() {
        if (!isManager) {
            updateColumn.setVisible(false);
            deleteColumn.setVisible(false);
//            m_addNewEntryBtn.setVisible(false);
        } else {
            updateColumn.setVisible(true);
            deleteColumn.setVisible(true);
//            m_addNewEntryBtn.setVisible(true);
        }
    }

    private void setTableData() {
        inventoryTable.setItems(stockEntryObservableList);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    private void setColumnSize() {
        ObservableList<MFXTableColumn> cols = inventoryTable.getTableColumns();
        for (MFXTableColumn col : cols) {
            if (col.getText() != "") {
                col.setShowLockIcon(false);
                col.setMinWidth(30);
            }
        }
    }



    void filterTableCategory(ActionEvent event) {

        Category category = (Category)searchCombo.getSelectionModel().getSelectedItem();

        if (category != null) {
            System.out.println("Category Name: " + ((Category) searchCombo.getSelectionModel().getSelectedItem()).getCategory_name());
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

    public void toggleLowStock(ActionEvent actionEvent) {
        if(lowCheck.isSelected()){
            outCheck.setSelected(false);
            findLowOnStockItems();
            inventoryTable.setItems(stockEntryFilteredList);
        }else{
            inventoryTable.setItems(stockEntryObservableList);
        }

    }

    public void toggleOutStock(ActionEvent actionEvent) {
        if(outCheck.isSelected()){
            lowCheck.setSelected(false);
            findOutOfStockItems();
            inventoryTable.setItems(stockEntryFilteredList);
        }else{
            inventoryTable.setItems(stockEntryObservableList);
        }

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

        searchCombo.setItems(categoryList);
    }
}
