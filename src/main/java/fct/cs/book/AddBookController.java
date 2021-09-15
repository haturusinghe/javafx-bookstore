package fct.cs.book;

import com.jfoenix.controls.JFXDrawer;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddBookController {

    @FXML
    private HBox book_title_hbox;

    @FXML
    private TextField book_title_txtField;

    @FXML
    private HBox id_isbn_hbox;

    @FXML
    private TextField id_txtField;

    @FXML
    private TextField isbn_txtField;

    @FXML
    private HBox author_category_hbox;

    @FXML
    private TextField authorid_txtField;

    @FXML
    private TextField categoryid_txtField1;

    @FXML
    private HBox publisher_hbox;

    @FXML
    private TextField publisher_txtField;

    @FXML
    private HBox mrp_lang_hbox;

    @FXML
    private TextField mrp_txtField;

    @FXML
    private TextField language_txtField;

    @FXML
    private HBox pages_year_hbox;

    @FXML
    private TextField pages_txtField;

    @FXML
    private TextField year_txtField;

    @FXML
    private HBox description_hbox;

    @FXML
    private TextField description_txtField;

    @FXML
    private MFXButton submitBtn;

    @FXML
    private MFXButton cancelBtn;

    public void cancel(javafx.event.ActionEvent actionEvent) {
        drawer.close();
    }

    private MainBookController parentController;
    private JFXDrawer drawer;
    private boolean isAddingNew = false;

    public void setParentController(MainBookController thisController) {
        this.parentController = thisController;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void updateBook(ActionEvent actionEvent) {
        BookData bookData = getEntry();
        if (isAddingNew) {
            BookManager.addSingleBook(bookData);
        } else {
            BookManager.updateBook(bookData);
        }
        parentController.getBookData();
        drawer.close();

    }

    private BookData getEntry() {
        BookData e = new BookData(
                book_title_txtField.getText(),
                Integer.parseInt(id_txtField.getText()),
                isbn_txtField.getText(),
                Integer.parseInt(authorid_txtField.getText()),
                Integer.parseInt(categoryid_txtField1.getText()),
                publisher_txtField.getText(),
                Integer.parseInt(mrp_txtField.getText()),
                language_txtField.getText(),
                Integer.parseInt(pages_txtField.getText()),
                Integer.parseInt(year_txtField.getText()),
                description_txtField.getText()
        );

        return e;
    }


    public void setEntry(BookData bookData) {
        resetForm();
        book_title_txtField.setText(bookData.getTitle());
        id_txtField.setText(String.valueOf(bookData.getBook_id()));
        isbn_txtField.setText(bookData.getIsbn());
        authorid_txtField.setText(String.valueOf(bookData.getAuthor_id()));
        categoryid_txtField1.setText(String.valueOf(bookData.getCategory_id()));
        publisher_txtField.setText(bookData.getPublisher());
        mrp_txtField.setText(String.valueOf(bookData.getMrp()));
        language_txtField.setText(bookData.getLang());
        pages_txtField.setText(String.valueOf(bookData.getNum_pages()));
        year_txtField.setText(String.valueOf(bookData.getB_year()));
        description_txtField.setText(bookData.getBook_description());
    }

    private void resetForm() {
        book_title_txtField.clear();
        id_txtField.clear();
        isbn_txtField.clear();
        authorid_txtField.clear();
        categoryid_txtField1.clear();
        publisher_txtField.clear();
        mrp_txtField.clear();
        language_txtField.clear();
        pages_txtField.clear();
        year_txtField.clear();
        description_txtField.clear();
    }

    public void setAddingNew(boolean b) {
        this.isAddingNew = b;
        if(b){
            id_txtField.setEditable(true);
        }else{
            id_txtField.setEditable(false);
        }
    }

}

