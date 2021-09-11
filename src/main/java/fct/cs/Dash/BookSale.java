package fct.cs.Dash;

import java.sql.Date;

public class BookSale {

    private int book_id;
    private String title;
    private int numOrders;
    private int totalQtySold;

    public BookSale(int book_id, String title, int numOrders, int totalQtySold) {
        this.book_id = book_id;
        this.title = title;
        this.numOrders = numOrders;
        this.totalQtySold = totalQtySold;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOrders() {
        return numOrders;
    }

    public void setNumOrders(int numOrders) {
        this.numOrders = numOrders;
    }

    public int getTotalQtySold() {
        return totalQtySold;
    }

    public void setTotalQtySold(int totalQtySold) {
        this.totalQtySold = totalQtySold;
    }
}
