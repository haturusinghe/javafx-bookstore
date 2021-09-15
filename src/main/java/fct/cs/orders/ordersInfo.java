package fct.cs.orders;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ordersInfo {
//    order_id INT PRIMARY KEY,
//    customer_id INT,
//    employee_id INT,
//    order_date DATE,
//    total_quantity INT,
//    total_price INT,
//    total_discount INT,

    private final StringProperty order_id = new SimpleStringProperty("");
    private final StringProperty customer_id = new SimpleStringProperty("");
    private final StringProperty employee_id = new SimpleStringProperty("");
    private final StringProperty order_date = new SimpleStringProperty("");
    private final IntegerProperty total_quantity = new SimpleIntegerProperty(0);
    private final IntegerProperty total_price= new SimpleIntegerProperty(0);
    private final IntegerProperty total_discount = new SimpleIntegerProperty(0);

    public ordersInfo(String orderId,String customerId,String employeeId,String orderDate,int totalQty,int totalPrice,int tdiscount) {
        setOrder_id(orderId);
        setCustomer_id(customerId);
        setEmployee_id(employeeId);
        setOrder_date(orderDate);
        setTotal_quantity (totalQty);
        setTotal_price(totalPrice);
        setTotal_discount(tdiscount);
    }

    public ordersInfo(String order_detail_id, String book_id, String order_id, int quantity, int unit_price, int tprice, String title) {
    }

    public String getOrder_id() {
        return order_id.get();
    }

    public StringProperty order_idProperty() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id.set(order_id);
    }

    public String getCustomer_id() {
        return customer_id.get();
    }

    public StringProperty customer_idProperty() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id.set(customer_id);
    }

    public String getEmployee_id() {
        return employee_id.get();
    }

    public StringProperty employee_idProperty() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id.set(employee_id);
    }

    public String getOrder_date() {
        return order_date.get();
    }

    public StringProperty order_dateProperty() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date.set(order_date);
    }

    public int getTotal_quantity() {
        return total_quantity.get();
    }

    public IntegerProperty total_quantityProperty() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity.set(total_quantity);
    }

    public int getTotal_price() {
        return total_price.get();
    }

    public IntegerProperty total_priceProperty() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price.set(total_price);
    }

    public int getTotal_discount() {
        return total_discount.get();
    }

    public IntegerProperty total_discountProperty() {
        return total_discount;
    }

    public void setTotal_discount(int total_discount) {
        this.total_discount.set(total_discount);
    }

}
