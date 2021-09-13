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

    @FXML
    void updateBook(ActionEvent event) {

    }



}
