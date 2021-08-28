package fct.cs.controllers;

import com.jfoenix.controls.JFXButton;
import fct.cs.commonUtil.AppUtils;
import fct.cs.data.Category;
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
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField search_txtField;

    @FXML
    private ComboBox categoryCombo;

    @FXML
    private Button cancelNewBtn;
    
    @FXML
    private Button addNewBtn;

    @FXML
    private Button editEmpBtn;

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

    private boolean isAddingNew = false;


//    private static DatabaseHandler databaseHandler;

    private ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    private FilteredList<Employee> employeeFilteredList = new FilteredList<>(employeeObservableList);

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    private static AppUtils appUtils;
    private static Employee selectedEmployee;

    public void selectEmployee(MouseEvent mouseEvent) {
        employee_details_vbox.setDisable(true);
        removeBtn.setVisible(false);
        updateBtn.setVisible(false);
        editEmpBtn.setVisible(true);

        selectedEmployee = (Employee) employeeTable.getSelectionModel().getSelectedItem();

        employeeID_txtField.setText(String.valueOf(selectedEmployee.getEmployee_id()));
        salary_txtField.setText(String.valueOf(selectedEmployee.getSalary()));
        firstName_txtField.setText(selectedEmployee.getFirst_name());
        lastName_txtField.setText(selectedEmployee.getLast_name());
        email_txtField.setText(selectedEmployee.getEmail());
        gender_txtField.setText(selectedEmployee.getGender());
        phone_txtField.setText(selectedEmployee.getPhone_number());


    }



    private void resetEmployeeDetailsSidebar() {
        employee_details_vbox.setDisable(true);
        removeBtn.setVisible(false);
        updateBtn.setVisible(false);
        addNewBtn.setVisible(false);
        cancelNewBtn.setVisible(false);

        if(employeeTable.isDisabled()){
            employeeTable.setDisable(false);
        }

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
        resetEmployeeDetailsSidebar();
        isAddingNew = true;
        editEmpBtn.setVisible(false);
        employeeTable.setDisable(true);
        employee_details_vbox.setDisable(false);
        employeeID_txtField.setPromptText("Add Details");
        addNewBtn.setVisible(false);
        cancelNewBtn.setVisible(true);

    }

    public void updateEmployee(ActionEvent actionEvent)  {

        DatabaseHandler databaseHandler = null;
        try {
            databaseHandler = new DatabaseHandler();
        } catch (SQLException e) {
            e.printStackTrace();
            errorAlert(e.getLocalizedMessage());
        }
        Connection connection = databaseHandler.getConn();
        //employee_id, first_name, last_name, email, gender, phone_number, salary
        String tableName = "employee";
        String sql = "UPDATE "+tableName+" set employee_id = ? , first_name = ? , last_name = ? , email = ?, gender = ?, phone_number = ?, salary = ? where employee_id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(employeeID_txtField.getText()));
            preparedStatement.setString(2,firstName_txtField.getText());
            preparedStatement.setString(3,lastName_txtField.getText());
            preparedStatement.setString(4,email_txtField.getText());
            preparedStatement.setString(5,gender_txtField.getText());
            preparedStatement.setString(6,phone_txtField.getText());
            preparedStatement.setInt(7, Integer.parseInt(salary_txtField.getText()));
            preparedStatement.setInt(8,selectedEmployee.getEmployee_id());
            preparedStatement.executeUpdate();
            updateAlert(selectedEmployee.getFirst_name());
        } catch (SQLException e) {
            e.printStackTrace();
            errorAlert(e.getLocalizedMessage());
        }

        try {
            getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resetEmployeeDetailsSidebar();



    }

    public void removeEmployee(ActionEvent actionEvent) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm Removal");
        alert.setContentText("Save?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
//        ButtonType cancelButton = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {

                System.out.println("Remove Element: " + selectedEmployee.toString());

                DatabaseHandler databaseHandler = null;
                try {
                    databaseHandler = new DatabaseHandler();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Connection connection = databaseHandler.getConn();

                String tableName = "employee";
                String sql = "DELETE FROM " + tableName + " WHERE employee_id = ?";

                int id = selectedEmployee.getEmployee_id();

                PreparedStatement statement = null;
                try {
                    statement = connection.prepareStatement(sql);

                    statement.setInt(1, id);
                    statement.executeUpdate();

                    statement.close();
                    connection.close();
                    getResults();
                    resetEmployeeDetailsSidebar();
                } catch (SQLException e) {
                    errorAlert(e.getLocalizedMessage());
                    e.printStackTrace();
                }


            } else if (type == noButton) {

            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnProperties();
        try {
            setCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        if (!isAddingNew) {
            updateBtn.setVisible(true);
        } else if (isAddingNew) {
            addNewBtn.setVisible(true);
            cancelNewBtn.setVisible(true);
        }
    }

    public void editEmployee(ActionEvent actionEvent) {
        if (selectedEmployee != null) {
            employee_details_vbox.setDisable(false);
            removeBtn.setVisible(true);
        }
    }

    private void saveAlert(Employee user){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The employee "+user.getFirst_name()+ ".");
        alert.showAndWait();
    }

    private void errorAlert(String e){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!.");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }

    private void updateAlert(String user){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The employee "+user+" has been updated.");
        alert.showAndWait();
    }

    public void addNewEmployee(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = null;
        try {
            databaseHandler = new DatabaseHandler();
        } catch (SQLException e) {
            e.printStackTrace();
            errorAlert(e.getLocalizedMessage());
        }
        Connection connection = databaseHandler.getConn();
        //employee_id, first_name, last_name, email, gender, phone_number, salary
        String tableName = "employee";
        String sql_old = "UPDATE "+tableName+" set employee_id = ? , first_name = ? , last_name = ? , email = ?, gender = ?, phone_number = ?, salary = ? where employee_id = ?";
        String sql = "insert into employee (employee_id, first_name, last_name, email, gender, phone_number, salary) values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(employeeID_txtField.getText()));
            preparedStatement.setString(2,firstName_txtField.getText());
            preparedStatement.setString(3,lastName_txtField.getText());
            preparedStatement.setString(4,email_txtField.getText());
            preparedStatement.setString(5,gender_txtField.getText());
            preparedStatement.setString(6,phone_txtField.getText());
            preparedStatement.setInt(7, Integer.parseInt(salary_txtField.getText()));
            preparedStatement.executeUpdate();
            updateAlert(firstName_txtField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
            errorAlert(e.getLocalizedMessage());
        }

        try {
            getResults();
        } catch (SQLException e) {
            e.printStackTrace();
            errorAlert(e.getLocalizedMessage());
        }
        resetEmployeeDetailsSidebar();
        isAddingNew = false;

    }

    public void cancelNewEmployee(ActionEvent actionEvent) {
        isAddingNew = false;
        resetEmployeeDetailsSidebar();
    }

    public void searchEmployees(KeyEvent keyEvent) {
        filterList();
        employeeTable.setItems(employeeFilteredList);
    }

    public void filterEmployees(MouseEvent mouseEvent) {

    }


    public void filterList() {
        System.out.println("Searching ...");
        employeeFilteredList.setPredicate(employee -> {
            String filter = search_txtField.getText().toLowerCase();
            boolean title_matches = employee.getFirst_name().toLowerCase().contains(filter) || employee.getLast_name().toLowerCase().contains(filter);

            /*
            boolean creator_matches = (employee.getTitle() != null
                    && employee.getPrice().toLowerCase().contains(filter))
                    || (employee.getAuthor() != null && employee.getPublisher.toLowerCase().contains(filter));*/

            // check that the series has the selected category

            /*boolean category_matches = false;

            if (categoryCombo.getSelectionModel().getSelectedItem() != null) {

            }*/

            return title_matches; //&& category_matches;
        });
    }

    public void setCategories() throws SQLException {
        String qu = "select * from category";
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet rs = databaseHandler.excecuteQuery(qu);

        while (rs.next()){
            categoryList.add(new Category(
                    rs.getString("category_id"),
                    rs.getString("category_name")
            ));
        }

        categoryCombo.setItems(categoryList);


    }
}


