package fct.cs.Profile;

import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    private static int currentEmployeeId;

    private static ProfileManager.UserDetails currentUser;

    public ImageView profile;
    public Label employeeID;
    public MFXButton saveBtn;
    public Label genderLabel;

    @FXML
    private  Label nameLabel;

    @FXML
    private  Label locationLabel;

    @FXML
    private  JFXTextField fName;

    @FXML
    private  JFXTextField email;

    @FXML
    private  JFXTextField location;

    @FXML
    private  JFXTextField lName;

    @FXML
    private  JFXTextField phone;

    @FXML
    private  JFXTextField salary;


    @FXML
    void enableEdit(ActionEvent event) {
        setEdit(true);
    }

    private void setEdit(boolean b) {
        fName.setEditable(b);
        lName.setEditable(b);
        email.setEditable(b);
        phone.setEditable(b);
        location.setEditable(b);
        saveBtn.setDisable(!b);
    }

    @FXML
    void saveChanges(ActionEvent event) {
        setEdit(false);
        ProfileManager.UserDetails user = getUserDetailsFromForm();
        ProfileManager.updateUserDetails(user);
        fillForm(ProfileManager.getUserDetails(user.getEmp_id()));
    }

    private ProfileManager.UserDetails getUserDetailsFromForm() {

        return new ProfileManager.UserDetails(currentUser.getEmp_id(),currentUser.getSalary(),fName.getText(),lName.getText(),currentUser.getGender(),location.getText(),email.getText(),phone.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img1 = new Image(String.valueOf(getClass().getResource("/images/avatar.png")));
        profile.setImage(img1);
    }

    public void setEmployeeId(int id) {
        currentEmployeeId = id;
        fillForm(ProfileManager.getUserDetails(id));
    }

    private void fillForm(ProfileManager.UserDetails userDetails) {
        currentUser = userDetails;
        fName.setText(userDetails.getFname());
        lName.setText(userDetails.getLname());
        email.setText(userDetails.getEmail());
        phone.setText(userDetails.getTelnum());
        location.setText(userDetails.getLocation());
        salary.setText(String.valueOf(userDetails.getSalary()));

        employeeID.setText("Employee ID: " + userDetails.getEmp_id());
        locationLabel.setText(userDetails.getLocation());
        nameLabel.setText(userDetails.getFname() + " " + userDetails.getLname());
        genderLabel.setText(userDetails.getGender());
    }
}
