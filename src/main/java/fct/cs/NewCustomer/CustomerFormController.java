package fct.cs.NewCustomer;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CustomerFormController {

    public TextField customer_id_txtField;

    @FXML
    private HBox name_hbox;
    @FXML
    private TextField customer_name_txtField;

    @FXML
    private TextField location_txtField;
    @FXML
    private HBox mobile_hbox;
    @FXML
    private TextField mobile_txtField;

    @FXML
    private HBox email_hbox;
    @FXML
    private TextField email_txtField;

    @FXML
    private MFXButton submitBtn;

    @FXML
    private MFXButton cancelBtn;
    private NewCustomerController parentController;
    private JFXDrawer drawer;
    private boolean isAddingNew = false;

    @FXML
    void cancel(ActionEvent event) {
        drawer.close();
    }

    public void setParentController(NewCustomerController thisController) {
        this.parentController = thisController;
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void updateCustomer(ActionEvent actionEvent) {
        CustomerData customerData = getEntry();
        if (isAddingNew) {
            CustomerManager.addSingleCustomer(customerData);
        } else {
            CustomerManager.updateCustomer(customerData);
        }
        parentController.getCustomerData();
        drawer.close();
    }

    private CustomerData getEntry() {
        CustomerData c = new CustomerData(
                customer_id_txtField.getText(),
                customer_name_txtField.getText(),
                location_txtField.getText(),
                mobile_txtField.getText(),
                email_txtField.getText()
        );
        return c;
    }

    public void setEntry(CustomerData customerData) {
        resetForm();
        customer_id_txtField.setText(customerData.getcustomer_id());
        customer_name_txtField.setText(customerData.getcustomer_name());
        location_txtField.setText(customerData.getlocation());
        mobile_txtField.setText(customerData.getmobile());
        email_txtField.setText(customerData.getemail());
    }

    private void resetForm() {
        customer_id_txtField.clear();
        customer_name_txtField.clear();
        location_txtField.clear();
        mobile_txtField.clear();
        email_txtField.clear();
    }

    public void setAddingNew(boolean b) {
        this.isAddingNew = b;
        if(b){
            customer_id_txtField.setEditable(true);
        }else{
            customer_id_txtField.setEditable(false);
        }
    }
}