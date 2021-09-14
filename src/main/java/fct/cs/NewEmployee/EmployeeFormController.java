package fct.cs.NewEmployee;

import com.jfoenix.controls.JFXDrawer;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EmployeeFormController {

    public TextField emp_id_txtField;
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
    private NewEmployeeController parentController;
    private JFXDrawer drawer;
    private boolean isAddingNew = false;

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
        if (isAddingNew) {
            EmployeeManager.addSingleEmployee(employeeData);
        } else {
            EmployeeManager.updateEmployee(employeeData);
        }
        parentController.getEmployeeData();
        drawer.close();

    }

    private EmployeeData getEntry() {
        EmployeeData e = new EmployeeData(
                emp_id_txtField.getText(),
                fName_txtField.getText(),
                lName_txtField.getText(),
                email_txtField.getText(),
                gender_txtField.getText(),
                phone_txtField.getText(),
                Integer.parseInt(salary_txtField.getText())
        );

        return e;
    }


    public void setEntry(EmployeeData employeeData) {
        resetForm();
        emp_id_txtField.setText(employeeData.getEmployee_id());
        fName_txtField.setText(employeeData.getFirst_name());
        lName_txtField.setText(employeeData.getLast_name());
        salary_txtField.setText(String.valueOf(employeeData.getSalary()));
        gender_txtField.setText(employeeData.getGender());
        email_txtField.setText(employeeData.getEmail());
        phone_txtField.setText(employeeData.getPhone_number());
    }


    private void resetForm() {
        emp_id_txtField.clear();
        fName_txtField.clear();
        lName_txtField.clear();
        salary_txtField.clear();
        gender_txtField.clear();
        email_txtField.clear();
        phone_txtField.clear();
    }

    public void setAddingNew(boolean b) {
        this.isAddingNew = b;
        if(b){
            emp_id_txtField.setEditable(true);
        }else{
            emp_id_txtField.setEditable(false);
        }
    }
}
