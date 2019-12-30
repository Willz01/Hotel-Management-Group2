package HotelManagementApplication_Group2;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {


        System.out.println("1-User name : 1234" + " password  1234");
        System.out.println("2- User name :4321"+ "Password 4321");
        HotelLogic hotelMyApp = new HotelLogic();
        hotelMyApp.loginMenu();


    }


}
