package fct.cs.Bill;

public class BillCustomer {
    private int customer_id ;
    private String customer_name,email,mobile ;

    public BillCustomer(int customer_id, String customer_name, String email, String mobile) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.email = email;
        this.mobile = mobile;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

}
