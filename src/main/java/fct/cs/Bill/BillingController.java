package fct.cs.Bill;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.NewCustomer.NewCustomerController;
import fct.cs.data.Order;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXDateCell;
import io.github.palexdev.materialfx.utils.BindingUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class BillingController {

    public HBox dateHBox;
    public MFXTextField empIdField;

    /* TODO : Improve all billing pages and related forms styling/layout */
    /* TODO : Add success/error notifications */
    /* TODO : Add table styling */
    /* TODO : Fix button sizes */
    /* TODO : Remove current styles from table buttons and use FontIcons in buttons instead */

    @FXML
    private StackPane stackPane;

    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private Label customerName;

    @FXML
    private Label total;

    @FXML
    private MFXTextField discount;

    @FXML
    private Label finalTotal;

    @FXML
    private Button chargeCustomer;


    @FXML
    private Button cancel;
    @FXML
    private VBox content;


//    @FXML
//    private AnchorPane Bill;
//
//    @FXML
//    private Button search;
//
//    @FXML
//    private TextField searchText;

    @FXML
    private Button button;

//    @FXML
//    private VBox content;
//
//    @FXML
//    private Label customerName;

    @FXML
    private TableView<Billdetails> BillTable;

    @FXML
    private TableColumn<Billdetails, Integer> bookID;

    @FXML
    private TableColumn<Billdetails, String> item;

    @FXML
    private TableColumn<Billdetails, String> unitPrice;

    @FXML
    private TableColumn<Billdetails, String> qty;

    @FXML
    private TableColumn<Billdetails, String> totalPrice;
    @FXML
    private Label customerID;

    private int lastAddedOrder = 0;


    @FXML
    private TableColumn remove;
    @FXML
    private JFXDrawer drawer;

    private FXMLLoader loader = null;

    private BillingController thisController = this;
    private String currentPage = "";
    public static Label static_label;
    public static FXMLLoader load;

    private ObservableList<Billdetails> BillingObservableList = FXCollections.observableArrayList();
    private ArrayList<Billdetails> billDetails;
    private BillItemController parentController;
    private BillManager billManager;
    private NewCustomerController newCustomerController ;
    private SelectCustomerController SelectCustomerController ;
    MFXDatePicker datePicker;
//    private int subTotal;


    public int getLastAddedOrder() {
        return lastAddedOrder;
    }

    public void setLastAddedOrder(int lastAddedOrder) {
        this.lastAddedOrder = lastAddedOrder;
    }

    private int orderDetailId = 1;
    //    private ArrayList<Billdetails> BillitemsList;
    private boolean alreadyAdded = false;

    public void initialize() {

        datePicker = new MFXDatePicker(LocalDate.now());
        datePicker.setPickerColor(Color.BLACK);
        datePicker.setLineColor(Color.BLACK);
        datePicker.setColorText(true);
        dateHBox.getChildren().add(datePicker);


        discount.setText("0");
        discount.setValidated(true);
        discount.getValidator().add(
                BindingUtils.toProperty(
                        discount.textProperty().length().greaterThanOrEqualTo(1)
                ),
                "This field cant be empty"
        );
        discount.getValidator().add(BindingUtils.toProperty(
                        Bindings.createBooleanBinding(
                                () -> discount.getText().matches("^[0-9]*$"),
                                discount.textProperty()
                        )
                ),
                "Must only contain digits"
        );

        static_label = customerName;
        billManager = new BillManager();
        billDetails = new ArrayList<Billdetails>();
        moveToSelectCustomer();
        setColumns();
        loadBillTable();
        finalTotal.setText(String.valueOf(getFinalTotal(Integer.parseInt(total.getText()))));


    }

    public void loadBillTable() {

        BillingObservableList.clear();
        if (billDetails.size() > 0) {
            for (Billdetails currentItem : billDetails) {
                BillingObservableList.add(currentItem);
            }
        }
        total.setText(String.valueOf(getTotal(billDetails)));
        finalTotal.setText(String.valueOf(getFinalTotal(Integer.parseInt(total.getText()))));
        BillTable.setItems(BillingObservableList);
    }

    public void refreshTable() {
        BillTable.refresh();
    }

    private void setColumns() {

        bookID.setCellValueFactory(new PropertyValueFactory<Billdetails, Integer>("book_id"));
        item.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("book_name"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("unit_price"));
        qty.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("quantity"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Billdetails, String>("totalForItem"));

        remove.setCellFactory((tableColumn) -> {
            TableCell<Billdetails, Integer> tableCell = new TableCell<>() {
                javafx.scene.image.Image imgSelect = new Image(getClass().getResourceAsStream("/images/remove.png"));
                final javafx.scene.control.Button btnRemove = new Button();
                FontIcon icon3 = new FontIcon("antf-close-square");


                @Override
                protected void updateItem(Integer customer_id, boolean empty) {
                    super.updateItem(customer_id, empty);

                    if (empty) {
                        this.setText(null);
                        this.setGraphic(null);
                    } else {

                        btnRemove.setOnAction(e -> {
                            System.out.println("Clicked Remove");
                            Billdetails entry = getTableView().getItems().get(getIndex());
                            getTableView().getSelectionModel().select(getIndex());
                            System.out.println(getTableView().getSelectionModel().getSelectedItem().toString());

                            for (Billdetails currentItem : billDetails) {

                                if (entry.getBook_id() == currentItem.getBook_id()) {
                                    if (currentItem.getQuantity() > 1 ) {
                                        currentItem.setQuantity(currentItem.getQuantity() - 1);
                                        break;
                                    }else{
                                        billDetails.remove(currentItem);
                                        break;
                                    }


                                }
                            }

                            loadBillTable();

                        });


                        btnRemove.setStyle("-fx-background-color: transparent;");
                        icon3.setIconColor(Color.BLACK);
                        icon3.setIconSize(30);

                        ImageView iv = new ImageView();
                        iv.setImage(imgSelect);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnRemove.setGraphic(icon3);

                        this.setGraphic(btnRemove);
                        this.setAlignment(Pos.CENTER);


                    }


                }

            };
            return tableCell;
        });


    }

    public void displayCustomerDetails(BillCustomer customer) {

        customerName.setText(customer.getCustomer_name());
        customerID.setText(String.valueOf(customer.getCustomer_id()));

    }

    public void MovetoBooks() {
        if (!currentPage.equals("selectItemsBill")) {
            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/billing/selectItemsForBill.fxml"));
            try {
                content.getChildren().clear();
                content.getChildren().add(loader.load());
                currentPage = "selectItemsBill";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("already loaded");
        }
        BillItemController controller = loader.getController();
        controller.setParentController(this);
    }

    public void moveToSelectCustomer() {


            loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/billing/selectCustomerBill.fxml"));
            try {
                content.getChildren().clear();
                content.getChildren().add(loader.load());
                currentPage = "selectCustomerBills";
            } catch (IOException e) {
                e.printStackTrace();
            }

        SelectCustomerController controller = loader.getController();
        controller.setParentController(this);


    }

    public void getOrderDetails(int book_id, String book_name, int unit_price) {
        alreadyAdded = false;
        int qty = billManager.getQtySingleItem(book_id);
        System.out.println(qty);
        if (billDetails.size() > 0) {

            for (Billdetails currentItem : billDetails) {

                if (currentItem.getBook_id() == book_id) {
                    if (currentItem.getQuantity() < qty) {
                        currentItem.setQuantity(currentItem.getQuantity() + 1);
                        currentItem.setTotalForItem(currentItem.getQuantity() * currentItem.getUnit_price());

                    }else{
                        ErrorShow(currentItem.getBook_name() + " is out of stock!!!");
                    }
                    alreadyAdded = true;
                    break;
                }
            }
        }

        if (qty > 0){
            if (!alreadyAdded) {


                Billdetails getOrderDetail = new Billdetails(orderDetailId, book_id, book_name, 1, unit_price, unit_price);
                getOrderDetail.setOrder_id(orderDetailId);
                orderDetailId++;
                getOrderDetail.setBook_id(book_id);
                getOrderDetail.setBook_name(book_name);
                getOrderDetail.setQuantity(1);
                getOrderDetail.setUnit_price(unit_price);

                billDetails.add(getOrderDetail);

            }

        }else{
            ErrorShow(book_name + " is Out of Stock!!! ");
        }


    }

    public int getTotal(ArrayList<Billdetails> array) {
        int subTotal = 0;
        for (Billdetails currentItem : array) {

            subTotal += currentItem.getTotalForItem();
        }
        return subTotal;
    }

    public int getQuantity(ArrayList<Billdetails> array) {
        int quantity = 0;
        for (Billdetails currentItem : array) {
            quantity += currentItem.getQuantity();
        }
        return quantity;
    }

    public int getFinalTotal(int total) {
        int disc = Integer.parseInt(discount.getText());

        return (total - (total * disc) / 100);
    }

    public void addDiscount(ActionEvent action) {

        finalTotal.setText(String.valueOf(getFinalTotal(Integer.parseInt(total.getText()))));

    }

    public void cancelOrder(ActionEvent action) {
        cancelOrders();


    }

    public void cancelOrders(){
        billDetails.clear();
        loadBillTable();
        customerName.setText("");
        customerID.setText("");
        discount.setText("");
        moveToSelectCustomer();

    }

    public Order getOrderEntryFromBill(){
        int customer_id = Integer.parseInt(customerID.getText());
        int employee_id = Integer.parseInt(empIdField.getText()); ;
        Date order_date = getCurrentDate();
        int total_quantity = getQuantity(billDetails);
        int total_discount = (Integer.parseInt(total.getText()) * Integer.parseInt(discount.getText()))/100;
        int discount_perc = Integer.parseInt(discount.getText());
        int total_price = Integer.parseInt(finalTotal.getText());
        return new Order(customer_id,employee_id,order_date,total_quantity,total_price,total_discount,discount_perc);
    }

    public Date getCurrentDate(){
        return Date.valueOf(datePicker.getDate());
    }

    public void ChargeCustomer(ActionEvent action){
        ChargeCustomer();
    }

    private void ChargeCustomer(){
        if(billDetails.size() == 0 ){
            ErrorShow("No Items in the Bill!!");
        }else {
            Order order = getOrderEntryFromBill();
            setLastAddedOrder(billManager.updateOrderEntry(order));
            billManager.updateOrderDetailsByArray(billDetails);
            confirmationToInvoice();
        }
    }

    public void ErrorShow(String str){
        JFXDialogLayout DialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("Okay");
        JFXDialog dialog = new JFXDialog( stackPane, DialogLayout , JFXDialog.DialogTransition.TOP);
        button.setOnAction(e ->{
            dialog.close();
        });

        DialogLayout.setHeading(new Label(str));
        DialogLayout.setActions(button);
        dialog.show();
    }

    public void confirmationToInvoice(){
        JFXDialogLayout DialogLayout = new JFXDialogLayout();
        JFXButton yes = new JFXButton("Yes");

        JFXButton no = new JFXButton("No");
        JFXDialog dialog = new JFXDialog( stackPane, DialogLayout , JFXDialog.DialogTransition.TOP);
        yes.setOnAction(e->{
            billManager.jasperInvoice(getLastAddedOrder());
            cancelOrders();
            dialog.close();

        });
        no.setOnAction(e ->{
            cancelOrders();
            dialog.close();
        });

        DialogLayout.setHeading(new Label("Payment Successful!! \n Do you need a Invoice ?"));
        DialogLayout.setActions(yes, no);
        dialog.show();
    }

    public void setManager(boolean isManager) {
    }

    public void newCustomer(){
        newCustomerController = new NewCustomerController() ;
        newCustomerController.addNewCustomer(drawer);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        moveToSelectCustomer();
        drawer.toBack();
    }

    public void displayDate(ActionEvent actionEvent) {
        getCurrentDate();
    }
}












