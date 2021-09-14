package fct.cs.NewBook;

import com.jfoenix.controls.JFXDrawer;
import fct.cs.NewEmployee.NewEmployeeController;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class BookFormController{

    @FXML
    private HBox book_id_hbox;

    @FXML
    private TextField bookTitle_txtField;

    @FXML
    private HBox isbn_hbox;

    @FXML
    private TextField isbn_txtField;

    @FXML
    private TextField author_txtField;

    @FXML
    private HBox line3_hbox;

    @FXML
    private TextField bookID_txtField;

    @FXML
    private TextField Category_txtField;

    @FXML
    private HBox line4_hbox;

    @FXML
    private TextField Bookyear_txtField;

    @FXML
    private TextField Mrp_txtField;

    @FXML
    private HBox line5_hbox;

    @FXML
    private TextField NoOfPage_txtField;

    @FXML
    private TextField lang_txtField;

    @FXML
    private HBox line6_hbox;

    @FXML
    private TextField Publisher_txtField;

    @FXML
    private TextField Descripe_txtField;

    @FXML
    private MFXButton submitBtn;
    @FXML
    private MFXButton cancelBtn;
    private BookController parentBookController;
    private JFXDrawer drawer;
    private boolean isAddingNew = false;



    @FXML
    void cancel(ActionEvent event) {

    }
    public void setParentBookController(BookController thisController) {
        this.parentBookController = thisController;
    }

    public void setDrawer(JFXDrawer bookdrawer) {
        this.drawer = bookdrawer;
    }

    public void setEntry(BookData bookData) {
        resetForm();
        bookTitle_txtField.setText(bookData.getTitle());
        isbn_txtField.setText(bookData.getIsbn());
        //author_txtField.setText(bookData.getLast_name());
        bookID_txtField.setText(String.valueOf(bookData.getBook_id()));
        Category_txtField.setText(String.valueOf(bookData.getCategory()));
        Bookyear_txtField.setText(bookData.getBook_year());
        Publisher_txtField.setText(bookData.getPublisher());
    }
    private void resetForm() {
        bookTitle_txtField.clear();
        isbn_txtField.clear();
       // author_txtField.clear();
        bookID_txtField.clear();
        Category_txtField.clear();
        Bookyear_txtField.clear();
        Publisher_txtField.clear();
    }

    public void setAddingNew(boolean b) {
        this.isAddingNew = b;
        if(b){
            bookTitle_txtField.setEditable(true);
        }else{
            bookTitle_txtField.setEditable(false);
        }
    }

    @FXML
    void updateBook(ActionEvent event) {

    }



}
