package HotelManagementApplication_Group2;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("===============================");
        System.out.println("Admins log in information ");
        System.out.println("1-> User name : admin" + " password  admin");
        System.out.println("===============================");
        System.out.println("Employees log in information ");
        System.out.println("1-> User name : 1234" + " password  1234");
        System.out.println("2-> User name : 4321" + " Password  4321");
        System.out.println("===============================");
        System.out.println("Test information for customer log in");
        System.out.println("1-> User name: aaaa,  password: aaaa");
        System.out.println("2-> User name: xxxx,  password: xxxx");
        
        HotelLogic hotelMyApp = new HotelLogic();
        hotelMyApp.loginMenu();


    }


}
