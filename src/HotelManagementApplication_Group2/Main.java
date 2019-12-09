package HotelManagementApplication_Group2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        Main myApp = new Main();
        HotelLogic hotelMyApp = new HotelLogic();


        Employee Wills = new Employee("wills", "pass");
        Employee Muhannad = new Employee("muhannad", "pass");
        Employee Piotr = new Employee("piotr", "pass");
        Employee Muzi = new Employee("muzi", "pass");
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(Wills);
        employees.add(Muhannad);
        employees.add(Piotr);
        employees.add(Muzi);

        hotelMyApp.employeeLogin(employees);

        hotelMyApp.existingRooms();


        while (true) {
            myApp.menu();
            int choice = input.nextInt();
            if (choice == 1) {
                hotelMyApp.addNewBookingEmploy();
            }
            if (choice == 2) {
                hotelMyApp.viwBooking();
            }

        }
    }



    public void menu() {
        System.out.println("Welcome to the hotel application");
        System.out.println("1-Add booking ");
        System.out.println("2-view booking");
        System.out.println("3-Cancel a booking");
        System.out.println("Choose: ");

    }
}
