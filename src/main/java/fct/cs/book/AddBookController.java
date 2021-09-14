package fct.cs.book;

import com.jfoenix.controls.JFXDrawer;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.awt.event.ActionEvent;

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
    private HBox author_hbox;

    @FXML
    private TextField author_txtField;

    @FXML
    private HBox publisher_hbox;

    @FXML
    private TextField publisher_txtField;

    @FXML
    private TextField category_txtField;

    @FXML
    private TextField language_txtField;

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

    public void setAddingNew(boolean b) {
        this.isAddingNew = b;
        if(b){
            id_txtField.setEditable(true);
        }else{
            id_txtField.setEditable(false);
        }
    }

}

