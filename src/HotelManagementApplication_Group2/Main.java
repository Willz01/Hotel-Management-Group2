package HotelManagementApplication_Group2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static HotelLogic hotelMyApp;

    static {
        try {
            hotelMyApp = new HotelLogic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Main myApp = new Main();
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        Employee Wills = new Employee("wills", "pass");
        Employee Muhannad = new Employee("muhannad", "pass");
        Employee Piotr = new Employee("piotr", "pass");
        Employee Muzi = new Employee("muzi", "pass");
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(Wills);
        employees.add(Muhannad);
        employees.add(Piotr);
        employees.add(Muzi);

        ArrayList<Customer> customers = new ArrayList<>();

        myApp.employeeLogin(employees, customers);


    }



    public void employMenu() {
        hotelMyApp.existingRooms();

        System.out.println("Employee");                 // The employee menu, here only for testing our method the menu will finish soon.
            System.out.println("1> new customer");          // add customer to the system without adding booking
            System.out.println("2> Add booking");           // adding booking with adding customer
            System.out.println("3> List of customers");
            System.out.println("4> Search");
            System.out.println("5> Cancel booking");
            System.out.println("6> View all booking");
            System.out.println("Enter your choice: ");

            int choice = input.nextInt();
            if (choice == 1) {
                hotelMyApp.addCustomer();
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
    }
    public void employeeLogin(ArrayList<Employee> employees, ArrayList<Customer> customers) {
        boolean loggedOut = true;

        System.out.println ("Login as employee or customer?\n1> Employee\n2> Customer");
        int employeeOrCustomer = input.nextInt();

        switch (employeeOrCustomer) {
            case 1:
                while (loggedOut == true) {


                    System.out.println("Enter your employee username: ");

                    String userCheck = input.nextLine();
                    userCheck = input.nextLine();
                    System.out.println("Enter your employee password: ");

                    String passCheck = input.nextLine();


                    for (int i = 0; i < employees.size(); i++) {
                        if (employees.get(i).getUsername().compareTo(userCheck) == 0 && employees.get(i).getPassword().compareTo(passCheck) == 0) {

                            loggedOut = false;
                            System.out.println("Login successful");
                            while (loggedOut == false) {
                                myApp.employMenu();
                            }
                        } else {
                            System.out.println("Invalid username or/and password\nTry again !");
                        }
                    }
                }
            case 2:
                while (loggedOut == true) {

                    System.out.println("Enter your customer username: ");

                    String userCheck = input.nextLine();
                    userCheck = input.nextLine();
                    System.out.println("Enter your customer password: ");

                    String passCheck = input.nextLine();

                    for (int i = 0; i < customers.size(); i++) {
                        if (employees.get(i).getUsername().compareTo(userCheck) == 0 && employees.get(i).getPassword().compareTo(passCheck) == 0) {

                            loggedOut = false;
                            System.out.println("Login successful");
                        } else {
                            System.out.println("Invalid username or/and password\nTry again");
                        }
                    }
                }
        }
    }
}
