package fct.cs.Login;

import com.jfoenix.controls.JFXComboBox;
import fct.cs.controllers.mainPageController;
import fct.cs.dbUtil.DatabaseConnector;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXStageDialog;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.enums.DialogType;
import io.github.palexdev.materialfx.utils.BindingUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangePassword implements Initializable {

    public ImageView imgX;
    ObservableList<String> questionBoxList = FXCollections.observableArrayList("What's your pet's name?","What's your favorite food?","Who was your childhood hero?");


    @FXML
    private MFXTextField fUsername;

    @FXML
    private JFXComboBox fQuesBox;

    @FXML
    private MFXTextField fAns;

    @FXML
    private MFXPasswordField fPasswordGet;

    @FXML
    private MFXPasswordField fPasswordCheck;

    @FXML
    private Label fErrLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        javafx.scene.image.Image img1 = new Image(String.valueOf(getClass().getResource("/images/BookStore.png")));
        imgX.setImage(img1);

        fQuesBox.setItems(questionBoxList);

        fUsername.setValidated(true);
        fUsername.getValidator().add(
                BindingUtils.toProperty(
                        fUsername.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your Username"
        );

        //check Answer validation
        fAns.setValidated(true);
        fAns.getValidator().add(
                BindingUtils.toProperty(
                        fAns.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your Answer"
        );

        fPasswordGet.setValidated(true);
        fPasswordGet.getValidator().add(
                BindingUtils.toProperty(
                        fPasswordGet.passwordProperty().length().greaterThanOrEqualTo(8)
                ),
                "Must be at least 8 characters long"
        );
        fPasswordGet.getValidator().add(BindingUtils.toProperty(
                        Bindings.createBooleanBinding(
                                () -> fPasswordGet.getPassword().matches(".*\\d.*"),
                                fPasswordGet.passwordProperty()
                        )
                ),
                "Must contain at least one digit"
        );
        fPasswordGet.getValidator().add(BindingUtils.toProperty(
                        Bindings.createBooleanBinding(
                                () -> StringUtils.containsAny(fPasswordGet.getPassword(),
                                        "", "?", "!", "@", "(", ")", "[", "]", "{", "}", "-", "_"),
                                fPasswordGet.passwordProperty()
                        )
                ),
                "Must contain at least one among these: ?!@()[]{}-_"
        );

    }

    private Connection conn;

    @FXML
    public void changeOnAction(ActiveEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {

        String username = fUsername.getText().trim();
        String answer = fAns.getText().trim();
        String passGet = fPasswordGet.getText().trim();
        String passCheck = fPasswordCheck.getText().trim();
        String question = null;
        try {
            question = fQuesBox.getValue().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fPasswordCheck.setValidated(true);
        fPasswordCheck.getValidator().add(
                BindingUtils.toProperty(
                        fPasswordCheck.passwordProperty().isEqualTo(fPasswordGet.getPassword())
                ),
                "Passwords dont match"
        );

        if (fUsername.isValid() && fAns.isValid() && fPasswordGet.isValid() && fPasswordCheck.isValid() && question != null) {

            System.out.println(fPasswordGet.passwordProperty().getValue());
            System.out.println(fPasswordGet.passwordProperty().getValueSafe());
            System.out.println("Form OK!");

            try {
                this.conn = DatabaseHandler.getInstance().getConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PasswordSecure encrypt = new PasswordSecure();
            PasswordSecure decrypt = new PasswordSecure();
            String passwordEncrypt = null, answerDecrypt = null;

            //Encrypt password
            try {
                passwordEncrypt = encrypt.encryptString(passGet);
                System.out.println("Password Encrypted");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            try {

                //sql query for getting username as telnum
                PreparedStatement ps_1 = conn.prepareStatement("select * from login where telnum=?");
                //sql query for getting username as email
                PreparedStatement ps_2 = conn.prepareStatement("select * from login where email=?");

                ps_1.setString(1, username);
                ps_2.setString(1, username);

                ResultSet rs_1 = ps_1.executeQuery();
                ResultSet rs_2 = ps_2.executeQuery();

                if (rs_1.next()) {
                    String storedAnswer = rs_1.getString("ans");
                    Integer id = rs_1.getInt(1);
                    boolean matchedAnswer = decrypt.validateString(answer, storedAnswer);
                    boolean changePass = correctAns(passwordEncrypt, id);

                    if (matchedAnswer == true) {

                        if(changePass == true) {

                            try {

                                Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/login.fxml"));
                                Scene scene = new Scene(view);
                                System.out.println("Load Login Page");
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(scene);
                                window.show();

                            }
                        }


                    } else {


                    }

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();

            }
        } else {

            System.out.println("Check Again");
            MFXStageDialog dialog = new MFXStageDialog(DialogType.WARNING, "Fill Forget-Password Form", "Please fill all the fields in the above form");
            dialog.show();

        }
    }

    public boolean correctAns(String password, int iD) throws NoSuchAlgorithmException, InvalidKeySpecException {


        try {
            this.conn = DatabaseHandler.getInstance().getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String updateQuery = "UPDATE login set password = ? where id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setString(1,password);
            preparedStatement.setInt(2,iD);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }

            @FXML
    public void hyperlinkRegister(ActionEvent event)throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("/fct/cs/Register.fxml"));
        Scene scene = new Scene(view);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



}
