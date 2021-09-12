package fct.cs.Login;

import com.jfoenix.controls.JFXButton;
//import com.mysql.cj.conf.BooleanProperty;
import com.jfoenix.controls.JFXComboBox;
import fct.cs.dbUtil.DatabaseConnector;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.enums.DialogType;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import io.github.palexdev.materialfx.utils.BindingUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    public ImageView imgX;
    ObservableList<String> questionBoxList = FXCollections.observableArrayList("What's your pet's name?","What's your favorite food?","Who was your childhood hero?");


    @FXML
    private MFXTextField firstName;

    @FXML
    private MFXTextField lastName;

    @FXML
    private MFXTextField telNum;

    @FXML
    private MFXTextField emailAddress;

    @FXML
    private MFXPasswordField passwordGet;

    @FXML
    private MFXPasswordField passwordCheck;

    @FXML
    private JFXComboBox quesBox;

    @FXML
    private MFXTextField ansField;

    @FXML
    private MFXCheckbox checkBox;

    @FXML
    private Label errLabel;

    @FXML
    private JFXButton signBtn;

    @FXML
    private Hyperlink hyperlinkLogin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        javafx.scene.image.Image img1 = new Image(String.valueOf(getClass().getResource("/images/BookStore.png")));
        imgX.setImage(img1);

        quesBox.setItems(questionBoxList);

        ansField.setValidated(true);
        ansField.getValidator().add(
                BindingUtils.toProperty(
                        ansField.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter the Answer"
        );

        //check firstname validation
        firstName.setValidated(true);
        firstName.getValidator().add(
                BindingUtils.toProperty(
                        firstName.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your first name"
        );

        //check lastname validation
        lastName.setValidated(true);
        lastName.getValidator().add(
                BindingUtils.toProperty(
                        lastName.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your last name"
        );

        //check telnum validation
        telNum.setValidated(true);
        telNum.getValidator().add(
                BindingUtils.toProperty(
                        telNum.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your Phone Number"
        );

        //check email validation
        emailAddress.setValidated(true);
        emailAddress.getValidator().add(
                BindingUtils.toProperty(
                        emailAddress.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your email address"
        );

        emailAddress.setValidated(true);
        emailAddress.getValidator().add(BindingUtils.toProperty(
                Bindings.createBooleanBinding(
                        () -> StringUtils.containsAll(emailAddress.getText(),
                                "",  "@", "."),
                        emailAddress.textProperty()
                )
                ),
                "You must enter valid Email Address"
        );


        passwordGet.setValidated(true);
        passwordGet.getValidator().add(
                BindingUtils.toProperty(
                        passwordGet.passwordProperty().length().isNotEqualTo(0)
                ),
                "You need enter your password"
        );



        passwordGet.setValidated(true);
        passwordGet.getValidator().add(
                BindingUtils.toProperty(
                        passwordGet.passwordProperty().length().greaterThanOrEqualTo(8)
                ),
                "Must be at least 8 characters long"
        );
        passwordGet.getValidator().add(BindingUtils.toProperty(
                        Bindings.createBooleanBinding(
                                () -> passwordGet.getPassword().matches(".*\\d.*"),
                                passwordGet.passwordProperty()
                        )
                ),
                "Must contain at least one digit"
        );
        passwordGet.getValidator().add(BindingUtils.toProperty(
                        Bindings.createBooleanBinding(
                                () -> StringUtils.containsAny(passwordGet.getPassword(),
                                        "", "?", "!", "@", "(", ")", "[", "]", "{", "}", "-", "_"),
                                passwordGet.passwordProperty()
                        )
                ),
                "Must contain at least one among these: ?!@()[]{}-_"
        );


        passwordCheck.setValidated(true);
        passwordCheck.getValidator().add(
                BindingUtils.toProperty(
                        passwordCheck.passwordProperty().length().isNotEqualTo(0)
                ),
                "You need re-enter your password"
        );

    }

    private Connection conn;
    //private DatabaseConnector databaseConnector;

    @FXML
    public void signOnAction(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {

        DatabaseConnector databaseConnector = new DatabaseConnector();

        try {
            this.conn = databaseConnector.getConn();

            String fName = firstName.getText().trim();
            String lName = lastName.getText().trim();
            String pNum = telNum.getText().trim();
            String email = emailAddress.getText().trim();
            String passGet = passwordGet.getText().trim();
            String passCheck = passwordCheck.getText().trim();
            String answer = ansField.getText().trim();
            String question = quesBox.getValue().toString();


            if (fName.isEmpty() && lName.isEmpty()) {
                errLabel.setText("Please complete all the fills");
            } else if (fName.isEmpty() || lName.isEmpty() || pNum.isEmpty() || email.isEmpty() || passGet.isEmpty() || passCheck.isEmpty() || answer.isEmpty()) {
                errLabel.setText("");
            }else if(passGet.length()<8) {
                passwordGet.setValidated(true);
                passwordGet.getValidator().add(
                        BindingUtils.toProperty(
                                passwordGet.passwordProperty().length().greaterThanOrEqualTo(8)
                        ),
                        "Must be at least 8 characters long"
                );
            }else if(passGet != passCheck){
                passwordCheck.setValidated(true);
                passwordCheck.getValidator().add(
                        BindingUtils.toProperty(
                                passwordCheck.passwordProperty().isEqualTo(passwordGet.getPassword())
                        ),
                        "You need enter same password"
                );
            }else {


            PasswordSecure encrypt = new PasswordSecure();

            //Encrypt password
            String passwordEncrypt = encrypt.encryptString(passGet);
            System.out.println("Password Encrypted");

            //Encrypt answer
            String answerEncrypt = encrypt.encryptString(answer);
            System.out.println("Answer Encrypted");

            //check phone number from database
            PreparedStatement ps_1 = conn.prepareStatement("select * from login where telnum = ?");
            ps_1.setString(1, pNum);

            //check email address from database
            PreparedStatement ps_2 = conn.prepareStatement("select * from login where email = ?");
            ps_2.setString(1, email);

            ResultSet rs_1 = ps_1.executeQuery();
            ResultSet rs_2 = ps_2.executeQuery();

            if (rs_1.next()) {
                errLabel.setText("Phone number already taken, please enter another phone number");
            } else if (rs_2.next()) {
                errLabel.setText("Email address already taken, please enter another email address");
            } else {


                String sql = "INSERT INTO login (fname, lname, telnum, email, password, ques, ans) values(?,?,?,?,?,?,?)";
                PreparedStatement ps_3 = conn.prepareStatement(sql);

                ps_3.setString(1, fName);
                ps_3.setString(2, lName);
                ps_3.setString(3, pNum);
                ps_3.setString(4, email);
                ps_3.setString(5, passwordEncrypt);
                ps_3.setString(6, question);
                ps_3.setString(7, answerEncrypt);

                ps_3.execute();

                System.out.println("Account successfully registered");
            }
            }

        }catch(Exception ex){
            System.out.println("error" + ex.toString());
        }

    }

    @FXML
    public void hLoginOnAction(ActionEvent event)throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/login.fxml"));
        Scene scene = new Scene(view);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void createAccount(ActionEvent actionEvent) {
        String fName = firstName.getText().trim();
        String lName = lastName.getText().trim();
        String pNum = telNum.getText().trim();
        String email = emailAddress.getText().trim();
        String passGet = passwordGet.getText().trim();
        String passCheck = passwordCheck.getText().trim();
        String answer = ansField.getText().trim();
        String question = null;
        try {
            question = quesBox.getValue().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        passwordCheck.setValidated(true);
        passwordCheck.getValidator().add(
                BindingUtils.toProperty(
                        passwordCheck.passwordProperty().isEqualTo(passwordGet.getPassword())
                ),
                "Passwords dont match"
        );


        if (firstName.isValid() && lastName.isValid() && telNum.isValid() && emailAddress.isValid() &&
                passwordGet.isValid() && passwordCheck.isValid() &&
                ansField.isValid() && question !=null
        ) {
            System.out.println(passwordGet.passwordProperty().getValue());
            System.out.println(passwordGet.passwordProperty().getValueSafe());
            System.out.println("Form OK!");
            DatabaseConnector databaseConnector = new DatabaseConnector();
            this.conn = databaseConnector.getConn();
            PasswordSecure encrypt = new PasswordSecure();
            String passwordEncrypt = null, answerEncrypt = null;
            //Encrypt password
            try {
                passwordEncrypt = encrypt.encryptString(passGet);
                System.out.println("Password Encrypted");
                answerEncrypt = encrypt.encryptString(answer);
                System.out.println("Answer Encrypted");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            try {
                if (passwordEncrypt != null && answerEncrypt != null) {
                    String sql = "INSERT INTO login (fname, lname, telnum, email, password, ques, ans) values(?,?,?,?,?,?,?)";
                    PreparedStatement ps_3 = conn.prepareStatement(sql);

                    ps_3.setString(1, fName);
                    ps_3.setString(2, lName);
                    ps_3.setString(3, pNum);
                    ps_3.setString(4, email);
                    ps_3.setString(5, passwordEncrypt);
                    ps_3.setString(6, question);
                    ps_3.setString(7, answerEncrypt);

                    ps_3.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Account successfully registered");

        } else {
            System.out.println("Check Again");
            MFXStageDialog dialog = new MFXStageDialog(DialogType.WARNING, "Fill Registration Form", "Please fill all the fields in the above form");
            dialog.show();
        }

    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}