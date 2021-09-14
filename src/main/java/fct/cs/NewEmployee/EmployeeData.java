package fct.cs.NewEmployee;

import io.github.palexdev.materialfx.filter.IFilterable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeData implements IFilterable {
    //
    //employee_id
    //first_name
    //last_name
    //email
    //gender
    //phone_number
    //salary
    private final StringProperty employee_id = new SimpleStringProperty("");
    private final StringProperty first_name = new SimpleStringProperty("");
    private final StringProperty last_name = new SimpleStringProperty("");
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty gender = new SimpleStringProperty("");
    private final StringProperty phone_number = new SimpleStringProperty("");
    private final IntegerProperty salary = new SimpleIntegerProperty(0);

    public EmployeeData(String empid,String fname,String lname,String email,String gender,String phone,int sal) {
        setEmail(email);
        setEmployee_id(empid);
        setFirst_name(fname);
        setLast_name(lname);
        setGender(gender);
        setSalary(sal);
        setPhone_number(phone);
    }

    public String getEmployee_id() {
        return employee_id.get();
    }

    public StringProperty employee_idProperty() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id.set(employee_id);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public StringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public StringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public StringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public int getSalary() {
        return salary.get();
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    @Override
    public String toFilterString() {
        return getFirst_name() + " " + getLast_name();
    }
}
