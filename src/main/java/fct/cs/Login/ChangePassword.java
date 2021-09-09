package fct.cs.Login;

import com.jfoenix.controls.JFXComboBox;
import fct.cs.dbUtil.DatabaseConnector;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.BindingUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
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
    public void changeOnAction(ActiveEvent event) {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        PasswordSecure decrypt = new PasswordSecure();

        try {
            this.conn = databaseConnector.getConn();

            String username = fUsername.getText().trim();
            String question = fQuesBox.getValue().toString();
            String answer = fAns.getText().trim();
            String passGet = fPasswordGet.getText().trim();
            String passCheck = fPasswordCheck.getText().trim();




            /*if (fName.isEmpty() && lName.isEmpty()) {
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
            }else {*/

            //sql query for getting username as telnum
            PreparedStatement ps1 = conn.prepareStatement("select * from login where telnum=?");
            //sql query for getting username as email
            PreparedStatement ps2 = conn.prepareStatement("select * from login where email=?");

            ps1.setString(1, username);
            ps2.setString(1, username);

            ResultSet rs_1 = ps1.executeQuery();
            ResultSet rs_2 = ps2.executeQuery();

            if(rs_1.next()){

                String storedAnswer = rs_1.getString("ans");
                Integer id = rs_1.getInt(1);
                boolean matched = decrypt.validateString(answer, storedAnswer );
                System.out.println(matched);
                if(matched == true){
                    boolean changePass = correctAns(true, id);
                    System.out.println(changePass + ",Change Password");
                }else{
                    fErrLabel.setText("Wrong Answer, Please try again..");
                    System.out.println("Invalid Input");
                }

            }else if(rs_2.next()){
                String storedAnswer = rs_2.getString("ans");
                Integer id = rs_2.getInt(1);
                boolean matched = decrypt.validateString(answer, storedAnswer );
                System.out.println(matched);
                if(matched == true){
                    boolean changePass = correctAns(true, id);
                    System.out.println(changePass + ",Change Password");
                }else{
                    fErrLabel.setText("Wrong Answer, Please try again..");
                    System.out.println("Invalid Input");
                }

            } else {
                System.out.println("Not Found");
                fErrLabel.setText("Invalid credentials. Please try again..");
            }



            //}

        }catch(Exception ex){
            System.out.println("error" + ex.toString());
        }

    }


    public boolean updatePassword(String updatePassword, int iD){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.conn = databaseConnector.getConn();

        String updateQuery = "UPDATE login set password = ? where id = ?";
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setString(1,updatePassword);
            preparedStatement.setInt(2,iD);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count > 0;
    }


    private boolean correctAns(boolean matched, int id) {

        Integer iD = id;


        PasswordSecure encrypt = new PasswordSecure();

        String passGet = fPasswordGet.getText().trim();
        String passCheck = fPasswordCheck.getText().trim();

        try {

            if(passGet == passCheck){

                //Encrypt password
                String passwordEncrypt = encrypt.encryptString(passGet);
                System.out.println("Password Encrypted");

                boolean update  = updatePassword(passwordEncrypt, iD);
                String eg = update.
                return update > 0;

            }else{
                fPasswordCheck.setValidated(true);
                fPasswordCheck.getValidator().add(
                        BindingUtils.toProperty(
                                fPasswordCheck.passwordProperty().isEqualTo(fPasswordGet.getPassword())
                        ),
                        "You need enter same password"
                );
            }
        }catch(Exception ex){
            System.out.println("error" + ex.toString());
        }






    }


}
