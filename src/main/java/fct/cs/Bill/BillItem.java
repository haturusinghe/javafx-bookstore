package fct.cs.Bill;

public class BillItem {
    private int item_id ,unit_price;
    private String item_name,isbn;

    public BillItem(int item_id, String item_name, String isbn, Integer unit_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.isbn = isbn;
        this.unit_price = unit_price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }
}
