package HotelManagementApplication_Group2;

import java.io.Serializable;

public class Customer implements Serializable {
    private String ssn;
    private String name;
    private String userName;
    private String address;
    private String customerTelephoneNumber;
    private String email;
    private int password;

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCustomerTelephoneNumber() {
        return customerTelephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCustomerTelephoneNumber(String customerTelephoneNumber) {
        this.customerTelephoneNumber = customerTelephoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer(String ssn, String name, String address, String customerTelephoneNumber, String email,int password,String userName) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.customerTelephoneNumber = customerTelephoneNumber;
        this.email = email;
        this.password = password;
        this.userName=userName;
    }

    @Override
    public String toString() {
        return "Customer" +
                "ssn='" + ssn + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", customerTelephoneNumber='" + customerTelephoneNumber + '\'' +
                ", email='" + email + '\'' ;
    }
}
