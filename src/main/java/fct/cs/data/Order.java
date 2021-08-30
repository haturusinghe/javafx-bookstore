package fct.cs.data;

import java.util.Date;

public class Order {
    private int order_id;
    private int customer_id;
    private int employee_id;
    private Date order_date;
    private int total_quantity;
    private int total_price;
    private int total_discount;

    public Order(int order_id, int customer_id, int employee_id, Date order_date, int total_quantity, int total_price, int total_discount) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.order_date = order_date;
        this.total_quantity = total_quantity;
        this.total_price = total_price;
        this.total_discount = total_discount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(int total_discount) {
        this.total_discount = total_discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", employee_id=" + employee_id +
                ", order_date=" + order_date +
                ", total_quantity=" + total_quantity +
                ", total_price=" + total_price +
                ", total_discount=" + total_discount +
                '}';
    }
}
