package fct.cs.book;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainBookController implements Initializable {
    public MFXTableView bookTable;
    public JFXDrawer drawer;
    private MainBookController thisController = this;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
