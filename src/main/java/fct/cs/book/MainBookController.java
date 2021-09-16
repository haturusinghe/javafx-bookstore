package fct.cs.book;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.enums.Styles;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainBookController implements Initializable {
    public MFXTableView bookTable;
    public JFXDrawer drawer;
    private MainBookController thisController = this;
    private ObservableList<BookData> bookObservableList = FXCollections.observableArrayList();
    private FilteredList<BookData> bookFilteredList;

    private static BookData selectedBook = null;
    private final MFXTextField searchField = new MFXTextField();
    private final MFXComboBox<String> searchCombo = new MFXComboBox<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getBookData();
        setColumnProps();

        bookTable.setHeaderSupplier(() -> {
            HBox mainContainer = new HBox();
            mainContainer.setPrefHeight(50);
            mainContainer.setPrefWidth(1030);
            mainContainer.setAlignment(Pos.CENTER_LEFT);
            mainContainer.setPadding(new Insets(10,0,5,5));

            HBox spanMid = new HBox();
            spanMid.setMinWidth(610);
            spanMid.setMinHeight(50);

            HBox spanEnd = new HBox();
            spanEnd.setMinHeight(50);
            spanEnd.setMaxWidth(10);

            HBox searchContainer = new HBox(10);
            searchContainer.setMinHeight(50);
            searchContainer.setAlignment(Pos.CENTER_RIGHT);
            searchField.setPromptText("Search Book Title or Author");
            searchField.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
            searchField.setIconInsets(new Insets(0,0,10,0));
            searchField.setMinHeight(50);
            searchField.setMinWidth(240);
            searchField.setStyle("-fx-font-size: 18px;");

            searchField.setOnKeyTyped(actionEvent -> {
                bookFilteredList = new FilteredList<BookData>(bookObservableList);
                thisController.searchTableFromText(searchField.getText());
                bookTable.setItems(bookFilteredList);
            });

            searchCombo.setItems(FXCollections.observableArrayList("Name", "Salary", "Gender"));
            searchCombo.setPromptText("Select Category");
            searchCombo.setMinHeight(50);
            searchCombo.setComboStyle(Styles.ComboBoxStyles.STYLE2);
            searchCombo.setStyle("-fx-font-size: 30px;");

            FontIcon addIcon = new FontIcon("anto-plus-circle");
            addIcon.setIconColor(Color.WHITE);
            addIcon.setIconSize(25);

            MFXButton addBtn = new MFXButton();
            addBtn.setText("Add Employee");
            addBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            addBtn.setGraphic(addIcon);
            addBtn.setOnAction(actionEvent -> {
                thisController.addNewBook();
            });


            /*MFXLabel testLabel = new MFXLabel("Header");
            HBox holderHbox = new HBox();
            holderHbox.getChildren().addAll(testLabel);
            holderHbox.setMaxWidth(Region.USE_PREF_SIZE);
            VBox box = new VBox(holderHbox);
            box.setAlignment(Pos.CENTER_RIGHT);*/

            searchContainer.getChildren().addAll(searchField);
            mainContainer.getChildren().addAll(searchContainer,spanMid,addBtn,spanEnd);
            return mainContainer;
        });
    }

    public void getBookData() {
        ArrayList<BookData> eList = BookManager.getBookList(100,1);
        bookObservableList.clear();
        for (BookData e:
                eList) {
            bookObservableList.add(e);

        }
        setDataData();
    }

    private void setDataData() {
        bookTable.setItems(bookObservableList);
    }

    private void setColumnProps() {
        MFXTableColumn<BookData> bookIdColumn = new MFXTableColumn<>("Book ID", Comparator.comparing(BookData::getBook_id));
        MFXTableColumn<BookData> bookTitleColumn = new MFXTableColumn<>("Book Title", Comparator.comparing(BookData::getTitle));
        MFXTableColumn<BookData> authorColumn = new MFXTableColumn<>("Author", Comparator.comparing(BookData::getAuthor));
        MFXTableColumn<BookData> categoryColumn = new MFXTableColumn<>("Category", Comparator.comparing(BookData::getCategoryName));
        MFXTableColumn<BookData> updateColumn = new MFXTableColumn<>("", Comparator.comparing(BookData::getBook_id));
        MFXTableColumn<BookData> deleteColumn = new MFXTableColumn<>("", Comparator.comparing(BookData::getBook_id));

        bookIdColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getBook_id())));
        bookTitleColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getTitle())));
        authorColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getAuthor())));
        categoryColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getCategoryName())));

        updateColumn.setMinWidth(100);
        updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(bookData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Update");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Update");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/book/addbook.fxml"));
                    VBox box = loader.load();
                    AddBookController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(drawer);
//                    controller.setInventoryManager(inventoryManager);
                    controller.setEntry(bookData);
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
        deleteColumn.setRowCellFunction(bookData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Delete");
            rowCell.setGraphicTextGap(5);
//            rowCell.setStyle("-fx-background-color:grey");
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Delete");
                BookManager.deleteSingleBook(bookData);
                getBookData();
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

        bookTable.getTableColumns().addAll(bookIdColumn, bookTitleColumn,authorColumn, categoryColumn,updateColumn,deleteColumn);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/book/addbook.fxml"));
            VBox box = loader.load();
            AddBookController controller = loader.getController();

            controller.setParentController(thisController);
            controller.setDrawer(drawer);
            controller.setAddingNew(true);
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
    }

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        bookFilteredList.setPredicate(bookData -> {
            String filter = key.toLowerCase();
            boolean nameMatches = String.valueOf(bookData.getTitle()).toLowerCase().contains(filter)||
            String.valueOf(bookData.getAuthor()).toLowerCase().contains(filter);
            return nameMatches;
        });
    }
}
