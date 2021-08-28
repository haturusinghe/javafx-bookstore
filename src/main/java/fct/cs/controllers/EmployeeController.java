package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TableView employeeTable;

    @FXML
    private TableColumn colEmpId;

    @FXML
    private TableColumn colFirstName;

    @FXML
    private TableColumn colLastName;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colGender;

    @FXML
    private TableColumn colPhone;

    @FXML
    private TableColumn colSalary;

    @FXML
    private Label headerLabel;

    @FXML
    private JFXButton editBtn;

    @FXML
    private TextField employeeID_txtField;

    @FXML
    private TextField firstName_txtField;

    @FXML
    private TextField lastName_txtField;

    @FXML
    private TextField email_txtField;

    @FXML
    private TextField phone_txtField;

    @FXML
    private TextField salary_txtField;

    @FXML
    private TextField gender_txtField;

    @FXML
    private Button updateBtn;

    @FXML
    private Button removeBtn;

    public void selectEmployee(MouseEvent mouseEvent) {
    }

    public void addEmployee(ActionEvent actionEvent) {
    }

    public void updateEmployee(ActionEvent actionEvent) {
    }

    public void removeEmployee(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
