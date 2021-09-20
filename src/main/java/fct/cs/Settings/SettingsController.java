package fct.cs.Settings;

import com.jfoenix.controls.JFXButton;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.BindingUtils;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import org.apache.ibatis.jdbc.ScriptRunner;

public class SettingsController implements Initializable {
    public HBox progressBox;
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

    Connection conn = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userTxt.setValidated(true);
        userTxt.getValidator().add(
                BindingUtils.toProperty(
                        userTxt.textProperty().length().isNotEqualTo(0)
                ),
                "Cannot be empty"
        );

        dbURL.setValidated(true);
        dbURL.getValidator().add(
                BindingUtils.toProperty(
                        dbURL.textProperty().length().isNotEqualTo(0)
                ),
                "Enter URL"
        );

        dbURL.getValidator().add(BindingUtils.toProperty(
                        Bindings.createBooleanBinding(
                                () -> dbURL.getText().matches("(^[a-z]*):([0-9]*)"),
                                dbURL.textProperty()
                        )
                ),
                "Invalid URL format"
        );

        Image img1 = new Image(String.valueOf(getClass().getResource("/images/BookStore.png")));
        imgX.setImage(img1);
        centerImage();
    }

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

    public void submitInfo(ActionEvent actionEvent) {


        MFXProgressBar pb = new MFXProgressBar(0);
        progressBox.getChildren().add(pb);
        Task<String> task = new Task<String>() {

            @Override
            protected String call() throws IOException {
                updateMessage("Creating Settings");
                updateProgress(0.2d, 1.0d);

                String username = userTxt.getText().trim();
                String password = passTxt.getText().trim();
                String urlName = dbURL.getText().trim();

                Properties configFile = new Properties();

                try {
                    File myObj = new File("config.xml");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }

                    configFile.setProperty("url", urlName);
                    OutputStream o = new FileOutputStream("config.xml");
                    configFile.storeToXML(o,"Test Write");
                } finally {}

                updateMessage("Settings Created");
                updateProgress(1.0d, 1.0d);
                return "OK";
            }

            @Override
            protected void succeeded() {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Success");
                alert1.showAndWait();
                System.exit(0);
            }

            @Override
            protected void failed() {
                updateProgress(0.0d, 1.0d);
                getException().printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, getException().toString());
                alert.showAndWait();
                System.exit(1);
            }
        };

//        bottomControls.visibleProperty().bind( task.runningProperty() );
        pb.progressProperty().bind(task.progressProperty());
//        messageLabel.textProperty().bind( task.messageProperty() );

        new Thread(task).start();

    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);

    }


    public void centerImage() {
        Image img = imgX.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imgX.getFitWidth() / img.getWidth();
            double ratioY = imgX.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
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
}
