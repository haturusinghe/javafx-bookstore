package fct.cs.book;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;

import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

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

    private void getBookData() {
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

        bookIdColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getBook_id())));
        bookTitleColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getTitle())));
        authorColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getAuthor())));
        categoryColumn.setRowCellFunction(book -> new MFXTableRowCell(String.valueOf(book.getCategoryName())));

        bookTable.getTableColumns().addAll(bookIdColumn, bookTitleColumn,authorColumn, categoryColumn);
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
