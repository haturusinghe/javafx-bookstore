package fct.cs.Login;

import com.jfoenix.controls.JFXComboBox;
import fct.cs.dbUtil.DatabaseConnector;
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

        } else {

            System.out.println("Check Again");
            MFXStageDialog dialog = new MFXStageDialog(DialogType.WARNING, "Fill Forget-Password Form", "Please fill all the fields in the above form");
            dialog.show();

        }
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
