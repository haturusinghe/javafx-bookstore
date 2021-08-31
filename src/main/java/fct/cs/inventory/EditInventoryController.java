package fct.cs.inventory;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditInventoryController {
    @FXML
    private JFXDrawer drawer;

    @FXML
    private TextField txtInvId;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtMin;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button submitBtn;

    private InventoryManager inventoryManager;

    private InventoryController parentController;

    private boolean isAddingNew = false;


    public void setTextFields(int inv_id ,int bookid,int price,int qty,int min){
        txtInvId.setText(String.valueOf(inv_id));
        txtBookId.setText(String.valueOf(bookid));
        txtPrice.setText(String.valueOf(price));
        txtQty.setText(String.valueOf(qty));
        txtMin.setText(String.valueOf(min));
    }

    public void clearTextFields(){
        txtInvId.clear();
        txtBookId.clear();
        txtPrice.setText("");
        txtQty.setText("");
        txtMin.setText("");
    }

    public StockEntry getStockEntryFromForm(){
        int inv_id = Integer.parseInt(txtInvId.getText());
        int book_id = Integer.parseInt(txtBookId.getText());
        int price = Integer.parseInt(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        int min = Integer.parseInt(txtMin.getText());

        return new StockEntry(inv_id,book_id,price,qty,min);
    }

    public void cancel(ActionEvent actionEvent) {
        drawer.close();
    }

    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void setParentController(InventoryController parentController) {
        this.parentController = parentController;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void setAddingNew(boolean addingNew) {
        isAddingNew = addingNew;
        txtInvId.setDisable(false);
    }

    public void updateInventory(ActionEvent actionEvent) {
        if(!isAddingNew){
            System.out.println(getStockEntryFromForm().toString());
            inventoryManager.updateSingleEntry(getStockEntryFromForm());
            clearTextFields();
            drawer.close();
            parentController.loadTableData();
        }else{
            inventoryManager.addSingleEntry(getStockEntryFromForm());
            setAddingNew(false);
            clearTextFields();
            drawer.close();
            parentController.loadTableData();
        }
    }

    public void setEntry(StockEntry entry) {
        setTextFields(
                entry.getInv_id(),
                entry.getBook_id(),
                entry.getList_price(),
                entry.getQty(),
                entry.getMin_qty()
                );
    }
}
