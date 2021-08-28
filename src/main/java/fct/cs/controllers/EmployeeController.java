package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import fct.cs.commonUtil.AppUtils;
import fct.cs.data.Employee;
import fct.cs.dbUtil.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private VBox employee_details_vbox;

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
    private TableColumn colEdit;

    @FXML
    private TableColumn colRemove;

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


//    private static DatabaseHandler databaseHandler;

    private ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    private FilteredList<Employee> employeeFilteredList = new FilteredList<>(employeeObservableList);

    private static AppUtils appUtils;
    private static Employee selectedEmployee;

    public void selectEmployee(MouseEvent mouseEvent) {
        employee_details_vbox.setDisable(true);
        updateBtn.setVisible(false);
        selectedEmployee = (Employee) employeeTable.getSelectionModel().getSelectedItem();
        employeeID_txtField.setText(String.valueOf(selectedEmployee.getEmployee_id()));
        salary_txtField.setText(String.valueOf(selectedEmployee.getSalary()));
        firstName_txtField.setText(selectedEmployee.getFirst_name());
        lastName_txtField.setText(selectedEmployee.getLast_name());
        email_txtField.setText(selectedEmployee.getEmail());
        gender_txtField.setText(selectedEmployee.getGender());
        phone_txtField.setText(selectedEmployee.getPhone_number());


    }

    private void clearEmployeeFields() {
//        selectedEmployee = null;
        employeeID_txtField.setText("");
        salary_txtField.setText("");
        firstName_txtField.setText("");
        lastName_txtField.setText("");
        email_txtField.setText("");
        gender_txtField.setText("");
        phone_txtField.setText("");
    }

    public void addEmployee(ActionEvent actionEvent) {

    }

    public void updateEmployee(ActionEvent actionEvent) {

    }

    public void removeEmployee(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnProperties();
        try {
            getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getResults() throws SQLException {
        String qu = "select * from employee";
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet rs = databaseHandler.excecuteQuery(qu);

        employeeObservableList.clear();
        while (rs.next()){
            employeeObservableList.add(new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getString("phone_number"),
                    rs.getInt("salary")
            ));
        }

        loadEmployeeTable();
    }

    private void loadEmployeeTable() {
        employeeTable.setItems(employeeObservableList);
    }

    private void setColumnProperties() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        colEdit.setCellFactory((tableColumn) -> {
            TableCell<Employee, Integer> tableCell = new TableCell<>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                protected void updateItem(Integer empid, boolean empty) {
                    super.updateItem(empid, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        btnEdit.setOnAction(e ->{
                            System.out.println("Clicked");
                            employee_details_vbox.setDisable(false);
                        });

                        btnEdit.setStyle("-fx-background-color: #fff;");
                        btnEdit.setStyle("-fx-padding: 5 5 5 5;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        this.setGraphic(btnEdit);
                        this.setAlignment(Pos.CENTER);

                    }

                }
            };

            return tableCell;
        });

        colRemove.setCellFactory((tableColumn) -> {
            TableCell<Employee, Integer> tableCell = new TableCell<>() {
                Image imgRemove = new Image(getClass().getResourceAsStream("/images/remove.png"));
                final Button btnRemove = new Button();

                @Override
                protected void updateItem(Integer empid, boolean empty) {
                    super.updateItem(empid, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        btnRemove.setOnAction(e ->{
                            System.out.println("Clicked Remove");
                        });

                        btnRemove.setStyle("-fx-background-color: #fff;");
                        btnRemove.setStyle("-fx-padding: 5 5 5 5;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgRemove);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnRemove.setGraphic(iv);

                        this.setGraphic(btnRemove);
                        this.setAlignment(Pos.CENTER);

                    }

                }
            };

            return tableCell;
        });
    }

    public void showUpdateBtn(KeyEvent keyEvent) {
        updateBtn.setVisible(true);
    }
}
