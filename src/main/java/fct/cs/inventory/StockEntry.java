package fct.cs.inventory;

public class StockEntry {
    private int inv_id;
    private int book_id;
    private int list_price;
    private int qty;
    private int min_qty;

    public StockEntry(int inv_id, int book_id, int list_price, int qty, int min_qty) {
        this.inv_id = inv_id;
        this.book_id = book_id;
        this.list_price = list_price;
        this.qty = qty;
        this.min_qty = min_qty;
    }

    public StockEntry(int book_id, int list_price, int qty, int min_qty) {
        this.book_id = book_id;
        this.list_price = list_price;
        this.qty = qty;
        this.min_qty = min_qty;
    }

    public int getInv_id() {
        return inv_id;
    }

    public void setInv_id(int inv_id) {
        this.inv_id = inv_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getList_price() {
        return list_price;
    }

    public void setList_price(int list_price) {
        this.list_price = list_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getMin_qty() {
        return min_qty;
    }

    public void setMin_qty(int min_qty) {
        this.min_qty = min_qty;
    }

    @Override
    public String toString() {
        return "StockEntry{" +
                "inv_id=" + inv_id +
                ", book_id=" + book_id +
                ", list_price=" + list_price +
                ", qty=" + qty +
                ", min_qty=" + min_qty +
                '}';
    }
}
