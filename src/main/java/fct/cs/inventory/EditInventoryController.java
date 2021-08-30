package fct.cs.inventory;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditInventoryController {
    @FXML
    private JFXDrawer drawer;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtMin;

    @FXML
    private Button cancelBtn;


    public void cancel(ActionEvent actionEvent) {
        drawer.close();
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }
}
