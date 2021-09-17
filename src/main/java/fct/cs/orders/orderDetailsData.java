package fct.cs.orders;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class orderDetailsData {
//    order_detail_id INT,
//    book_id INT,
//    order_id INT,
//    quantity INT,
//    unit_price INT,
//    price INT,

    private final StringProperty order_detail_id = new SimpleStringProperty("");
    private final StringProperty book_id = new SimpleStringProperty("");
    private final StringProperty order_id = new SimpleStringProperty("");
    private final StringProperty title = new SimpleStringProperty("");
    private final IntegerProperty quantity = new SimpleIntegerProperty(0);
    private final IntegerProperty unit_price = new SimpleIntegerProperty(0);
    private final IntegerProperty price = new SimpleIntegerProperty(0);

    public orderDetailsData(String orderDetId, String bookId, String orderId, String title, int qty, int unitPrice, int price){
        setOrder_detail_id(orderDetId);
        setBook_id(bookId);
        setOrder_id(orderId);
        setTitle(title);
        setQuantity(qty);
        setUnit_price(unitPrice);
        setPrice(price);

    }
    public String getOrder_detail_id() {
        return order_detail_id.get();
    }

    public StringProperty order_detail_idProperty() {
        return order_detail_id;
    }

    public void setOrder_detail_id(String order_detail_id) {
        this.order_detail_id.set(order_detail_id);
    }

    public String getBook_id() {
        return book_id.get();
    }

    public StringProperty book_idProperty() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id.set(book_id);
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

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getUnit_price() {
        return unit_price.get();
    }

    public IntegerProperty unit_priceProperty() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price.set(unit_price);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return "orderDetailsData{" +
                "order_detail_id=" + order_detail_id +
                ", book_id=" + book_id +
                ", order_id=" + order_id +
                ", title=" + title +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", price=" + price +
                '}';
    }
}
