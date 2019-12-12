package HotelManagementApplication_Group2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Room> roomArrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        Main myApp = new Main();
        HotelLogic hotelMyApp = new HotelLogic();
        hotelMyApp.existingRooms();

        hotelMyApp.loginMenu();


    }

}
