package HotelManagementApplication_Group2;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeID;
    private String userName;
    private String employeePassWord;
    private String name;
    private String address;
    private String telephoneNumber;

    public Employee(String employeeID, String userName, String employeePassWord, String name, String address, String telephoneNumber) {
        this.employeeID = employeeID;
        this.userName = userName;
        this.employeePassWord = employeePassWord;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployeePassWord() {
        return employeePassWord;
    }

    public void setEmployeePassWord(String employeePassWord) {
        this.employeePassWord = employeePassWord;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "userName='" + userName + '\'' +
                ", employeePassWord='" + employeePassWord + '\'' +
                '}';
    }
}
