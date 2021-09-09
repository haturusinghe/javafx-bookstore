package fct.cs.data;

public class Customer {
    private int customer_id;
    private String customer_name;
    private String location;
    private String mobile;

    private String email;

    public Customer(int customer_id, String customer_name,String mobile , String location, String email){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.location = location;
        this.mobile = mobile;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", location=" + location +
                ", mobile=" + mobile +
                ", email=" + email +
                '}';
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


