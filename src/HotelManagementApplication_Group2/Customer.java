package HotelManagementApplication_Group2;

public class Customer {
    private String ssn;
    private String name;
    private String address;
    private String customerTelephoneNumber;
    private String email;

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

    public Customer(String ssn, String name, String address, String customerTelephoneNumber, String email) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.customerTelephoneNumber = customerTelephoneNumber;
        this.email = email;
    }
}
