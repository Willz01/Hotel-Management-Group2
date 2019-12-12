package HotelManagementApplication_Group2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        Main myApp = new Main();
        HotelLogic hotelMyApp = new HotelLogic();

        hotelMyApp.existingRooms();

        while (true) {
            myApp.menu();
            int choice = input.nextInt();
            if (choice == 1) {
                hotelMyApp.addCustomerAfterCheckIfTheCustomerExist();
            }
            else if (choice == 2) {
                hotelMyApp.addBooking();
            }
            else if (choice ==3){
                hotelMyApp.viewCustomer();
            }
            else if (choice == 4){
                hotelMyApp.search();
            }
            else if (choice == 5){
                hotelMyApp.cancelBooking();
            }
            else if (choice== 6){
                hotelMyApp.viwBooking();
            }
            else if (choice == 7){
                hotelMyApp.editRoomInformation();
            }
            else if (choice ==8){
                hotelMyApp.viewAllRoom();
            }
            else if (choice ==9){
                hotelMyApp.availableRooms();
            }
            else if (choice ==10){
                hotelMyApp.editCustomerInformation();
            }
            else if (choice ==11){
                hotelMyApp.viewBookedRoom();
            }
        }
    }

    public void menu() {
        System.out.println("Employee");                 // The employee menu, here only for testing our method the menu will finish soon.
        System.out.println("1> new customer");          // add customer to the system without adding booking
        System.out.println("2> Add booking");           // adding booking with adding customer
        System.out.println("3> List of customers");
        System.out.println("4> Search");
        System.out.println("5> Cancel booking");
        System.out.println("6> View all booking");
        System.out.println("7> Edit room information");
        System.out.println("8> View rooms information");
        System.out.println("9> View available rooms");
        System.out.println("10> Edit customer's information ");
        System.out.println(">11View all booked room");
        System.out.println("Enter your choice: ");
    }

}
