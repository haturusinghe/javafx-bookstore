package fct.cs.Login;





        import java.io.IOException;
        import java.net.URL;
        import java.sql.*;
        import java.util.ResourceBundle;

        import com.jfoenix.controls.JFXButton;
        import fct.cs.dbUtil.DatabaseConnector;
        import io.github.palexdev.materialfx.controls.MFXPasswordField;
        import io.github.palexdev.materialfx.controls.MFXTextField;
        import javafx.application.Platform;
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
        import javafx.scene.image.ImageView;

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


    private Connection conn;
    private DatabaseConnector databaseConnector;
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
            System.out.println("yo");
            imgX.setX((imgX.getFitWidth() - w) / 2);
            imgX.setY((imgX.getFitHeight() - h) / 2);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img1 = new Image(String.valueOf(getClass().getResource("/fct/cs/book.png")));
        imgX.setImage(img1);
        centerImage();
    }


    @FXML
    void loginOnAction(ActionEvent event) throws IOException {


        DatabaseConnector databaseConnector = new DatabaseConnector();

        try {
            this.conn = databaseConnector.getConn();

            String username = txtUsername.getText().trim();
            String password = txtPass.getText().trim();

            if(username.isEmpty() || password.isEmpty()){
//                errorMsg.setText("Please insert username and password");
            }
            else
            {
                //sql query for getting username as telnum
                PreparedStatement ps_1 = conn.prepareStatement("select * from login where telnum=?"
                        + " and password=?");
                //sql query for getting username as email
                PreparedStatement ps_2 = conn.prepareStatement("select * from login where email=?"
                        + " and password=?");

                ps_1.setString(1,txtUsername.getText().trim() );
                ps_1.setString(2, txtPass.getText().trim());

                ps_2.setString(1,txtUsername.getText().trim() );
                ps_2.setString(2, txtPass.getText().trim());

                ResultSet rs_1 = ps_1.executeQuery();
                ResultSet rs_2 = ps_2.executeQuery();

                if(rs_1.next() || rs_2.next()){
                    System.out.println("Found");
                    Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/Register.fxml"));
                    Scene scene = new Scene(view);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();


                }

                else {
                    System.out.println("Not Found");
//                    errorMsg.setText("Invalid credentials. Please try again");
                }
            }
        }catch(Exception ex){
//            System.out.println("error" + ex.toString());
            ex.printStackTrace();
        }
    }

    @FXML
    public void signupScene(ActionEvent event)throws IOException{
        Parent view3=FXMLLoader.load(getClass().getResource("SignupPage.fxml"));
        Scene scene3=new Scene(view3);
        Stage window =new Stage();
        window.setScene(scene3);
        window.show();
    }

    @FXML
    public void forgotPsw(ActionEvent event)throws IOException{
        Parent view4=FXMLLoader.load(getClass().getResource("GetPassword.fxml"));
        Scene scene4=new Scene(view4);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene4);
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

}
