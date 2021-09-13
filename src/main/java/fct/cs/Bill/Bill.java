package fct.cs.Bill;

public class Bill {
    private int item_id ;
    private String item_name,isbn , unit_price ;

    public Bill(int item_id, String item_name, String isbn, String unit_price) {
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

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }
}
