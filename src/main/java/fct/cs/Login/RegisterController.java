package fct.cs.Login;

import com.jfoenix.controls.JFXButton;
//import com.mysql.cj.conf.BooleanProperty;
import fct.cs.dbUtil.DatabaseConnector;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.BindingUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
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
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class RegisterController implements Initializable {

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
    private MFXComboBox quesBox;

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

        //quesBox.s("Choose your question");
        quesBox.setItems(questionBoxList);

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



    }

    private Connection conn;
    private DatabaseConnector databaseConnector;
    private static byte[] salt;

    @FXML
    public void signOnAction(ActionEvent event) throws NoSuchAlgorithmException{

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
            //String question = quesBox.getSelectedValue().toString();

            if (fName.isEmpty() && lName.isEmpty()){
                errLabel.setText("You need to fill All the field");
            }

            else if (fName.isEmpty() || lName.isEmpty() || pNum.isEmpty() || email.isEmpty() || passGet.isEmpty() || passCheck.isEmpty() || answer.isEmpty()){

                errLabel.setText("");

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

                //check passwaordget validation
                passwordGet.setValidated(true);
                passwordGet.getValidator().add(
                        BindingUtils.toProperty(
                                passwordGet.passwordProperty().length().isNotEqualTo(0)
                        ),
                        "You need enter your password"
                );

                //check passwaordcheck validation
                passwordCheck.setValidated(true);
                passwordCheck.getValidator().add(
                        BindingUtils.toProperty(
                                passwordCheck.passwordProperty().length().isNotEqualTo(0)
                        ),
                        "You need enter your password Again"
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
                try{
                    this.salt = encrypt.getSalt();
                    String passwordEncrypt = encrypt.getSecurePassword(passGet, salt);
                    System.out.println(passwordEncrypt);

                    errLabel.setText(passwordEncrypt);
                }catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }





                //sql query for getting username as telnum
                //PreparedStatement ps_1 = conn.prepareStatement("select * from login where telnum=?"
                // " and password=?");
                //sql query for getting username as email
                //PreparedStatement ps_2 = conn.prepareStatement("select * from login where email=?"
                //" and password=?");

                //ps_1.setString(1,txtUsername.getText().trim() );
                //ps_1.setString(2, txtPass.getText().trim());

                //ps_2.setString(1,txtUsername.getText().trim() );
                //ps_2.setString(2, txtPass.getText().trim());

                //ResultSet rs_1 = ps_1.executeQuery();
                //ResultSet rs_2 = ps_2.executeQuery();

                //if(rs_1.next() || rs_2.next()){
                   /*ystem.out.println("Found");
                    Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/Register.fxml"));
                    Scene scene = new Scene(view);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();*/


                //se {
                //System.out.println("Not Found");
                //errorMsg.setText("Invalid credentials. Please try again");
                //
            }

        }catch(Exception ex){
            System.out.println("error" + ex.toString());
        }

    }


}
