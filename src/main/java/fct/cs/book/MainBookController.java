package fct.cs.book;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getBookData();
        setColumnProps();
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/addbook.fxml"));
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

    public void addNewBook(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/addbook.fxml"));
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
}
