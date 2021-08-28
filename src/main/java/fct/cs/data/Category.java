package fct.cs.data;

public class Category {
    private String category_id ;
    private String category_name;

    public Category(String category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
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
