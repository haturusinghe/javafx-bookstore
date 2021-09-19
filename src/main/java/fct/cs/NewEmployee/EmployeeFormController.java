package fct.cs.NewEmployee;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.utils.BindingUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    public TextField emp_id_txtField;
    public MFXCheckbox managerCheck;
    public TextField location_txtField;
    @FXML
    private HBox name_hbox;

    @FXML
    private TextField fName_txtField;

    @FXML
    private TextField lName_txtField;

    @FXML
    private HBox email_hbox;

    @FXML
    private TextField email_txtField;

    @FXML
    private HBox gender_hbox;

    @FXML
    private TextField gender_txtField;

    @FXML
    private HBox phone_hbox;

    @FXML
    private TextField phone_txtField;

    @FXML
    private HBox salary_hbox;

    @FXML
    private TextField salary_txtField;

    @FXML
    private MFXButton submitBtn;

    @FXML
    private MFXButton cancelBtn;

    @FXML
    private MFXTextField firstName;

    @FXML
    private MFXTextField lastName;

    @FXML
    private MFXComboBox<String> genderBox;

    @FXML
    private MFXTextField locationField;

    @FXML
    private MFXTextField telNum;

    @FXML
    private MFXTextField emailAddress;

    @FXML
    private MFXPasswordField passwordGet;

    @FXML
    private MFXPasswordField passwordCheck;

    @FXML
    private JFXComboBox<String> quesBox;

    @FXML
    private MFXTextField ansField;


    private NewEmployeeController parentController;
    private JFXDrawer drawer;
    private boolean isAddingNew = false;

    ObservableList<String> questionBoxList = FXCollections.observableArrayList("What's your pet's name?","What's your favorite food?","Who was your childhood hero?");
    ObservableList<String> genderBoxList = FXCollections.observableArrayList("Male","Female");

    @FXML
    void cancel(ActionEvent event) {
        drawer.close();
    }

    public void setParentController(NewEmployeeController thisController) {
        this.parentController = thisController;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void updateEmployee(ActionEvent actionEvent) {

        EmployeeData employeeData = getEntry();
        if (!isAddingNew) {
            EmployeeManager.updateEmployee(employeeData);
        }
        submitBtn.setDisable(true);
        parentController.getEmployeeData();
        drawer.close();

    }

    private void setupFormValidation() {
        quesBox.setItems(questionBoxList);

        genderBox.setItems(genderBoxList);
        genderBox.setValidated(true);
        genderBox.getValidator().add(BindingUtils.toProperty(genderBox.getSelectionModel().selectedIndexProperty().isNotEqualTo(-1)), "Select Gender");

        ansField.setValidated(true);
        ansField.getValidator().add(
                BindingUtils.toProperty(
                        ansField.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter the Answer"
        );

        locationField.setValidated(true);
        locationField.getValidator().add(
                BindingUtils.toProperty(
                        locationField.textProperty().length().isNotEqualTo(0)
                ),
                "You need enter your Location"
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

    private EmployeeData getEntry() {
        EmployeeData e = new EmployeeData(
                emp_id_txtField.getText(),
                fName_txtField.getText(),
                lName_txtField.getText(),
                email_txtField.getText(),
                gender_txtField.getText(),
                phone_txtField.getText(),
                Integer.parseInt(salary_txtField.getText()),
                location_txtField.getText()
        );

        return e;
    }

    public void setEntry(EmployeeData employeeData) {
        resetForm();
        emp_id_txtField.setText(employeeData.getEmployee_id());
        fName_txtField.setText(employeeData.getFirst_name());
        lName_txtField.setText(employeeData.getLast_name());
        location_txtField.setText(employeeData.getLocation());
        salary_txtField.setText(String.valueOf(employeeData.getSalary()));
        gender_txtField.setText(employeeData.getGender());
        email_txtField.setText(employeeData.getEmail());
        phone_txtField.setText(employeeData.getPhone_number());
    }

    private void resetForm() {
        emp_id_txtField.clear();
        fName_txtField.clear();
        lName_txtField.clear();
        location_txtField.clear();
        salary_txtField.clear();
        gender_txtField.clear();
        email_txtField.clear();
        phone_txtField.clear();
    }

    public void setAddingNew(boolean b) {
        submitBtn.setDisable(false);
        this.isAddingNew = b;
        if(b){
            setupFormValidation();
        }else{
            emp_id_txtField.setEditable(false);
            fName_txtField.setEditable(false);
            lName_txtField.setEditable(false);
            gender_txtField.setEditable(false);
            email_txtField.setEditable(false);
            phone_txtField.setEditable(false);
        }
    }

    public void checkManager(ActionEvent actionEvent) {
    }

    public void addEmployee(ActionEvent actionEvent) {
        String fName = firstName.getText().trim();
        String lName = lastName.getText().trim();
        String pNum = telNum.getText().trim();
        String location = locationField.getText().trim();
        String email = emailAddress.getText().trim();
        String passGet = passwordGet.getText().trim();
        String passCheck = passwordCheck.getText().trim();
        String answer = ansField.getText().trim();
        String question = null;
        String gender = null;

        try {
            question = quesBox.getValue().toString();
            gender = genderBox.getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeData employeeData = new EmployeeData("0",fName,lName,email,gender,pNum,10000,location,false);
        EmployeeManager.addSingleEmployee(employeeData,passGet,question,answer);
        submitBtn.setDisable(true);
        parentController.getEmployeeData();
        drawer.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
