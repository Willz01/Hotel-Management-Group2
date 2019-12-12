package HotelManagementApplication_Group2;

public class Employee {

    private String userName;
    private String employeePassWord;

    public Employee(String userName, String employeePassWord) {
        this.userName = userName;
        this.employeePassWord = employeePassWord;
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
}
