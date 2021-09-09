package fct.cs.Bill;

public class BillCustomer {
    private int customer_id ;

    public BillCustomer(int customer_id, String customer_name, String email, String mobile) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.email = email;
        this.mobile = mobile;
    }

    private String customer_name,email,mobile ;



    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }



}
