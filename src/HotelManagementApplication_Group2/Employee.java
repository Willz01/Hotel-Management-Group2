package HotelManagementApplication_Group2;

import java.io.Serializable;

public class Employee implements Serializable {

    private String userName;
    private String employeePassWord;
    private String name;
    private String telephoneNumber;
    private int employeeNumber;

    public Employee(String userName, String employeePassWord, String name, String telephoneNumber, int employeeNumber) {
        this.userName = userName;
        this.employeePassWord = employeePassWord;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.employeeNumber = employeeNumber;
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

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
