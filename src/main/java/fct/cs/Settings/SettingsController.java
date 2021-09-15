package fct.cs.Settings;

import com.jfoenix.controls.JFXButton;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.apache.ibatis.jdbc.ScriptRunner;

public class SettingsController implements Initializable {
    @FXML
    private ImageView imgX;

    @FXML
    private VBox login_form_vbox;

    @FXML
    private MFXTextField dbURL;

    @FXML
    private MFXTextField userTxt;

    @FXML
    private MFXPasswordField passTxt;

    @FXML
    private JFXButton submitBtn;

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void submitInfo(ActionEvent event) {

    }

    Connection conn = null;

    public void changeInfo(ActionEvent actionEvent) {
        String username = userTxt.getText().trim();
        String password = passTxt.getText().trim();
        String urlName = dbURL.getText().trim();

        try {
             this.conn = DatabaseHandler.getInstance(username, password, urlName).getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDb(ActionEvent actionEvent) {
        //Initialize the script runner
        if (conn != null) {
            ScriptRunner sr = new ScriptRunner(conn);
            //Creating a reader object
            Reader reader = null;
            try {
                URL res = getClass().getResource("/fct/cs/sql/db.sql");
                File file = Paths.get(res.toURI()).toFile();
                String absolutePath = file.getAbsolutePath();
                System.out.println(absolutePath);

                reader = new BufferedReader(new FileReader(absolutePath));
                sr.runScript(reader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            //Running the script

        }
    }

    public void centerImage() {
        Image img = imgX.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imgX.getFitWidth() / img.getWidth();
            double ratioY = imgX.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;
            System.out.println("Load image");
            imgX.setX((imgX.getFitWidth() - w) / 2);
            imgX.setY((imgX.getFitHeight() - h) / 2);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img1 = new Image(String.valueOf(getClass().getResource("/images/BookStore.png")));
        imgX.setImage(img1);
        centerImage();
    }
}
