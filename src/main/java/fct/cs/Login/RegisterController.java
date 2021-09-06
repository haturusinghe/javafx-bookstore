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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class RegisterController implements Initializable {

    @FXML
    private MFXTextField firstName;

    @FXML
    private MFXTextField lastName;

    @FXML
    private MFXTextField telNum;

    @FXML
    private MFXDatePicker datePicker;

    @FXML
    private MFXTextField emailAddress;

    @FXML
    private MFXPasswordField passwordGet;

    @FXML
    private MFXPasswordField passwordCheck;

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

        BooleanProperty checkboxValidation = BindingUtils.toProperty(
                Bindings.createBooleanBinding(
                        () -> checkBox.isSelected(),
                        checkBox.selectedProperty()
                )
        );


        //errLabel.getValidator().add(checkboxValidation, "Checkbox must be selected");
        //firstName.getValidator().add(datePickerValidation, "Selected date must be 03/10/1911");
        //validated.setValidated(true);
        //validated.setIcon(new MFXFontIcon("mfx-variant7-mark", 16, Color.web("#8FF7A7")));
        //validated.getIcon().visibleProperty().bind(validated.getValidator().validProperty());

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

    @FXML
    public void signOnAction(javafx.event.ActionEvent event) throws IOException{

        DatabaseConnector databaseConnector = new DatabaseConnector();

        try {
            this.conn = databaseConnector.getConn();

            String fName = firstName.getText().trim();
            String lName = lastName.getText().trim();
            String pNum = telNum.getText().trim();
            String email = emailAddress.getText().trim();
            String passGet = passwordGet.getText().trim();
            String passCheck = passwordCheck.getText().trim();
            String dob = datePicker.getTypeSelector();

            if (fName.isEmpty()){
                firstName.setValidated(true);
                firstName.getValidator().add(
                        BindingUtils.toProperty(
                                firstName.textProperty().isEmpty()
                        ),
                        "You Must be fill this"
                );
            }

            /*String username = firstName.getText().trim();
            //String password = txtPass.getText().trim();

            if(username.isEmpty() || password.isEmpty()){
                //errorMsg.setText("Please insert username and password");
            }
            else
            {
                //sql query for getting username as telnum
                //PreparedStatement ps_1 = conn.prepareStatement("select * from login where telnum=?"
                        + " and password=?");
                //sql query for getting username as email
                //PreparedStatement ps_2 = conn.prepareStatement("select * from login where email=?"
                        + " and password=?");

                //ps_1.setString(1,txtUsername.getText().trim() );
                //ps_1.setString(2, txtPass.getText().trim());

                //ps_2.setString(1,txtUsername.getText().trim() );
                //ps_2.setString(2, txtPass.getText().trim());

                //ResultSet rs_1 = ps_1.executeQuery();
                //ResultSet rs_2 = ps_2.executeQuery();

                //if(rs_1.next() || rs_2.next()){
                    System.out.println("Found");
                    Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/Register.fxml"));
                    Scene scene = new Scene(view);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();


                }

                else {
                    System.out.println("Not Found");
                    //errorMsg.setText("Invalid credentials. Please try again");
                }
            }*/
        }catch(Exception ex){
            System.out.println("error" + ex.toString());
        }

    }


}
