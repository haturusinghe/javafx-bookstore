package fct.cs.Customer;

import com.jfoenix.controls.JFXButton;
import fct.cs.commonUtil.AppUtils;
import fct.cs.data.Category;
import fct.cs.data.Customer;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomController implements Initializable {

        @FXML
        private TextField search_txtField;
        @FXML
        private ComboBox categoryCombo;
        @FXML
        private Button cancelNewBtn;
        @FXML
        private Button addNewBtn;
        @FXML
        private Button editCustBtn;
        @FXML
        private VBox customer_details_vbox;
        @FXML
        private Button addBtn;
        @FXML
        private TableView customerTable;
        @FXML
        private TableColumn colcustomer_id;
        @FXML
        private TableColumn colcustomer_name;
        @FXML
        private TableColumn collocation;
        @FXML
        private TableColumn colemail;
        @FXML
        private TableColumn colmobile;
        @FXML
        private TableColumn colEdit;
        @FXML
        private TableColumn colRemove;
        @FXML
        private Label headerLabel;
        @FXML
        private JFXButton editBtn;
        @FXML
        private TextField customer_id_txtField;
        @FXML
        private TextField customer_name_txtField;
        @FXML
        private TextField location_txtField;
        @FXML
        private TextField email_txtField;
        @FXML
        private TextField mobile_txtField;
        @FXML
        private Button updateBtn;
        @FXML
        private Button removeBtn;
        private boolean isAddingNew = false;

        /* Lists for Customer Details Table */
        private ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        private FilteredList<Customer> customerFilteredList = new FilteredList<>(customerObservableList);

        private ObservableList<Category> categoryList = FXCollections.observableArrayList();

        private static Customer selectedCustomer = null;

        @Override
        /*Set Table Columns and ComboBox values , then */
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

        public void selectCustomer(MouseEvent mouseEvent) {

                selectedCustomer = (Customer)customerTable.getSelectionModel().getSelectedItem();

                if (selectedCustomer != null) {
                        customer_details_vbox.setDisable(true);
                        removeBtn.setVisible(false);
                        updateBtn.setVisible(false);
                        editCustBtn.setVisible(true);

                        customer_id_txtField.setText(String.valueOf(selectedCustomer.getCustomer_id()));
                        customer_name_txtField.setText(selectedCustomer.getCustomer_name());
                        location_txtField.setText(selectedCustomer.getLocation());
                        email_txtField.setText(selectedCustomer.getEmail());
                        mobile_txtField.setText(selectedCustomer.getMobile());
                }


        }

        private void resetCustomerDetailsSidebar() {
                customer_details_vbox.setDisable(true);
                removeBtn.setVisible(false);
                updateBtn.setVisible(false);
                addNewBtn.setVisible(false);
                cancelNewBtn.setVisible(false);

                if(customerTable.isDisabled()){
                        customerTable.setDisable(false);
                }

//        selectedEmployee = null;
                customer_id_txtField.setText("");
                customer_name_txtField.setText("");
                location_txtField.setText("");
                email_txtField.setText("");
                mobile_txtField.setText("");
        }

        public void prepareAddCustomer(ActionEvent actionEvent) {
                resetCustomerDetailsSidebar();
                isAddingNew = true;
                cancelNewBtn.setVisible(true);
                editCustBtn.setVisible(false);
                customerTable.setDisable(true);
                customer_details_vbox.setDisable(false);
                customer_id_txtField.setPromptText("Add Details");
                addNewBtn.setVisible(false);
                cancelNewBtn.setVisible(true);

        }

        public void updateCustomer(ActionEvent actionEvent)  {

                DatabaseHandler databaseHandler = null;
                try {
                        databaseHandler =  new DatabaseHandler();
                } catch (SQLException e) {
                        e.printStackTrace();
                        errorAlert(e.getLocalizedMessage());
                }
                Connection connection = databaseHandler.getConn();
                //customer_id, customer_name, location, email, mobile
                String tableName = "customer";
                String sql = "UPDATE "+tableName+" set customer_id = ? , customer_name = ? , location = ? , email = ?, mobile = ? where customer_id = ?";
                PreparedStatement preparedStatement = null;
                try {
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, Integer.parseInt(customer_id_txtField.getText()));
                        preparedStatement.setString(2,customer_name_txtField.getText());
                        preparedStatement.setString(3,location_txtField.getText());
                        preparedStatement.setString(4,email_txtField.getText());
                        preparedStatement.setString(5,mobile_txtField.getText());
                        preparedStatement.setInt(6,selectedCustomer.getCustomer_id());
                        preparedStatement.executeUpdate();
                        updateAlert(selectedCustomer.getCustomer_name());
                } catch (SQLException e) {
                        e.printStackTrace();
                        errorAlert(e.getLocalizedMessage());
                }

                try {
                        getResults();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                resetCustomerDetailsSidebar();



        }

        public void removeCustomer(ActionEvent actionEvent) throws SQLException {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Confirm Removal");
                alert.setContentText("Save?");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
//        ButtonType cancelButton = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, noButton);
                alert.showAndWait().ifPresent(type -> {
                        if (type == okButton) {

                                System.out.println("Remove Element: " + selectedCustomer.toString());

                                DatabaseHandler databaseHandler = null;
                                try {
                                        databaseHandler = new DatabaseHandler();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                                Connection connection = databaseHandler.getConn();

                                String tableName = "customer";
                                String sql = "DELETE FROM " + tableName + " WHERE customer_id = ?";

                                int id = selectedCustomer.getCustomer_id();

                                PreparedStatement statement = null;
                                try {
                                        statement = connection.prepareStatement(sql);

                                        statement.setInt(1, id);
                                        statement.executeUpdate();

                                        statement.close();
                                        connection.close();
                                        getResults();
                                        resetCustomerDetailsSidebar();
                                } catch (SQLException e) {
                                        errorAlert(e.getLocalizedMessage());
                                        e.printStackTrace();
                                }


                        } else if (type == noButton) {

                        }
                });


        }

        private void getResults() throws SQLException {
                String qu = "select * from customer";
                DatabaseHandler databaseHandler = new DatabaseHandler();
                ResultSet rs = databaseHandler.excecuteQuery(qu);

                customerObservableList.clear();
                while (rs.next()){
                        customerObservableList.add(new Customer(
                                rs.getInt("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("location"),
                                rs.getString("email"),
                                rs.getString("mobile")
                        ));
                }

                loadCustomerTable();
        }

        private void loadCustomerTable() {
                customerTable.setItems(customerObservableList);
        }

        private void setColumnProperties() {
                colcustomer_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
                colcustomer_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
                collocation.setCellValueFactory(new PropertyValueFactory<>("last_name"));
                colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
                colmobile.setCellValueFactory(new PropertyValueFactory<>("gender"));

                colEdit.setCellFactory((tableColumn) -> {
                        TableCell<Customer, Integer> tableCell = new TableCell<>() {
                                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                                final Button btnEdit = new Button();

                                @Override
                                protected void updateItem(Integer customer_id, boolean empty) {
                                        super.updateItem(customer_id, empty);

                                        if(empty)
                                        {
                                                this.setText(null);
                                                this.setGraphic(null);
                                        }
                                        else{
                                                btnEdit.setOnAction(e ->{
                                                        System.out.println("Clicked");
                                                        customer_details_vbox.setDisable(false);
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
                        TableCell<Customer, Integer> tableCell = new TableCell<>() {
                                Image imgRemove = new Image(getClass().getResourceAsStream("/images/remove.png"));
                                final Button btnRemove = new Button();

                                @Override
                                protected void updateItem(Integer customer_id, boolean empty) {
                                        super.updateItem(customer_id, empty);

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
                if (selectedCustomer != null) {
                        customer_details_vbox.setDisable(false);
                        removeBtn.setVisible(true);
                }
        }

        private void saveAlert(Customer user){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User saved successfully.");
                alert.setHeaderText(null);
                alert.setContentText("The customer "+user.getCustomer_name()+ ".");
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
                alert.setContentText("The customer "+user+" has been updated.");
                alert.showAndWait();
        }

        public void addNewCustomer(ActionEvent actionEvent) {
                DatabaseHandler databaseHandler = null;
                try {
                        databaseHandler = new DatabaseHandler();
                } catch (SQLException e) {
                        e.printStackTrace();
                        errorAlert(e.getLocalizedMessage());
                }
                Connection connection = databaseHandler.getConn();
                //customer_id, customer_name, location, email, mobile
                String tableName = "customer";
                String sql_old = "UPDATE "+tableName+" set customer_id = ? , customer_name = ? , location = ? , email = ?, mobile = ? where customer_id = ?";
                String sql = "insert into customer (customer_id, customer_name, location, email, mobile) values (?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement preparedStatement = null;
                try {
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, Integer.parseInt(customer_id_txtField.getText()));
                        preparedStatement.setString(2,customer_name_txtField.getText());
                        preparedStatement.setString(3,location_txtField.getText());
                        preparedStatement.setString(4,email_txtField.getText());
                        preparedStatement.setString(5,mobile_txtField.getText());
                        preparedStatement.executeUpdate();
                        updateAlert(customer_name_txtField.getText());
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
                resetCustomerDetailsSidebar();
                isAddingNew = false;

        }

        public void cancelNewCustomer(ActionEvent actionEvent) {
                isAddingNew = false;
                resetCustomerDetailsSidebar();
        }

        public void searchCustomer(KeyEvent keyEvent) {
                filterList();
                customerTable.setItems(customerFilteredList);
        }

        public void filterCustomer(ActionEvent actionEvent) {
                customerTable.setItems(customerFilteredList);
                System.out.println(categoryCombo.getValue());
                Category category = (Category)categoryCombo.getSelectionModel().getSelectedItem();
        }

        public void filterList() {
                System.out.println("Searching ...");
                customerFilteredList.setPredicate(customer -> {
                        String filter = search_txtField.getText().toLowerCase();
                        boolean title_matches = customer.getCustomer_name().toLowerCase().contains(filter) ;

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
                                rs.getString("category_name"),
                                rs.getInt("low_value"),
                                rs.getInt("up_value")
                        ));
                }

                categoryCombo.setItems(categoryList);
        }

}


