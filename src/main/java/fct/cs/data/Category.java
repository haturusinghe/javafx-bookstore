package fct.cs.data;

public class Category {
    private String category_id ;
    private String category_name;
    private int low_value;
    private int up_value;

    public Category(String category_id, String category_name, int low_value, int up_value) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.low_value = low_value;
        this.up_value = up_value;
    }

    public Category(String category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.low_value = 0;
        this.up_value = 100000;
    }

    public int getLow_value() {
        return low_value;
    }

    public void setLow_value(int low_value) {
        this.low_value = low_value;
    }

    public int getUp_value() {
        return up_value;
    }

    public void setUp_value(int up_value) {
        this.up_value = up_value;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return this.category_name;
    }
}
