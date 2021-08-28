package fct.cs.data;

public class Inventory {
    private int inv_id;
    private String isbn;
    private int list_price;
    private int qty;
    private int min_qty;
    private boolean isLow;

    public Inventory(int inv_id, String isbn, int list_price, int qty, int min_qty) {
        this.inv_id = inv_id;
        this.isbn = isbn;
        this.list_price = list_price;
        this.qty = qty;
        this.min_qty = min_qty;
        this.isLow = qty > min_qty;
    }

    public boolean isLow() {
        return isLow;
    }

    public void setLow(boolean low) {
        isLow = low;
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "inv_id=" + inv_id +
                ", isbn='" + isbn + '\'' +
                ", list_price=" + list_price +
                ", qty=" + qty +
                ", min_qty=" + min_qty +
                '}';
    }

    public int getInv_id() {
        return inv_id;
    }

    public void setInv_id(int inv_id) {
        this.inv_id = inv_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
}
