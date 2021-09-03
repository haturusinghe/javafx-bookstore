package fct.cs.Login;





        import java.io.IOException;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;

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
    private Button loginBtn;

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


    Connection conn;
    PreparedStatement pst;
    ResultSet rs;



    @FXML
    void login(ActionEvent event) throws IOException {
        DatabaseConnector connect = new DatabaseConnector();
        try {
            Connection conn = connect.getConn();

            String username = txtusername.getText().trim();
            String password = txtpass.getText().trim();

            if(username.isEmpty() || password.isEmpty()){
                errorMsg.setText("Please insert username and password");
            }
            else
            {

                PreparedStatement ps = conn.prepareStatement("select * from admin where telnum=?"
                        + " and password=?");
                ps.setString(1,txtusername.getText().trim() );
                ps.setString(2, txtpass.getText().trim());

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Parent view3=FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
                    Scene scene3=new Scene(view3);
                    Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene3);
                    window.show();
                }

                else if{

                }

                else {
                    errorMsg.setText("Invalid credentials. Please try again");
                }
            }
        }
        catch(Exception ex){
            System.out.println("error" + ex.toString());
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
