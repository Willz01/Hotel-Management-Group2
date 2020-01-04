package HotelManagementApplication_Group2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelLogic {

    private Scanner input = new Scanner(System.in);
    private LinkedList<HotelManagementApplication_Group2.Employee> employees = new LinkedList<>();
    private LinkedList<HotelManagementApplication_Group2.Customer> customers = new LinkedList<>();
    private LinkedList<HotelManagementApplication_Group2.Booking> bookings = new LinkedList<>();
    private LinkedList<HotelManagementApplication_Group2.Room> rooms = new LinkedList<>();


    private String ssn;
    private Random random = new Random();
    boolean customerExist = true;


    public HotelLogic() {
//         populateRoomArrayList();                //Here in the problem when I call this method so the java.io.NotSerializableException: HotelManagementApplication_Group2.Room happened.
//         testInformation();                       //User these two calls to create a database one one time. So that why I keep them here as a comment
        load();
    }
    // menu methods.

    void loginMenu() throws IOException {
        int logIn = 1;
        int choice = 0;
        while (true) {
            System.out.println();
            System.out.println("======================");
            System.out.println("Welcome to our hotel ");
            System.out.println("======================");
            System.out.println();
            System.out.println("====Login menu===");
            System.out.println("1> Customer     |");
            System.out.println("2> Employee     |");
            System.out.println("3> Exit         |");
            System.out.println("=================");


            try {
                System.out.print(">>>> ");
                choice = input.nextInt();
                input.nextLine();

            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid input, Try again !");
                continue;
            }


            if (choice == 1) {
                // 1- User name "aaaa" and password "aaaa"
                // 2- user name "xxxx" and password "xxxx"

                System.out.println("Enter your user name");
                String customerUserName = input.nextLine();
                System.out.println("Enter you password: ");
                String customerPassword = input.nextLine();

                for (Customer customer : customers) {
                    if (customerUserName.equals(customer.getUserName()) && customerPassword.equals(customer.getPassword())) {
                        logIn++;
                        if (customer.getClass().equals(Customer.class)) {
                            Customer c = (Customer) customer;
                            customerMenu(c);
                            break;
                        }


                    }

                }
                if (logIn == 1) {
                    System.out.println("***** The user name or password is not correct ****");
                }

            } else if (choice == 2) {

                // 1-User name "1234" and password "1234"
                // 2- user name "4321" and password "4321"

                System.out.printf("Enter your user name : ");
                String userName = input.nextLine();
                System.out.printf("Enter your password  : ");
                String employeePassWord = input.nextLine();


                for (Employee employee : employees) {
                    if (employee.getUserName().equals(userName) && employee.getEmployeePassWord().equals(employeePassWord)) {
                        logIn++;
                        employeesMenu();
                        break;
                    }
                }
                if (logIn == 1) {
                    System.out.println("***** The user name or password is not correct ****");
                }

            } else if (choice == 3) {
                System.out.println("Thanks for now !! ");
                System.exit(0);
                break;
            } else {
                System.out.println("Invalid option! ");
                loginMenu();
            }
        }
    }

    private void customerMenu(Customer customer) throws IOException {        // I don't have this method, it needs to push it.
        int choice;
        while (true) {
            System.out.println("=====================");
            System.out.println("\u001b[34m" + "Welcome to our hotel" + "\u001b[0m");
            System.out.println("=====================");
            System.out.println();
            System.out.println("1- View your booking");
            System.out.println("2- Edit your booking");
            System.out.println("3- Add new booking");
            System.out.println("4- Cancel a booking");
            System.out.println("5- Change your personal information");
            System.out.println("0- Sign out");
            System.out.println();
            System.out.println("=====================");
            System.out.print("Enter your choice: ");


            try {

                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number from the menu");
                input.next();
                continue;
            }
            if (choice == 1) {
                viewBookingAsCustomer();
            } else if (choice == 2) {
                editBooking();
            } else if (choice == 3) {
                addBooking();
            } else if (choice == 4) {
                cancelBookingAsCustomer();
            } else if (choice == 5) {
                editCustomerInformationAsCustomer();
            } else if (choice == 0) {
                System.out.println("\u001b[35m" + "***** Thank for your visiting *****" + "\u001b[0m");

                return;
            }
        }
    }

    private void employeesMenu() {
        int choice = 0;
        while (true) {
            menu();
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number from the menu");
                continue;
            }

            if (choice == 1) {
                customerManagement();
            } else if (choice == 2) {
                bookingManagement();
            } else if (choice == 3) {
                roomManagement();
            } else if (choice == 0) {
                System.out.println("\u001b[35m" + "Thanks for your service" + "\u001b[0m");
                save();
                return;
            }
        }

    }

    private void menu() {
        System.out.println("=== ==== === === === === === ===");
        System.out.println("----- Menu of employee ---- ");
        System.out.println("1- Customer management");
        System.out.println("2- Booking management");
        System.out.println("3- Room management");
        System.out.println("0- Back to log in menu");
        System.out.println("=== ==== === === === === === ===");
        System.out.print("Enter your choice:  ");


    }

    private void roomManagement() {
        System.out.println("=== ==== === === === === === ===");
        System.out.println("1> View available rooms");
        System.out.println("2> View all booked room");
        System.out.println("3> View rooms information");
        System.out.println("4> Edit room information");
        System.out.println("5> Add new room");
        System.out.println("6> A room out of service");
        System.out.println("0> Return to the menu");
        System.out.println("=== ==== === === === === === ===");
        System.out.println();

        int choice;
        try {
            System.out.print("Enter your choice: ");
            String choiceString = input.nextLine();

            choice = Integer.parseInt(choiceString);
            if (choice == 1) {
                availableRooms();
            } else if (choice == 2) {
                viewBookedRoom();
            } else if (choice == 3) {
                viewAllRoom();
            } else if (choice == 4) {
                editRoomInformation();
            } else if (choice == 5) {
                addNewRoom();
            } else if (choice == 6) {
                outOfOrder();
            } else if (choice == 0) {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!, please choose from the menu");
            return;
        }

    }

    private void customerManagement() {
        System.out.println("=== ==== === === === === === ===");
        System.out.println("1> Add new customer");
        System.out.println("2> List of customers");
        System.out.println("3> Search a customer");
        System.out.println("4> Edit customer's information ");
        System.out.println("0> Return to the menu ");
        System.out.println("=== ==== === === === === === ===");
        System.out.println();

        int choice;

        try {
            System.out.print("Enter your choice: ");
            String choiceString = input.nextLine();
            choice = Integer.parseInt(choiceString);

            if (choice == 1) {
                addNewCustomer();
            } else if (choice == 2) {
                viewCustomer();
            } else if (choice == 3) {
                search();
            } else if (choice == 4) {
                editCustomerInformation();
            } else if (choice == 0) {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!, please choose from the menu");
            return;
        }

    }

    private void bookingManagement() {
        System.out.println("=== ==== === === === === === ===");
        System.out.println("1> Add booking");
        System.out.println("2> Cancel booking");
        System.out.println("3> View all booking");
        System.out.println("4> Edit booking");
        System.out.println("0> Return to the menu ");
        System.out.println("=== ==== === === === === === ===");
        System.out.println();
        int choice;

        try {
            System.out.print("Enter your choice: ");
            String choiceString = input.nextLine();
            choice = Integer.parseInt(choiceString);
            if (choice == 1) {
                addBooking();
            } else if (choice == 2) {
                cancelBooking();
            } else if (choice == 3) {
                viwAllBooking();
            } else if (choice == 4) {
                editBooking();
            } else if (choice == 0) {
                return;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input!, please choose from the menu");
            return;
        }
    }

    //Customer methods

    private void addNewCustomer() {

        boolean done = false;
        while (!done) {
            System.out.println("Enter customer's SSN, SSN must to be 12 digit numbers");
            ssn = input.nextLine();

            if (ssn.equalsIgnoreCase("0")) {
                return;
            }
            if (!ssn.matches("\\d{12}")) {
                System.out.println("Social security number must be 12 numbers ");
                continue;
            }
            System.out.println(customers.size());
            for (Customer customer : customers) {
                if (customer.getSsn().equals(ssn)) {
                    System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
                    System.out.println("---- !The customer already exist in the system! ----");
                    System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
                    System.out.println("Enter another SSN or 0 to abort");
                    done = false;


                } else {
                    done = true;

                }
            }
        }
        System.out.print("Customer's name: ");
        String customerName = input.nextLine();
        System.out.print("Customer's address: ");
        String customerAddress = input.nextLine();
        System.out.print("TelephoneNumber: ");
        String customerTelephoneNumber = input.nextLine();
        System.out.print("E-mail: ");
        String customerEmail = input.nextLine();
        String userName = customerName.substring(0, 5);
        String customerPassword = "1111";               // A temporary password that the customer can change it.


        Customer customer = new Customer(ssn, customerName, customerAddress, customerTelephoneNumber,
                customerEmail, customerPassword, userName);       // Create new customer object and save it in the customers LinkedList
        customers.add(customer);
        save();                                                    // save the new customer in the database.


        // Text file for customer information. For the hotel use
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("CustomersInformation", true));
            printWriter.println("-------------------------------------------------------------------------------");
            printWriter.println("| Customer name: " + customer.getName() + " SSN: " + customer.getSsn() + ", Address: " + customer.getAddress()
                    + ", Telephone: " + customer.getCustomerTelephoneNumber() + ", E-mail " + customer.getEmail());
            printWriter.println("-------------------------------------------------------------------------------");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void viewCustomer() {
        System.out.println("\u001b[34m" + "---- All customers in the hotel ----" + "\u001b[0m");
        System.out.println();

        System.out.format("\u001B[33m" + "-----------------------+-------------+---------------------------+------------------+--------------------------+%n" + "\u001b[0m");
        System.out.format("\u001B[33m" + "Customer name          |SSN          |Address                    |Telephone number  |Email                     |%n" + "\u001b[0m");
        System.out.format("\u001B[33m" + "-----------------------+-------------+---------------------------+------------------+--------------------------+%n" + "\u001b[0m");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("| %-20s |%-12s | %-25s | %-16s | %-24s |%n", customers.get(i).getName(), customers.get(i).getSsn(),
                    customers.get(i).getAddress(), customers.get(i).getCustomerTelephoneNumber(), customers.get(i).getEmail());
        }
        System.out.format("\u001B[33m" + "-----------------------+-------------+---------------------------+------------------+--------------------------+%n" + "\u001b[0m");
    }

    private void search() {
        System.out.print("Enter SSN, Telephone number or customer's name: ");
        String userInput = input.nextLine();
        int bookingID = 0;
        boolean done = false;
        while (!done) {
            try {
                System.out.print("Booking ID     : ");
                bookingID = input.nextInt();
                input.nextLine();
                done = true;
                if (bookings.size() == 0) {
                    System.out.println("---- There are no booking in the hotel right now ----");
                } else {
                    for (int i = 0; i < customers.size(); i++) {
                        if ((customers.get(i).getName().equalsIgnoreCase(userInput) ||
                                customers.get(i).getSsn().equalsIgnoreCase(userInput) ||
                                customers.get(i).getCustomerTelephoneNumber().equalsIgnoreCase(userInput)) &&
                                bookings.get(i).getBookingId() == bookingID) {
                            System.out.println("++++++++++++++++++++++++++");
                            System.out.println("\u001b[34m" + "Customer's info : " + "\u001b[0m");
                            System.out.println("Name     : " + customers.get(i).getName());
                            System.out.println("SSN      : " + customers.get(i).getSsn());
                            System.out.println("Address  : " + customers.get(i).getAddress());
                            System.out.println("++++++++++++++++++++++++++");
                            System.out.println("\u001b[34m" + "Customer's booking info : " + "\u001b[0m");
                            System.out.println("Booking ID      : " + bookings.get(i).getBookingId());
                            System.out.println("Check in date   : " + bookings.get(i).getCheckInDate());
                            System.out.println("Check out date  : " + bookings.get(i).getCheckOutDate());
                            System.out.println("Room number     : " + rooms.get(i).getRoomNumber());
                            System.out.println("Price per night : " + rooms.get(i).getPrice() + " SEK");
                            System.out.println("Total price     : " + bookings.get(i).getTotalPrice() + " SEK");
                            System.out.println("++++++++++++++++++++++++++");
                        }
                    }
                }

            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid booking ID, Try again !");
            }
        }

    }

    private void editCustomerInformation() {
        int oneOrTwo;
        int customerNumber;                                                                                     // Modify information for customer which have booking in the hotel
        System.out.println("--- ---Change the information of current customers in the hotel--- ---");
        System.out.println();

        System.out.format("\u001B[33m" + "-------+-----------------------+-------------+---------------------------+------------------+--------------------------+%n" + "\u001b[0m");
        System.out.format("\u001B[33m" + " Index |Customer name          |SSN          |Address                    |Telephone number  |Email                     |%n" + "\u001b[0m");
        System.out.format("\u001B[33m" + "-------+-----------------------+-------------+---------------------------+------------------+--------------------------+%n" + "\u001b[0m");

        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("| %-4s | %-21s |%-12s | %-25s | %-16s | %-24s |%n",i, customers.get(i).getName(), customers.get(i).getSsn(),
                    customers.get(i).getAddress(), customers.get(i).getCustomerTelephoneNumber(), customers.get(i).getEmail());
        }
        System.out.format("\u001B[33m" + "-------+-----------------------+-------------+---------------------------+------------------+--------------------------+%n" + "\u001b[0m");
        try {

            System.out.println("Which customer information do you want to change? (enter a customer number)");
            String customerNumberString = input.nextLine();
            customerNumber = Integer.parseInt(customerNumberString);
        } catch (NumberFormatException e) {
            System.out.println("! Invalid input , enter which customer do you want to edit");
            return;
        }

        try {
            System.out.println("1- Change all the information)");
            System.out.println("2- Change certain information");
            System.out.println("Choose >1 or >2");

            String oneOrTwoString = input.nextLine();
            oneOrTwo = Integer.parseInt(oneOrTwoString);
        } catch (NumberFormatException e) {
            System.out.println("! Invalid input , enter which option do you want.");
            return;
        }
        if (oneOrTwo == 1) {
            System.out.println("Enter a new name: ");
            String name = input.nextLine();
            System.out.println("Enter customer's SSN");
            String ssn = input.nextLine();
            System.out.println("Enter customer's address");
            String address = input.nextLine();
            System.out.println("Enter customer's telephone number ");
            String customerTele = input.nextLine();
            System.out.println("Enter customer's email");
            String customerMail = input.nextLine();
            customers.get(customerNumber).setName(name);
            customers.get(customerNumber).setSsn(ssn);
            customers.get(customerNumber).setAddress(address);
            customers.get(customerNumber).setCustomerTelephoneNumber(customerTele);
            customers.get(customerNumber).setEmail(customerMail);
        } else if (oneOrTwo == 2) {
            System.out.println("Which information do you want to change");
            System.out.println("Name?, Address ? , Phone?, SSN? or Email?");
            System.out.println("Enter you choice: ");
            String choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("name")) {
                System.out.println("Enter the name");
                String name = input.nextLine();
                customers.get(customerNumber).setName(name);
                System.out.println("The name changed");
            } else if (choice.equals("ssn")) {
                System.out.println("Enter the SSN:");
                String ssn = input.nextLine();
                customers.get(customerNumber).setSsn(ssn);
                System.out.println("The SSN  changed");
            } else if (choice.equals("address")) {
                System.out.println("Enter the address: ");
                String address = input.nextLine();
                customers.get(customerNumber).setAddress(address);
                System.out.println("The address changed");
            } else if (choice.equals("phone")) {
                System.out.println("Enter the telephone number");
                String phone = input.nextLine();
                customers.get(customerNumber).setCustomerTelephoneNumber(phone);
                System.out.println("The telephone number changed");
            } else if (choice.equals("email")) {
                System.out.println("Enter the email");
                String email = input.nextLine();
                customers.get(customerNumber).setEmail(email);
                System.out.println("The email changed ");

            } else {
                System.out.println("Enter one of the above options please.");
            }
        }
        save();

    }

    private void editCustomerInformationAsCustomer() {

        int choice = 0;
        System.out.println("**** *** ** * ** *** ****");
        System.out.println("----- Edit personal information  ----- ");
        System.out.print(" Please enter your user name again: ");
        String userName = input.nextLine();
        System.out.print("Your password: ");
        String password = input.nextLine();

        for (Customer customer : customers) {
            if (customer.getUserName().equals(userName) && customer.getPassword().equals(password)) {
                System.out.println();
                System.out.println("\u001b[35m\t" + "---- This is your current information -----" + "\u001b[0m\t");
                System.out.println("Name: " + customer.getName());
                System.out.println("Address: " + customer.getAddress());
                System.out.println("Telephone number: " + customer.getCustomerTelephoneNumber());
                System.out.println("Email: " + customer.getEmail());

                while (true) {
                    System.out.println();
                    System.out.println("Which information do you want to change? ");
                    System.out.println("1- Change your address");
                    System.out.println("2- Change your Telephone number");
                    System.out.println("3- Change your email");
                    System.out.println("4- Change your user name");
                    System.out.println("5- Change your password");
                    System.out.println("0- Back to the menu");
                    System.out.print("Enter your choice: ");


                    System.out.println(customer.getName() + "   " + customer.getPassword() + "   " + customer.getPassword() + "   " + customer.getAddress() + "   " + customer.getCustomerTelephoneNumber() + "   " + customer.getEmail());
                    try {

                        String choiceString = input.nextLine();
                        choice = Integer.parseInt(choiceString);

                    } catch (Exception e) {
                        System.out.println("Please choose a number from the menu");
                        return;
                    }
                    switch (choice) {
                        case 1: {

                            System.out.println("Enter the new address: ");

                            String newAddress = input.nextLine();
                            customer.setAddress(newAddress);
                            System.out.println("---- Your address has been changed successfully -----");
                            break;
                        }
                        case 2: {

                            System.out.println("Enter your new telephone number: ");

                            String newTelephoneNumber = input.nextLine();
                            customer.setCustomerTelephoneNumber(newTelephoneNumber);
                            System.out.println("---- Your phone number has been changed successfully -----");
                            break;
                        }
                        case 3: {

                            System.out.println("Enter your new email: ");

                            String newEmail = input.nextLine();
                            customer.setEmail(newEmail);
                            System.out.println("---- Your email has been changed successfully -----");
                            break;
                        }
                        case 4: {

                            System.out.println("Enter your new user name: ");
                            input.next();
                            String newUserName = input.nextLine();
                            System.out.println("Enter it again: ");
                            String newUserName2 = input.nextLine();
                            if (newUserName.equals(newUserName2)) {
                                customer.setUserName(newUserName2);
                                break;
                            }


                        }
                        case 5: {

                            System.out.println("Enter your new password: ");
                            input.next();
                            String newPassword = input.nextLine();
                            System.out.println();
                            customer.setPassword(newPassword);
                            break;
                        }
                        case 0: {
                            System.out.println("***** **** *** ** * ** *** **** *****");
                            System.out.println("Thanks for your visiting!");
                            System.out.println("***** **** *** ** * ** *** **** *****");
                            return;
                        }
                    }

                }
            } else {
                System.out.println();
                System.out.println("---- ----your password or user name is not correct ---- ----");
                System.out.println();
                return;
            }
        }
//        save();
    }

    //Booking methods.

    private void viewBookingAsCustomer() {
        int customerBookinId = 0;

        System.out.println("**** ***** ***** ***** ");
        System.out.print("Enter your booking number: ");
        try {
            customerBookinId = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid booking number, Enter your booking number: ");
            return;
        }
        System.out.print("Enter your password: ");
        String customerPassword = input.nextLine();

        for (Booking booking : bookings) {
            for (Customer c : customers) {
                if (booking.getBookingId() == customerBookinId) {
                    if (c.getPassword().equals(customerPassword)) {
                        System.out.println();
                        System.out.println("\u001b[35m\t" + "---- Your booking information ----" + "\u001b[0m");
                        System.out.println("Booking id: " + customerBookinId);
                        System.out.println("Check in: " + booking.getCheckInDate());
                        System.out.println("Check Out: " + booking.getCheckOutDate());
                        System.out.println("Room number: " + booking.getRoomNbr());
                        System.out.printf("Total price %.2f\n ", booking.getTotalPrice());
                    }

                }
            }
            if (customerBookinId != booking.getBookingId()) {
                System.out.println("*** *** *** **** *** *** ");
                System.out.println("No booking found with this booking number: " + customerBookinId);
                System.out.println("*** *** *** **** *** *** ");
                System.out.println();
            }

        }
    }


    private void addBooking() {


        LinkedList<Integer> list;
        Date checkoutDate;
        Date checkinDate;
        String date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean checkDate;

        System.out.println("Rooms description : ");
        System.out.println("----------------------------------------------------");
        System.out.println("Single bed rooms               : (1  - 6 ) ");
        System.out.println("Single bed rooms & balcony     : (7  - 12)  ");
        System.out.println("Single bed and balcony         : (13 - 18) ");
        System.out.println("Double beds & balcony          : (19 - 24) ");
        System.out.println("----------------------------------------------------");
        System.out.println();


        do {
            checkDate = false;
            // Enter check in date and try parse, if bad input return to previous menu
            System.out.print("Enter check-in date (Format: yyyy-mm-dd) or 0 to abort: ");
            date = input.nextLine();

            if (date.equalsIgnoreCase("0")) {
                return;
            }

            try {
                checkinDate = dateFormat.parse(date);
            } catch (ParseException e) {
                System.out.println("\nDate input incorrect");
                System.out.println();
                return;
            }

            if (!checkinDate.after(new Date(System.currentTimeMillis()))) {
                System.out.println("Check in date cannot be before today");
                System.out.println();
                checkDate = true;
            }
        } while (checkDate);

        do {
            checkDate = false;
            // Enter check out date and try parse, if bad input return to previous menu
            System.out.print("Enter check-out date (Format: yyyy-mm-dd) or 0 to abort: ");
            date = input.nextLine();

            if (date.equalsIgnoreCase("0")) {
                return;
            }

            try {
                checkoutDate = dateFormat.parse(date);
            } catch (ParseException e) {
                System.out.println("\nDate Input incorrect");
                System.out.println();
                return;
            }

            // Check if check out date is before check in date or date not entered, return to previous menu if dates are bad
            try {
                if (checkoutDate.before(checkinDate)) {
                    System.out.println("You have entered a check out date that is before check in- Please try again\n");
                    System.out.println();
                    checkDate = true;
                } else if (checkinDate.equals(checkoutDate)) {
                    System.out.println("Check Out has to be at least the day after check In\n");
                    checkDate = true;
                } else if (checkoutDate.before(new Date(System.currentTimeMillis()))) {
                    System.out.println("Check out has to be at least tomorrow\n");
                    System.out.println();
                    checkDate = true;
                }
            } catch (NullPointerException e) {
                System.out.println("Date was not entered\n");
                checkDate = true;
            }
        } while (checkDate);

        // Print all available rooms and check the user input against booked rooms
        list = viewAvailableRoomDate(checkinDate, checkoutDate, true, -1);
        int roomNumber;
        while (true) {
            System.out.print("\nEnter RoomNumbers or 0 to abort: ");
            try {
                roomNumber = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(",invalid input please try again\n");
                input.next();
                return;
            }

            if (roomNumber == 0) {
                return;
            }

            if (list != null && !list.contains(roomNumber) && roomNumber > 0) {
                break;
            } else {
                System.out.println("Room not available for the given Dates");
            }
        }
        // evaluate days between check in and check out
        int numberOfDays = (int) ((checkoutDate.getTime() - checkinDate.getTime()) / (1000 * 60 * 60 * 24));

        // find room for current booking
        Room temp = null;
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                temp = r;
            }
        }

        // calculate totalprice
        double price;
        if (temp != null) {
            price = temp.getPrice() * numberOfDays;
        } else {
            return;
        }


        Random rand = new Random();
        int bookingNumber = rand.nextInt(9999);

        // Print confirmation info

        System.out.println("\n\t\t***Confirmation***");
        System.out.println("Your booking id is: " + bookingNumber);
        System.out.println("Thr room that you chose has the number: " + temp.getRoomNumber());
        System.out.println("Your check in will be at: " + checkinDate);
        System.out.println("Your check out will be at: " + checkoutDate);
        System.out.printf("The room that you chose costs per day %.2f", temp.getPrice());
        System.out.printf(" The total price is:  %.2f", price);
        System.out.print("\nAll information are correct (Y/N)? ");
        String choice = input.nextLine();

        // check input for confirmation and create new booking, print in logg and add booking to customer list of bookings
        if (choice.equalsIgnoreCase("y")) {
            Booking booking = new Booking(bookingNumber, checkinDate, checkoutDate, price, roomNumber);
            booking.setTotalPrice(price);


            bookings.add(booking);
            save();
            new ReadAndWrite().write(booking.getBookingId(), checkinDate, checkoutDate, temp.getRoomNumber(), false);
            System.out.println("** ** ** ** ** ** ** ** ** ** ** ");
            System.out.println("Thank you for choosing our hotel!");
            System.out.println("** ** ** ** ** ** ** ** ** ** ** ");

        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println();
            System.out.println("----- ---Thanks for your visiting--- -----");
            System.out.println();
        } else {
            System.out.println("** ** ** ** ** ** ** ");
            System.out.println("Invalid input");
            System.out.println("** ** ** ** ** ** ** ");
        }
    }

    private LinkedList<Integer> viewAvailableRoomDate(Date tempStart, Date tempEnd, boolean print, int bookId) {

        LinkedList<Integer> roomNbrs = new LinkedList<>();

        try {
            for (Booking booking : bookings) {
                if ((tempStart.after(booking.getCheckInDate()) && tempStart.before(booking.getCheckOutDate())) ||
                        (tempEnd.after(booking.getCheckInDate()) && tempEnd.before(booking.getCheckOutDate())) ||
                        (tempStart.before(booking.getCheckInDate()) && tempEnd.after(booking.getCheckOutDate())) ||
                        (tempStart.equals(booking.getCheckInDate()) && tempEnd.equals(booking.getCheckOutDate()))) {
                    if (booking.getBookingId() != bookId) {
                        roomNbrs.add(booking.getRoomNbr());
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error getting booking numbers");
            return null;
        }

        Room temp;
        boolean counter = false;
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 1; i < rooms.size(); i++) {
            temp = rooms.get(i);

            if (!roomNbrs.contains(temp.getRoomNumber())) {
                if (!counter && print) {
                    System.out.println("Listing all registered available rooms at the Hotel \n");
                    System.out.println("Room\tBeds\t\tPrice/Night\t\tBalcony\n");
                    counter = true;
                }

                if (print) {
                    String isHasBalconyStatus = rooms.get(i).isHasBalcony() ? "Yes" : "No";
                    System.out.println(
                            temp.getRoomNumber() + "\t\t" +
                                    rooms.get(i).getTypeOfBed() + "\t\t" +
                                    df.format(rooms.get(i).getPrice()) + "\t\t\t" +
                                    isHasBalconyStatus);

                }
            }
        }
        if (!counter && print) {
            System.out.println("There are nona available rooms at the Hotel !");
            return null;
        }
        return roomNbrs;
    }

    public void viwAllBooking() {
        if (bookings == null) {
            System.out.println("There is no booking in the hotel");
        } else {

            System.out.println("All booking in the hotel");
            System.out.println("Booking Id\t\t" + "Room Number\t\t\t\t" + "Check in date\t\t\t\t\t\t" + "check out date");
            for (int i = 0; i < bookings.size(); i++) {
                System.out.println(bookings.get(i).getBookingId() + "\t\t\t\t\t" + bookings.get(i).getRoomNbr() + "\t\t\t\t\t" +
                        bookings.get(i).getCheckInDate() + "\t\t" + bookings.get(i).getCheckOutDate());
            }
        }
    }

    public void cancelBooking() {
        viwAllBooking();
        if (bookings.size() > 0) {
            try {
                System.out.print("\nChoose the booking that you want to delete or just 0 to abort: ");
                int choice = input.nextInt();
                if (choice == 0) {
                    return;
                } else if (choice > bookings.getLast().getBookingId()) {
                    System.out.println("This booking id is invalid");
                }

                for (Booking b : bookings) {
                    if (b.getBookingId() == choice) {
                        bookings.remove(b);
                        System.out.println("Booking Removed");
                    }

                }

            } catch (ConcurrentModificationException e) {
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
            }

        } else {
            System.out.println("There are no bookings to show");
        }
        save();
    }

    public void cancelBookingAsCustomer() {
        int bookingId = 0;
        System.out.println();
        System.out.print("Enter your booking id: ");
        try {
            bookingId = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid booking number, Enter your booking number: ");
            return;
        }
        input.nextLine();
        System.out.print("Enter your password: ");
        String customerPassword = input.nextLine();

        for (Booking booking : bookings) {
            for (Customer customer : customers) {
                if (bookingId == booking.getBookingId()) {
                    if (customerPassword.equals(customer.getPassword())) {

                        System.out.println();
                        System.out.println("\u001b[35m\t" + "---- Your booking information ----" + "\u001b[0m");
                        System.out.println("Booking id: " + booking);
                        System.out.println("Check in: " + booking.getCheckInDate());
                        System.out.println("Check Out: " + booking.getCheckOutDate());
                        System.out.println("Room number: " + booking.getRoomNbr());
                        System.out.printf("Total price %.2f\n ", booking.getTotalPrice());
                    }

                }
                if (booking.getBookingId() != bookingId) {
                    System.out.println("*** *** *** **** *** *** ");
                    System.out.println("No booking found with this booking number: " + booking);
                    System.out.println("*** *** *** **** *** *** ");
                    System.out.println();
                }

            }
        }
        System.out.println();
        System.out.println("**** *** ** * ** *** **** ");
        System.out.println("Do you want to cancel your booking (Y/N)");
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase("y")) {
            for (Booking booking : bookings) {
                if (booking.getBookingId() == bookingId) {
                    bookings.remove(booking);
                    System.out.println("Booking canceled");
                    System.out.println("Thanks for your visiting");
                    System.out.println();
                    save();
                }
            }

        } else if (userInput.equalsIgnoreCase("n")) {
            System.out.println("Thanks for staying in our hotel");
        } else {
            System.out.println("Invalid input");

        }
    }

    private boolean checkDates(Date checkIn, Date checkOut) {

        if (checkOut.before(checkIn) ||
                checkOut.equals(checkIn) ||
                checkIn.before(new Date(System.currentTimeMillis())) ||
                checkOut.before(new Date(System.currentTimeMillis()))) {
            return false;
        } else {
            return true;
        }
    }

    private void editBooking() throws IOException {
        Date CheckDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        int choice = 0;
        int bookingId;
        int bookingNumber = 0;
        System.out.println("Enter booking id: ");
        bookingId = input.nextInt();

        for (int i = 0; i < bookings.size(); i++) {
            if (bookingId == bookings.get(i).getBookingId()) {
                bookingId = bookingNumber;
                System.out.println("Information about the booking");
                System.out.println("=== === === === === === === === === === === === ");
                System.out.println(bookings.get(bookingNumber));
                System.out.println("=== === === === === === === === === === === === ");
                System.out.println("Which information do you want to change ");

                while (choice != -1) {
                    System.out.print("\n\n" +
                            "[1] Check in date\n" +
                            "[2] Check out date\n" +
                            "[3] Change Entire booking\n" +
                            "[4] Back to menu \n" +
                            "Enter your choice: ");
                    try {
                        choice = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Please choose a number from the menu");
                        input.next();
                        continue;
                    }
                    switch (choice) {
                        case 1: {
                            System.out.print("Check in date for this booking is :\t");
                            System.out.print(bookings.get(bookingNumber).getCheckInDate());
                            input.nextLine();
                            System.out.println("Enter new check in date (yyyy-MM-dd)");
                            date = input.nextLine();
                            try {
                                CheckDate = dateFormat.parse(date);
                            } catch (ParseException e) {
                                System.out.println("\nInvalid Date\n");
                                return;
                            }
                            for (int j = 0; j < bookings.size(); j++) {
                                LinkedList<Integer> list = viewAvailableRoomDate(CheckDate, bookings.get(bookingNumber).getCheckOutDate(),
                                        false, bookings.get(bookingNumber).getBookingId());
                                if (list != null && !list.contains((bookings.get(bookingNumber).getRoomNbr()))) {
                                    if (!checkDates(CheckDate, bookings.get(bookingNumber).getCheckOutDate())) {
                                        System.out.println("= == === ==== === == =");
                                        System.out.println("This date is not possible");
                                        System.out.println("= == === ==== === == =");
                                        System.out.println();
                                        return;
                                    }
                                    bookings.get(bookingNumber).setCheckInDate(CheckDate);
                                    updateTotalPriceBooking(bookings.get(bookingNumber));
                                    new ReadAndWrite().write(bookingId, CheckDate, bookings.get(i).getCheckOutDate(),
                                            bookings.get(bookingNumber).getRoomNbr(), true);
                                } else {
                                    System.out.println("The date is not available ");
                                    viewAvailableRoomDate(CheckDate, bookings.get(bookingNumber).getCheckOutDate(),
                                            true, bookings.get(bookingNumber).getBookingId());
                                }

                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Check out date for this booking: \t");
                            System.out.print(bookings.get(bookingNumber).getCheckOutDate());
                            input.nextLine();
                            System.out.println("Enter new check out date (yyyy-MM-dd)");
                            date = input.nextLine();
                            try {
                                CheckDate = dateFormat.parse(date);
                            } catch (ParseException e) {
                                System.out.println("\nInvalid Date\n");
                                return;
                            }
                            LinkedList<Integer> list = viewAvailableRoomDate(bookings.get(bookingNumber).getCheckInDate(),
                                    CheckDate, false, bookings.get(bookingNumber).getBookingId());
                            if (list != null && !list.contains(bookings.get(bookingNumber).getRoomNbr())) {
                                if (!checkDates(bookings.get(bookingNumber).getCheckInDate(), CheckDate)) {
                                    System.out.println("= == === ==== === == =");
                                    System.out.println("This date is not possible");
                                    System.out.println("= == === ==== === == =");
                                    System.out.println();
                                    return;
                                }
                                bookings.get(bookingNumber).setCheckOutDate(CheckDate);
                                updateTotalPriceBooking(bookings.get(bookingNumber));
                                new ReadAndWrite().write(bookingId, CheckDate, bookings.get(i).getCheckOutDate(),
                                        bookings.get(bookingNumber).getRoomNbr(), true);
                            } else {
                                System.out.println("\u001b[33m" + "The date is not available " + "\u001b[0m");
                                viewAvailableRoomDate(bookings.get(bookingNumber).getCheckInDate(), CheckDate, true,
                                        bookings.get(bookingNumber).getBookingId());
                            }
                        }
                        break;
                        case 3: {
                            input.nextLine();
                            addBooking();
                            try {
                                for (Booking booking : bookings) {
                                    if (booking.getBookingId() == bookingNumber) {
                                        bookings.remove(booking);
                                        System.out.println("\u001b[33m" +
                                                "The booking is removed from the system"
                                                + "\u001b[0m");
                                        break;
                                    }
                                }
                            } catch (ConcurrentModificationException e) {
                                System.out.println("\u001b[33m" + "Something went wrong" + "\u001b[0m");
                                continue;
                            }
                        }
                        break;
                        case 4: {
                            choice = -1;
                            break;
                        }
                    }
                }

            }
        }
    }

    private void updateTotalPriceBooking(Booking booking) {
        int nbrOfDays = (int) ((booking.getCheckOutDate().getTime() - booking.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24));

        for (Room room : rooms) {
            if (room.getRoomNumber() == booking.getRoomNbr()) {
                booking.setTotalPrice((room.getPrice() * nbrOfDays));
            }
        }
        System.out.println("\u001b[33m" + "\nThe booking updated\n" + "\u001b[0m");

    }


    private void viewBookingById(int bookinId, boolean print, Customer customer) {
        if (bookinId == -1) {
            return;
        }
        boolean noBooking = true;

        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookinId) {
                System.out.println("\nBooking ID: " + bookinId);
                System.out.println("Check in: " + booking.getCheckInDate());
                System.out.println("Check Out: " + booking.getCheckOutDate());
                System.out.println("Room Number: " + booking.getRoomNbr());
                System.out.printf("Total Price: %.2f\n", booking.getTotalPrice());
                noBooking = false;
                if (print) {
                    new ReadAndWrite().printBooking(booking, (Customer) customer);
                }
            }
        }
        if (noBooking) {
            System.out.println("No Booking Found with bookingId: " + bookinId);
        }
    }
    //Room methods.

    private void editRoomInformation() {
        int roomNumber;

        System.out.println("\u001b[34m" + "Room number\t\tRoom status\t\tPrice per night" + "\u001b[0m");
        System.out.println("\u001b[34m" + "---- ---- ---- ---- ---- ---- ---- ---- ---- " + "\u001b[0m");
        for (int i = 1; i < rooms.size(); i++) {
            System.out.println(rooms.get(i));
        }

        try {
            System.out.println("Which room will you modify?");
            String roomNumberString = input.nextLine();
            roomNumber = Integer.parseInt(roomNumberString);
        } catch (NumberFormatException e) {
            System.out.println("! Invalid input , enter a room number from the list");
            return;
        }


        System.out.println("Does the room have a balcony? ");
        System.out.println("yes or no");
        String yesOrNo = input.nextLine();
        yesOrNo = yesOrNo.toLowerCase();
        if (yesOrNo.equals("yes")) {
            rooms.get(roomNumber).setHasBalcony(true);
        } else if (yesOrNo.equals("no")) {
            rooms.get(roomNumber).setHasBalcony(false);
        }

        System.out.println("Which type of bed has the room, (single bed or double bed?)");
        String typeOfBed = input.nextLine();
        typeOfBed = typeOfBed.substring(0, 1).toUpperCase() + typeOfBed.substring(1).toLowerCase();
        rooms.get(roomNumber).setTypeOfBed(typeOfBed);

        System.out.println("Price per night for this room:");
        double pricePerNight = input.nextDouble();
        rooms.get(roomNumber).setPrice(pricePerNight);
    }

    private void viewAllRoom() {
        System.out.println("-----All room at the hotel-----");
        System.out.println("\u001b[33m" + "Room Number\t\tBed System\t\tBalcony\t\tPrice per night" + "\u001b[0m");
        for (int i = 1; i < rooms.size(); i++) {

            String isBookedStatus = rooms.get(i).isHasBalcony() ? "Yes" : "No";

            System.out.println(rooms.get(i).getRoomNumber() + "\t\t\t\t" + rooms.get(i).getTypeOfBed() + "\t\t" +
                    isBookedStatus + "\t\t\t" + rooms.get(i).getPrice());
        }
    }

    private void availableRooms() {

        System.out.println("-- -- -- -- -- -- -- -- -- ");
        System.out.println("----All available rooms---- ");
        System.out.println("-- -- -- -- -- -- -- -- -- ");


        System.out.println("\u001b[33m" + "Room Number\t\tBed System\t\tBalcony\t\tPrice per night" + "\u001b[0m");

        for (int i = 0; i < rooms.size(); i++) {

            if (!rooms.get(i).getBooked()) {
                String isBookedStatus = rooms.get(i).isHasBalcony() ? "Yes" : "No";

                System.out.println(rooms.get(i).getRoomNumber() + "\t\t\t\t" + rooms.get(i).getTypeOfBed() + "\t\t" +
                        isBookedStatus + "\t\t\t" + rooms.get(i).getPrice());
            }

        }
    }

    private void viewBookedRoom() {

        for (int i = 1; i < rooms.size(); i++) {
            if (rooms.get(i).getBooked()) {
                System.out.println("-- -- -- -- -- -- -- -- -- ");
                System.out.println("\u001b[33m" + "---- All booked room ----" + "\u001b[0m");
                System.out.println("-- -- -- -- -- -- -- -- -- ");
                System.out.println(rooms.get(i));
            } else {
                System.out.println("== == === === === === ===");
                System.out.println("\u001b[33m" + "There are no booked room" + "\u001b[0m");
                System.out.println("== == === === === === ===");
                break;
            }
        }
    }

    private void addNewRoom() {

        boolean hasBalcony = false;
        int newRoomNumber = rooms.size() + 1;
        System.out.println("The room number will be: " + newRoomNumber);
        System.out.println("\u001b[35m" + "//// Adding new room to the hotel/////" + "\u001b[0m");


        System.out.println("Which type of bed the room have? (Single bed or double bed)");
        String typeOfBed = input.nextLine();
        typeOfBed = typeOfBed.substring(0, 1).toUpperCase() + typeOfBed.substring(1).toLowerCase();

        System.out.println("Does the room have a balcony\nyes or no ");
        String yesOrNo = input.nextLine();
        yesOrNo = yesOrNo.toLowerCase();
        if (yesOrNo.equals("yes")) {
            hasBalcony = true;
        } else if (yesOrNo.equals("no")) {
            hasBalcony = false;
        }

        System.out.println("How much does the room cost per night?");

        double price;
        while (true) {
            try {
                price = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Please enter the room cost per night");
                input.next();
            }
        }
        input.nextLine();
        System.out.println("\n\t\t**** Confirmation ****");
        System.out.println("The new room number: " + newRoomNumber);
        System.out.println("Type of bed: " + typeOfBed);
        if (hasBalcony) {
            System.out.println("The room has a balcony");
        } else {
            System.out.println("The has not a balcony");
        }
        System.out.println("The price per night: " + price);
        System.out.println("**** *** **** *** **** *** **** ***");
        System.out.println();
        System.out.println("\n All information are correct? (Y/N)");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("y")){
            System.out.println("Thank you");
            System.out.println("You added new room to the hotel");
            Room room = new Room(newRoomNumber, typeOfBed, hasBalcony, false, price);
            rooms.add(room);

            // add room to text file as data base to recall them if we restart the program.
            // it is worked as a database to the room objects.
            save();
        }else if (answer.equalsIgnoreCase("n")){
            System.out.println("The room did not add to the system.");
            System.out.println("Thank you");
        }else {
            System.out.println("Invalid input!");
        }





    }

    private void outOfOrder() {
        System.out.println("/// Remove a room from the system ///");
        System.out.println("Which room do you want to remove?");
        for (int i = 1; i < rooms.size(); i++) {
            System.out.println(rooms.get(i));
        }

        try {
            System.out.println("Enter room number: ");


            String roomNumberString = input.nextLine();
            int roomNumber = Integer.parseInt(roomNumberString);
            input.nextLine();
            System.out.printf("Are you sure want to remove the room %s ?", roomNumber + "Yes or No");
            String userAnswer = input.nextLine();
            userAnswer = userAnswer.toLowerCase();
            if (userAnswer.equals("yes")) {

                rooms.get(roomNumber).setRoomNumber(roomNumber);
                rooms.get(roomNumber).setPrice(0);
                rooms.get(roomNumber).setBooked(true);
                rooms.get(roomNumber).setHasBalcony(false);
                rooms.get(roomNumber).setTypeOfBed("Not available room");
                System.out.println("\u001b[34m" + "----------------------------------" + "\u001b[0m");
                System.out.println("Do you want to remove the room from the system");
                System.out.println("y OR n");
                userAnswer = input.nextLine();
                userAnswer = userAnswer.toLowerCase();

                if (userAnswer.equals("y")) {
                    rooms.remove(roomNumber);
                    System.out.println("The room is removed from the system");
                } else if (userAnswer.equals("n")) {
                    System.out.println("\u001b[34m" + "----------------------------------" + "\u001b[0m");
                    System.out.println("The room out of service and still in the system ");
                    System.out.println("\u001b[34m" + "----------------------------------" + "\u001b[0m");
                    System.out.println();
                }

            } else if (userAnswer.equals("no")) {
                System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
                System.out.println("The room still exist in the system");
                System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- ");
            } else {
                System.out.println("Invalid input!");
            }
        } catch (InputMismatchException e) {
            System.out.println("---Invalid input---");
            return;
        }


        save();

    }


    // Write, read methods and creating rooms, employees and customers to make text.

    private void save() {
        ReadAndWrite readAndWrite = new ReadAndWrite();
        readAndWrite.saveUsersCustomer(customers);
        readAndWrite.saveUsersEmployee(employees);
        readAndWrite.saveBookings(bookings);
        readAndWrite.saveRooms(rooms);
    }

    private void load() {
        ReadAndWrite readAndWrite = new ReadAndWrite();
        customers = readAndWrite.readCustomers();
        employees = readAndWrite.readEmployees();
        bookings = readAndWrite.readBookings();
        rooms = readAndWrite.readRooms();

    }

    // these methods uses only one time to create testInformation and these information uses as a database

    private void testInformation() {
        Employee employee1 =
                new Employee("1234", "1234", "Muhannad ",
                        "0768837489", 1);

        employees.add(employee1);

        Employee employee2 =
                new Employee("4321", "4321",
                        "Wills", "0768837489", 2);
        employees.add(employee2);


        Customer customer1 =
                new Customer("121212-1212", "Johan Andersson", "Kristianstad",
                        "0722880025", "johan@gmail.se", "xxxx", "xxxx");
        customers.add(customer1);

        Customer customer2 =
                new Customer("999999-9999", "Adam Smith", "Kristianstad",
                        "0788226699", "adam@gmail.se", "aaaa", "aaaa");
        customers.add(customer2);

        save();
    }

    private void populateRoomArrayList() {                   // Create 24 rooms
        Room store_room = new Room(0, "Store room", false, true, 0);       // unused room but need for easy printing the ArryList.
        rooms.add(store_room);


        for (int i = 1; i <= 6; i++) {
            Room room = new Room(i, "Single bed", false, false, 300);
            rooms.add(room);

        }
        for (int i = 7; i <= 12; i++) {
            Room room = new Room(i, "Single bed", true, false, 400);
            rooms.add(room);

        }
        for (int i = 13; i <= 18; i++) {
            Room room = new Room(i, "Double bed", false, false, 450);
            rooms.add(room);

        }
        for (int i = 19; i <= 24; i++) {
            Room room = new Room(i, "Double bed", true, false, 550);
            rooms.add(room);
        }
        save();
    }
}