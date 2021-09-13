package fct.cs.NewBook;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.NewBook.BookData;
import fct.cs.NewBook.BookFormController;
import fct.cs.NewBook.BookManager;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Comparator;

public class BookController {
    public MFXTableView bookTable;
    public JFXDrawer bookdrawer;
    private BookController thisthisController = this;
    private ObservableList<BookData> bookObservableList = FXCollections.observableArrayList();
    private FilteredList<BookData> bookFilteredList = new FilteredList<>(bookObservableList);
    private static BookData selectedbook = null;

    // private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    private void setColumnProps() {

        MFXTableColumn<BookData> TitleColumn = new MFXTableColumn<>("Book Title", Comparator.comparing(BookData::getTitle));
        MFXTableColumn<BookData> publisherColumn = new MFXTableColumn<>("Publisher", Comparator.comparing(BookData::getPublisher));
        MFXTableColumn<BookData> AuthorColumn = new MFXTableColumn<>("Author Id", Comparator.comparing(BookData::getAuthor_id));
        MFXTableColumn<BookData> YearColumn = new MFXTableColumn<>("Year", Comparator.comparing(BookData::getBook_year));

        MFXTableColumn<BookData> updateColumn = new MFXTableColumn<>("", Comparator.comparing(BookData::getTitle));
        MFXTableColumn<BookData> deleteColumn = new MFXTableColumn<>("", Comparator.comparing(BookData::getTitle));

        TitleColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getTitle())));
        publisherColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getPublisher())));
        AuthorColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getAuthor_id())));
        YearColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getBook_year())));

//        updateColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));
//        deleteColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));

        updateColumn.setMinWidth(100);
        updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(employeeData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Update");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Update");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/employee-form.fxml"));
                    VBox box = loader.load();
                   BookFormController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(bookdrawer);
//                    controller.setInventoryManager(inventoryManager);
                    controller.setEntry(employeeData);
                    controller.setAddingNew(false);
                    bookdrawer.setSidePane(box);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (bookdrawer.isHidden()) {
                    bookdrawer.open();
                    bookdrawer.toFront();
                    System.out.println("open");
                } else {
                    bookdrawer.toBack();
                    bookdrawer.close();

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
        deleteColumn.setRowCellFunction(employeeData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Delete");
            rowCell.setGraphicTextGap(5);
//            rowCell.setStyle("-fx-background-color:grey");
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Delete");
                BookManager.deleteSingleEmployee(employeeData);
                getEmployeeData();
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


        bookTable.getTableColumns().addAll(TitleColumn,publisherColumn,AuthorColumn, YearColumn,updateColumn,deleteColumn);
    }


    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        bookdrawer.toBack();
    }

    public void addNewEntry(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/new-book-form.fxml"));
            VBox box = loader.load();
            BookFormController controller = loader.getController();

            controller.setParentBookController(thisthisController);
            controller.setDrawer(bookdrawer);
          //  controller.setAddingNew(true);
            bookdrawer.setSidePane(box);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (bookdrawer.isHidden()) {
            bookdrawer.open();
            bookdrawer.toFront();
            System.out.println("open");
        } else {
            bookdrawer.toBack();
            bookdrawer.close();

            System.out.println("close");
        }
    }

}



}