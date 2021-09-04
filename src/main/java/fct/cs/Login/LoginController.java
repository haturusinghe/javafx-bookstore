package fct.cs.Login;





        import java.io.IOException;
        import java.sql.*;

        import com.jfoenix.controls.JFXButton;
        import fct.cs.dbUtil.DatabaseConnector;
        import javafx.application.Platform;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

/**
 *
 * @author hoxha
 */
public class LoginController  {

    @FXML
    private Label label;

    @FXML
    private TextField txtusername;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorMsg;

    @FXML
    private PasswordField txtpass;

    @FXML
    private Button forgotBtn;

    @FXML
    private Button exitBtn;


    private Connection conn;
    private DatabaseConnector databaseConnector;
    PreparedStatement pst;
    ResultSet rs;


    @FXML
    void loginOnAction(ActionEvent event) throws IOException {


        databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();

        String username = txtusername.getText().trim();
        String password = txtpass.getText().trim();
        //sql query for getting username as telnum
        String verifyLogin_1 = "SELECT * FROM admin WHERE telnum ='" + txtusername.getText() + "' AND password ='" + txtpass.getText() + "'";
        //sql query for getting username as telnum
        String verifyLogin_2 = "SELECT * FROM admin WHERE email ='" + txtusername.getText() + "' AND password ='" + txtpass.getText() + "'";

        if(username.isEmpty() || password.isEmpty()){
            errorMsg.setText("Please insert username and password");
        }else{
            try {

                Statement statement_1 = conn.createStatement();
                ResultSet queryResult_1 = statement_1.executeQuery(verifyLogin_1);

                Statement statement_2 = conn.createStatement();
                ResultSet queryResult_2 = statement_2.executeQuery(verifyLogin_2);

                while (queryResult_1.next() || queryResult_2.next()){

                    if (queryResult_1.getInt(1) == 1 || queryResult_2.getInt(1) == 1){
                        errorMsg.setText("You are login");
                    }else {
                        errorMsg.setText("Invalid credentials. Please try again");
                    }
                }

            }catch(Exception ex){
            System.out.println("error" + ex.toString());
        }
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


}
