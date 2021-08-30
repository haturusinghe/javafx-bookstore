package fct.cs.inventory;

import fct.cs.Books.Book;

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

    public void loadBookDetails(Book bookDetails) {

        title_txtField.setText(bookDetails.getTitle());
        bookID_txtField.setText(String.valueOf(bookDetails.getBook_id()));

    }
}


