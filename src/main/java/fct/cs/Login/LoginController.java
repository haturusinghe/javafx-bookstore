package fct.cs.Login;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import fct.cs.commonUtil.AppUtils;
import fct.cs.commonUtil.NotificationCreator;
import fct.cs.controllers.mainPageController;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.BindingUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.image.Image;

/**
 *
 * @author hoxha
 */
public class LoginController implements Initializable {

    public ImageView imgX;
    @FXML
    private Label label;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorMsg;

    @FXML
    private MFXPasswordField txtPass;

    @FXML
    private Button forgotBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Hyperlink registerOnAction;

    @FXML
    private Hyperlink fgwPass;

    private Connection conn;
    PreparedStatement pst;
    ResultSet rs;

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

    @FXML
    void loginOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        if (!txtUsername.isValidated() && !txtPass.isValidated()) {
            txtUsername.setValidated(true);
            txtUsername.getValidator().add(
                    BindingUtils.toProperty(
                            txtUsername.textProperty().length().isNotEqualTo(0)
                    ),
                    "You need enter username"
            );

            txtPass.setValidated(true);
            txtPass.getValidator().add(
                    BindingUtils.toProperty(
                            txtPass.textProperty().length().isNotEqualTo(0)
                    ),
                    "You need enter password"
            );
        }

        PasswordSecure decrypt = new PasswordSecure();

        try {
            this.conn = DatabaseHandler.getInstance().getConn();
            String username = txtUsername.getText().trim();
            String password = txtPass.getText().trim();

            if (txtUsername.isValid() || txtPass.isValid()) {
                //sql query for getting username as telnum
                PreparedStatement ps_1 = conn.prepareStatement("select * from login where telnum=? OR email=?");

                ps_1.setString(1, username);
                ps_1.setString(2, username);

                ResultSet rs_1 = ps_1.executeQuery();


                if(rs_1.next()){
                    String storedPassword = rs_1.getString("password");
                    boolean isManger = rs_1.getBoolean("isManager");
                    boolean matchedPassword = decrypt.validateString(password, storedPassword );

                    if(matchedPassword == true) {
                        NotificationCreator.showSuccessBottomRight("Success","Logging In...");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/main-dash.fxml"));
                        Parent view = loader.load();
                        mainPageController controller = loader.getController();
                        controller.setManager(isManger);
                        System.out.println("check");
                        Scene scene = new Scene(view);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        AppUtils.enableDrag(scene,window);
                        window.setScene(scene);
                        window.show();
                    }else{
                        NotificationCreator.showErrorBottomRight("Error Loggin In","Incorrect Password");
                    }

                }else{
                    NotificationCreator.showErrorBottomRight("Error Loggin In","User Not Found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            NotificationCreator.showErrorBottomRight("Error Loggin In",e.getMessage());
        }

    }

    //Load register.fxml
    @FXML
    public void registerOnAction(ActionEvent event1)throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/fxml/login/register.fxml"));
        Scene scene = new Scene(view);
        System.out.println("Load register page");
        Stage window = (Stage)((Node)event1.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    //Load ChangePassword.fxml
    @FXML
    public void forgotOnAction(ActionEvent event2)throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/fxml/login/change-password.fxml"));
        Scene scene = new Scene(view);
        System.out.println("Load change-password page");
        Stage window = (Stage)((Node)event2.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void updateAlert(String user){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login successfully.");
        alert.setHeaderText(null);
        alert.setContentText(user + " has logged in");
        alert.showAndWait();
    }

    private void errorAlert(String e){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!.");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}