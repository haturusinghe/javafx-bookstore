package fct.cs.NewCustomer;

import io.github.palexdev.materialfx.filter.IFilterable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerData implements IFilterable{
        //customer_id, customer_name ,location , mobile, email
        private final StringProperty customer_id = new SimpleStringProperty("");
        private final StringProperty customer_name = new SimpleStringProperty("");
        private final StringProperty location = new SimpleStringProperty("");
        private final StringProperty mobile = new SimpleStringProperty("");
        private final StringProperty email = new SimpleStringProperty("");

        public CustomerData(String customer_id,String customer_name,String location,String mobile,String email) {
                setcustomer_id(customer_id);
                setcustomer_name(customer_name);
                setlocation(location);
                setmobile(mobile);
                setemail(email);
        }

        public String getcustomer_id() {
                        return customer_id.get();
                }
        public StringProperty customer_idProperty() {
                        return customer_id;
                }
        public void setcustomer_id(String customer_id) {
                        this.customer_id.set(customer_id);
                }

        public String getcustomer_name() {
                        return customer_name.get();
                }
        public StringProperty customer_nameProperty() {
                        return customer_name;
                }
        public void setcustomer_name(String customer_name) {
                        this.customer_name.set(customer_name);
                }

        public String getlocation() {
                        return location.get();
                }
        public StringProperty locationProperty() {
                        return location;
                }
        public void setlocation(String location) {
                        this.location.set(location);
                }

        public String getmobile() {
                return mobile.get();
        }
        public StringProperty mobileProperty() {
                return mobile;
        }
        public void setmobile(String mobile) {
                this.mobile.set(mobile);
        }

        public String getemail() {
                        return email.get();
                }
        public StringProperty emailProperty() {
                        return email;
                }
        public void setemail(String email) {
                        this.email.set(email);
                }

        @Override
        public String toFilterString() {
                        return getcustomer_name() ;
                }
        }
