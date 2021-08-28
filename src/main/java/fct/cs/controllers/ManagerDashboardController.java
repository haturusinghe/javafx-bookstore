package fct.cs.controllers;

import fct.cs.App;
import fct.cs.commonUtil.AppUtils;
import fct.cs.data.User;
import fct.cs.dbUtil.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerDashboardController implements Initializable {
    public Button logoutBtn;
    public TableView userTable;
    public TableColumn colStuId;
    public Button searchBtn;
    public Button addBookBtn;
    public Button getResultsBtn;
    public TableColumn colGrade;
    public MenuItem deleteUsers;
    public TableColumn colEdit;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    DatabaseHandler databaseHandler;

    AppUtils appUtils;

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("manager-dash");
    }

    public void addBook(MouseEvent mouseEvent) throws IOException {
        appUtils = new AppUtils();
        appUtils.createStage("/fct/cs/store-dash.fxml");
    }

    public void getResults(MouseEvent mouseEvent) throws SQLException {
        String qu = "select * from results";
        databaseHandler = new DatabaseHandler();
        ResultSet rs = databaseHandler.excecuteQuery(qu);


        while (rs.next()){
            String studentID = rs.getString("studentID");
            String grade = rs.getString("grade");
            System.out.println(studentID + " " + grade);
            userList.add(new User(studentID,grade));
        }

        loadUserDetails();


    }

    public void deleteUsers(ActionEvent actionEvent) {
    }

    private void saveAlert(User user){

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user "+user.getStudentID()+ ".");
        alert.showAndWait();
    }

    private void updateAlert(User user){

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user "+user.getStudentID()+" has been updated.");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
    }

    private void setColumnProperties() {
        colStuId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colEdit.setCellFactory((tableColumn) -> {
            TableCell<User, String> tableCell = new TableCell<>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                protected void updateItem(String grade, boolean empty) {
                    super.updateItem(grade, empty);

                    if(empty)
                    {
                        this.setText(null);
                        this.setGraphic(null);
                    }
                    else{
                        btnEdit.setOnAction(e ->{
                            System.out.println("Clicked");
                        });

                        btnEdit.setStyle("-fx-background-color: #fff;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        this.setGraphic(btnEdit);
                        this.setAlignment(Pos.CENTER);
                        this.setText("HELLO");
                    }

                }
            };

            return tableCell;
        });
    }

    private void loadUserDetails(){
//        userList.add
//        userList.addAll(userService.findAll());
        userTable.setItems(userList);
    }
}
