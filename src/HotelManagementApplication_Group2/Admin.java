package HotelManagementApplication_Group2;

public class Admin {
    private String userAdmin;
    private String passAdmin;

    public Admin(String userAdmin, String passAdmin) {
        this.userAdmin = userAdmin;
        this.passAdmin = passAdmin;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }
}
