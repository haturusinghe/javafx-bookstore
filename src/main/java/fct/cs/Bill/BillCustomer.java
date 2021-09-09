package fct.cs.Bill;

public class BillCustomer {
    private int customer_id ; 
    private String customer_name,email,mobile ;

    public BillCustomer(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }



}
