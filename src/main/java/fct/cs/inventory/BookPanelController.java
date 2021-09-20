package fct.cs.inventory;

import com.jfoenix.controls.JFXDrawer;
import fct.cs.Books.Book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookPanelController {

    @FXML
    private Label headerLabel;

    @FXML
    private TextField bookID_txtField;

    @FXML
    private TextField isbn_txtField;

    @FXML
    private TextField title_txtField;

    @FXML
    private TextField publisher_txtField;

    @FXML
    private TextField author_txtField;

    @FXML
    private TextField year_txtField;

    @FXML
    private TextField language_txtField;

    @FXML
    private TextField pages_txtField;

    @FXML
    private TextField description_txtField;

    @FXML
    private TextField category_txtField;

    @FXML
    private JFXDrawer drawer;

    private NewInventoryController parentController;

    public void loadBookDetails(Book bookDetails) {
        String authorName = bookDetails.getAuthor().getFirst_name() + " " + bookDetails.getAuthor().getLast_name();
        title_txtField.setText(bookDetails.getTitle());
        bookID_txtField.setText(String.valueOf(bookDetails.getBook_id()));
        isbn_txtField.setText(bookDetails.getIsbn());
        publisher_txtField.setText(bookDetails.getPublisher());
        author_txtField.setText(authorName);
        year_txtField.setText(bookDetails.getB_year().toString());
        language_txtField.setText(bookDetails.getLang());
        pages_txtField.setText(String.valueOf(bookDetails.getNum_pages()));
        description_txtField.setText(bookDetails.getBook_description());
        category_txtField.setText(bookDetails.getCategoryName());

    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void setParentController(NewInventoryController parentController) {
        this.parentController = parentController;
    }

    public void cancel(ActionEvent actionEvent) {
        drawer.close();
    }
}


