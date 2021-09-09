package fct.cs.data;

public class Customer {
    private int customer_id;
    private String customer_name;
    private String location;
    private int mobile;

    private String email;

    public Customer(int customer_id, String customer_name,String mobile , String location, String email){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.location = location;
        this.email = email;
    }
}


