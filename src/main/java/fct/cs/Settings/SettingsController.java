package fct.cs.Settings;

import com.jfoenix.controls.JFXButton;
import fct.cs.commonUtil.AppUtils;
import fct.cs.commonUtil.NotificationCreator;
import io.github.palexdev.materialfx.beans.NumberRange;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.ibatis.jdbc.ScriptRunner;

public class SettingsController implements Initializable {
    public HBox progressBox;

    public Label conStatus;

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

    private boolean writeToFile = true;

    Connection conn = null;
    MFXProgressSpinner pb = new MFXProgressSpinner(0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setupForm();
        loadImage();

        Task<String> checkSettings = new Task<String>() {
            String msg;

            @Override
            protected String call() throws IOException, SQLException, InterruptedException, InvalidPropertiesFormatException {
                updateMessage("Checking for Settings File");
                updateProgress(0.1d, 1.0d);
                Properties configFile = new Properties();
                try {
                    File myObj = new File("config.xml");
                    if (myObj.createNewFile()) {
                        writeToFile = true;
                        msg = ("File created: " + myObj.getName() + "\nEnter Settings and Submit");
                        lockForm(false);
                        updateMessage("Enter Settings...");
                    } else {
                        InputStream f = new FileInputStream("config.xml");
                        configFile.loadFromXML(f);
                        f.close();
                        updateProgress(0.3d, 1.0d);

                        String url = configFile.getProperty("url");
                        String user = configFile.getProperty("username");
                        String pass = configFile.getProperty("password");
                        updateProgress(0.4d, 1.0d);

                        userTxt.setText(user);
                        dbURL.setText(url);
                        passTxt.setText(pass);

                        lockForm(false);

                        msg = "File already exists.\nRead Settings from config.xml";

                        submitBtn.setText("Connect to Database");
                        updateProgress(0.5d, 1.0d);
                        writeToFile = false;
                    }

                } finally {
                }
                updateProgress(1.0d, 1.0d);
                return "OK";
            }

            @Override
            protected void succeeded() {
                lockForm(false);
                NotificationCreator.showSuccessBottomRight("Success", msg);
            }

            @Override
            protected void failed() {
                System.out.println("NO nO");
                lockForm(false);
                updateMessage("Error Reading Settings");
                updateProgress(0.0d, 1.0d);
                getException().printStackTrace();
                NotificationCreator.showErrorBottomRight("Error", "Error Reading settings");
            }
        };

        pb.visibleProperty().bind(checkSettings.runningProperty());
        pb.progressProperty().bind(checkSettings.progressProperty());
        conStatus.textProperty().bind(checkSettings.messageProperty());
        new Thread(checkSettings).start();
    }

    private void lockForm(boolean b) {
        userTxt.setDisable(b);
        passTxt.setDisable(b);
        dbURL.setDisable(b);
        submitBtn.setDisable(b);
    }

    private void loadImage() {
        Task loadImg = new Task() {
            @Override
            protected Object call() throws Exception {
                Image img1 = new Image(String.valueOf(getClass().getResource("/images/BookStore.png")));
                imgX.setImage(img1);
                centerImage();
                return null;
            }
        };
        new Thread(loadImg).start();
    }

    private void setupForm() {
        conStatus.setVisible(false);

        pb.getRanges1().add(NumberRange.of(0.0, 0.30));
        pb.getRanges2().add(NumberRange.of(0.31, 0.60));
        pb.getRanges3().add(NumberRange.of(0.61, 1.0));
        progressBox.getChildren().add(pb);
        pb.setVisible(false);

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
    }

    public void submitInfo(ActionEvent actionEvent) {
        Task<Parent> createDBTask = new Task<Parent>() {
            Parent view;

            @Override
            protected Parent call() throws Exception {
                updateProgress(0.0d, 1.0d);

                String username = userTxt.getText().trim();
                String password = passTxt.getText().trim();
                String urlName = "jdbc:mysql://" + dbURL.getText().trim();
                conn = DriverManager.getConnection(urlName,username,password);

                if (conn != null) {
                    updateMessage("Connected to Database");
                    updateProgress(0.3d, 1.0d);
                    ScriptRunner sr = new ScriptRunner(conn);
                    //Creating a reader object
                    Reader reader = null;
                    try {
//                        URL res = getClass().getResource("/fct/cs/sql/db.sql");
                        File file = new File("db.sql");
                        String absolutePath = file.getAbsolutePath();
                        System.out.println(absolutePath);
                        updateMessage("Read from File");
                        updateProgress(0.4d, 1.0d);
                        reader = new BufferedReader(new FileReader(absolutePath));
//                        InputStream res = getClass().getResourceAsStream("db.sql");
//                        reader = new InputStreamReader(res);
                        updateMessage("Writing to Database");
                        updateProgress(0.5d, 1.0d);
                        sr.runScript(reader);
                        updateProgress(0.8d, 1.0d);
                        NotificationCreator.showSuccessBottomRight("Success", "Created Database");
                        Properties configFile = new Properties();
                        configFile.setProperty("writeToDb", LocalDate.now().toString());
                        OutputStream o = new FileOutputStream("config.xml");
                        configFile.storeToXML(o, "Successfully written to database");
                        o.close();
                        view = FXMLLoader.load(getClass().getResource("/fct/cs/fxml/login/login.fxml"));
                    } finally {

                    }
                } else {
                    updateMessage("Failed to connect to database");
                    updateProgress(0.0d, 1.0d);
                    throw new SQLException("Cant connect to Database");
                }

                return view;
            }

            @Override
            protected void succeeded() {
//                updateMessage("Connected to Database");
                updateProgress(1d, 1.0d);
//                NotificationCreator.showSuccessBottomRight("Success","Created Database");
            }

            @Override
            protected void failed() {
                submitBtn.setDisable(false);
                updateProgress(0.0d, 1.0d);
                NotificationCreator.showErrorBottomRight("Error", getException().toString());
            }
        };

        createDBTask.setOnSucceeded(e -> {
            Scene scene = new Scene(createDBTask.getValue());
            NotificationCreator.showSuccessBottomRight("Loading...", "Loading Login Page");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            AppUtils.enableDrag(scene,window);
            window.setScene(scene);
            window.show();
            System.out.println("thread complete");
        });

        Task<String> writeTask = new Task<String>() {
            @Override
            protected String call() throws IOException, SQLException {
                updateMessage("Creating Settings");
                updateProgress(0.1d, 1.0d);

                String username = userTxt.getText().trim();
                String password = passTxt.getPassword().trim();
                String urlName = dbURL.getText().trim();

                Properties configFile = new Properties();

                try {
                    updateProgress(0.3d, 1.0d);

                    configFile.setProperty("url", urlName);
                    configFile.setProperty("username", username);
                    configFile.setProperty("password", password);
                    updateProgress(0.5d, 1.0d);

                    OutputStream o = new FileOutputStream("config.xml");
                    updateProgress(0.6d, 1.0d);
                    configFile.storeToXML(o, "Test Write");
                    updateProgress(0.9d, 1.0d);
                    o.close();

                } finally {

                }

                updateMessage("Settings Created");
                updateProgress(1.0d, 1.0d);
                return "OK";
            }

            @Override
            protected void succeeded() {
                NotificationCreator.showSuccessBottomRight("Success", "Settings Written Successfully to config.xml");
            }

            @Override
            protected void failed() {
                updateMessage(getException().toString());
                updateProgress(0.0d, 1.0d);
                getException().printStackTrace();
                NotificationCreator.showErrorBottomRight("Error", "Error Writing to config.xml");
            }
        };

        if (userTxt.isValid() && dbURL.isValid()) {
            submitBtn.setDisable(true);
            new Thread(writeTask).start();
            new Thread(createDBTask).start();
            pb.visibleProperty().unbind();
            pb.setVisible(true);
            pb.progressProperty().unbind();
            pb.progressProperty().bind(createDBTask.progressProperty());
            conStatus.textProperty().unbind();
            conStatus.textProperty().bind(createDBTask.messageProperty());

        } else {
            NotificationCreator.showErrorBottomRight("Invalid Input", "Check again");
        }


    }

    @FXML
    void close() {
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
