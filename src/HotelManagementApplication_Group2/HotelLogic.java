package HotelManagementApplication_Group2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class HotelLogic {

    private Scanner input = new Scanner(System.in);
    private LinkedList<Employee> employees = new LinkedList<>();
    private LinkedList<Customer> customers = new LinkedList<>();
    private LinkedList<Booking> bookings = new LinkedList<>();
    private LinkedList<Room> rooms = new LinkedList<>();

    private String ssn;
    private Random random = new Random();


    public HotelLogic() {
        // populateRoomArrayList();                //Here in the problem when I call this method so the java.io.NotSerializableException: HotelManagementApplication_Group2.Room happened.
        // testInformation();                       //User these two calls to create a database one one time. So that why I keep them here as a comment
        load();
    }
    // menu methods.

    public void loginMenu() throws IOException {
        System.out.println("======================");
        System.out.println("Welcome to our hotel ");
        System.out.println("======================");
        System.out.println();
        System.out.println("====Login menu===");
        System.out.println("1> Customer     |");
        System.out.println("2> Employee     |");
        System.out.println("3> Exit         |");
        System.out.println("=================");
        boolean done = false;
        int choice = 0;
        while (!done) {
            try {
                System.out.printf(">>>> ");
                choice = input.nextInt();
                input.nextLine();
                done = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid input, Try again !");
            }
        }

        if (choice == 1) {
            customersLogin();
        } else if (choice == 2) {

            // User name "1234" and password "1234"

            System.out.printf("User name : ");
            String userName = input.nextLine();
            System.out.printf("Password  : ");
            String employeePassWord = input.nextLine();
//
//            for (Employee employee : employees) {
//
//                if (employee.getUserName().equals(userName) && employee.getEmployeePassWord().equals(employeePassWord)) {
//                    employeesMenu();
//                }
//            }
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getUserName().equals(userName) && employees.get(i).getEmployeePassWord().equals(employeePassWord)) {
                    employeesMenu();
                } else {
                    System.out.println("employees Not Found");
                }
            }

        } else if (choice == 3) {
//        if (choice == 1) {                    // here the will app work but not correct checking
//            customersLogin();
//        } else if (choice == 2) {
//            System.out.printf("User name : ");
//            String userName = input.nextLine();
//            System.out.printf("Password  : ");
//            String employeePassWord = input.nextLine();
//            if (employeePassWord.equals("1234")) {
//                employeesMenu();
//            } else {
//                System.out.println("Invalid Password !");
//                loginMenu();
//            }
//
//        } else if (choice == 3) {
            System.out.println("Thanks for now !! ");
            System.exit(0);
        } else {
            System.out.println("Invalid option! ");
            loginMenu();
        }

    }

    public void customersLogin() {        // I don't have this method, it needs to push it.

    }

    public void employeesMenu() {

        while (true) {
            menu();
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                customerManagement();
            } else if (choice == 2) {
                bookingManagement();
            } else if (choice == 3) {
                roomManagement();
            }
        }

    }

    public void menu() {
        System.out.println("=== ==== === === === === === ===");
        System.out.println("----- Menu of employee ---- ");
        System.out.println("1- Customer management");
        System.out.println("2- Booking management");
        System.out.println("3- Room management");
        System.out.println("=== ==== === === === === === ===");
        System.out.print("Enter your choice:  ");


    }

    public void roomManagement() {
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

    public void customerManagement() {
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
                addCustomerAfterCheckIfTheCustomerExist();
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

    public void bookingManagement() {
        System.out.println("=== ==== === === === === === ===");
        System.out.println("1> Add booking");
        System.out.println("2> Cancel booking");
        System.out.println("3> View all booking");
        System.out.println("0> Return to the menu ");
        System.out.println("=== ==== === === === === === ===");
        System.out.println();
        int choice;

        try {
            System.out.print("Enter your choice: ");
            String choiceString = input.nextLine();
            choice = Integer.parseInt(choiceString);
            if (choice == 1) {
//                addBooking();
            } else if (choice == 2) {
                cancelBooking();
            } else if (choice == 3) {
                viwBooking();
            } else if (choice == 0) {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!, please choose from the menu");
            return;
        }
    }


    // Customer methods.
    public Customer addCustomer() {
        System.out.print("Customer's name: ");
        String customerName = input.nextLine();
        System.out.print("Customer's address: ");
        String customerAddress = input.nextLine();
        System.out.print("TelephoneNumber: ");
        String customerTelephoneNumber = input.nextLine();
        System.out.print("E-mail: ");
        String customerEmail = input.nextLine();

        String userName = customerName.substring(0, 5);


        int customerPassword = random.nextInt(9999);

        Customer customer = new Customer(ssn, customerName, customerAddress, customerTelephoneNumber,
                customerEmail, customerPassword, userName);       // Create new customer object and save it in the customers LinkedList
        customers.add(customer);
        save();                         // save the new customer in the database.

        // Text file for customer information.
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

        return customer;
    }

    public void addCustomerAfterCheckIfTheCustomerExist() {
        System.out.println("Enter customer's SSN, SSN must to be 12 digit numbers");
        System.out.print("Customer SSN:  ");
        ssn = input.nextLine();                                             // This method will take ssn as input and check if the ssn exist in the CustomerArrayList
        boolean isEmpty = customers.isEmpty();
        if (isEmpty) {
            addCustomer();
        } else {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getSsn().equals(ssn)) {
                    System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
                    System.out.println("---- !The customer already exist in the system! ----");
                    System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
                    break;
                }
            }

        }
    }

    public void viewCustomer() {
        System.out.println("\u001b[34m" + "---- All customers in the hotel ----" + "\u001b[0m");
        System.out.println();
        System.out.println("\u001b[33m" + "Customer name\t\tSSN\t\t\t\tAddress" + "\t\t\tTelephone number\t" + "Email\t\t" + "\u001b[0m");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getName() + "\t\t\t\t" + customers.get(i).getSsn() + "\t\t" + customers.get(i).getAddress()
                    + "\t" + customers.get(i).getCustomerTelephoneNumber() + "\t\t\t" + customers.get(i).getEmail());
            System.out.println("\u001b[33m" + "---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- --- ---- -----" + "\u001b[0m");
        }
    }

    public void search() {
        System.out.printf("Customers name : ");
        String name = input.nextLine();
        int bookingID = 0;
        boolean done = false;
        while (!done) {
            try {
                System.out.printf("Booking ID     : ");
                bookingID = input.nextInt();
                input.nextLine();
                done = true;
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getName().equalsIgnoreCase(name) && bookings.get(i).getBookingId() == bookingID) {
                        System.out.println("++++++++++++++++++++++++++");
                        System.out.println("Customer's info : ");
                        System.out.println("Name     : " + customers.get(i).getName());
                        System.out.println("SSN      : " + customers.get(i).getSsn());
                        System.out.println("Address  : " + customers.get(i).getAddress());
                        System.out.println("++++++++++++++++++++++++++");
                        System.out.println("Customer's booking info : ");
                        System.out.println("Booking ID      : " + bookings.get(i).getBookingId());
                        System.out.println("Check in date   : " + bookings.get(i).getCheckInDate());
                        System.out.println("Check out date  : " + bookings.get(i).getCheckOutDate());
                        System.out.println("Room number     : " + rooms.get(i).getRoomNumber());
                        System.out.println("Price per night : " + rooms.get(i).getPrice() + " SEK");
                        System.out.println("Total price     : " + bookings.get(i).getTotalPrice() + " SEK");
                        System.out.println("++++++++++++++++++++++++++");
                    }
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid booking ID, Try again !");
            }
        }

    }

    public void editCustomerInformation() {
        int oneOrTwo;
        int customerNumber;                                                                                     // Modify information for customer which have booking in the hotel
        System.out.println("--- ---Change the information of customers currently in the hotel--- ---");
        System.out.println();

        for (int i = 0; i < customers.size(); i++) {
            System.out.println("[" + i + "]" + customers.get(i));
        }

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

    }


    //Booking methods.

    public void addBooking(Employee employee) {

    }
    //    public void addNewBookingAsEmployee() throws IOException {
//
//        System.out.println("Rooms description : ");
//        System.out.println("----------------------------------------------------");
//        System.out.println("Single bed rooms               : (1  - 6 ) ");
//        System.out.println("Single bed rooms & balcony     : (7  - 12)  ");
//        System.out.println("Single bed and balcony         : (13 - 18) ");
//        System.out.println("Double beds & balcony          : (19 - 24) ");
//        System.out.println("----------------------------------------------------");
//
//        for (int i = 1; i < roomArrayList.size(); i++) {
//            System.out.println("[" + (i) + "]" + roomArrayList.get(i));
//        }
//
//
//        try {
//            System.out.print("which room would you like to book:  ");                                                     // Check if the user input is not integer so will fix it.
//            String userInputString = input.nextLine();
//            userInput = Integer.parseInt(userInputString);
//        } catch (NumberFormatException e) {
//            System.out.println("! Invalid room number , enter a room number from the list");
//            return;
//        }
//
//        if ((userInput < roomArrayList.size() && userInput >= 1)) {
//
//            if (!roomArrayList.get(userInput).isBooked()) {
//
////                Customer customer = addCustomer();
//                addCustomerAfterCheckIfTheCustomerExist();
//
//                boolean done = false;
//                while (!done) {
//                    try {
//                        System.out.printf("Check in date  : ");
//                        String checkINString = input.nextLine();
//                        checkInDate = Integer.parseInt(checkINString);
//                        done = true;
//
//                    } catch (NumberFormatException e) {
//
//                        input.nextLine();
//                        System.out.println("Invalid date (DD), Try again !");
//
//
//                    }
//                }
//                boolean done1 = false;
//                while (!done1) {
//                    try {
//                        System.out.printf("Check out date  : ");
//                        String checkOutString = input.nextLine();
//                        int checkOutDate = Integer.parseInt(checkOutString);
//                        done1 = true;
//
//                    } catch (NumberFormatException e) {
//                        input.nextLine();
//                        System.out.println("Invalid date (DD), Try again !");
//
//                    }
//                }
//
//                boolean done2 = false;
//                while (!done2) {
//                    try {
//                        System.out.println("How many nigh? :");
//                        String numberOfNightString = input.nextLine();
//                        int numberOfNight = Integer.parseInt(numberOfNightString);
//                        done2 = true;
//
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid number , Try again !");
//
//                    }
//                }
//                int bookingNumber = random.nextInt(1000);                                                           // generate a random booking number and then save it in the text file.
//
//
//                System.out.println("-- -- Reservation confirmation -- --");
//                System.out.println("Booking number " + bookingNumber + ",check in date: " + checkInDate + ",check out date: " + checkOutDate + ",total price" + totalPrice());
//
//                System.out.println("Confirm the booking (YES or No)");
//                String confirm = input.nextLine();
//                confirm = confirm.toUpperCase();
//                if (confirm.equals("YES")) {
//                    //print the booking information to confirm all information and total price.
//
//                    roomArrayList.get(userInput).setBooked(true);
//                    Booking booking = new Booking(bookingNumber, checkInDate, checkOutDate, totalPrice(),userInput);
//                    bookingArrayList.add(booking);
//
//                    System.out.println("Thanks for your booking");
//
////
////                    ObjectOutputStream objectOutputStream = null;
////                    FileOutputStream fileOutputStream = null;
////                    try{
////                        fileOutputStream = new FileOutputStream("booking.txt", true);
////                        objectOutputStream = new ObjectOutputStream(fileOutputStream);
////                        objectOutputStream.writeObject(bookingArrayList);
////                    } catch (Exception ex) {
////                        ex.printStackTrace();
////                    } finally {
////                        if(objectOutputStream != null){
////                            objectOutputStream.close();
////                        }
////                    }
//                    bookingArrayList.add(booking);
//
//
////                    try {
////                        new FileWriter("CustomerList.txt", true);
////                        new File("CustomersList.txt");
////                        BufferedWriter writer = new BufferedWriter(fileWriter);
////                        fileContent = ("Customer name: " + customer.getName() + ", SSN:" + customer.getSsn() + ", Address: " + customer.getAddress()
////                                + ", Telephone: " + customer.getCustomerTelephoneNumber() + ", E-mail" + customer.getEmail() + "Booking Number" + bookingNumber + "\n");
////                        writer.write(fileContent);
////                        writer.flush();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////
////                    }
//
//                } else if (confirm.equals("NO")) {
//                    System.out.println("The reservation has not been confirmed, thank you!");
//                }
//
//            } else if (roomArrayList.get(userInput).isBooked()) {
//                System.out.println("----The room already booked!----");
//                System.out.println("------Choose another room-------");
//
//            }
//        } else {                                                                                                        // if the user input invalid , room number in this case will be between 1 and 24 / we have only now 24 rooms.
//            System.out.println("! Invalid input, enter a room number");
//        }
//    }

    public void viwBooking() {
        System.out.println("All booking in the hotel");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println("[" + i + "]" + bookings.get(i));
        }
    }

    public void cancelBooking() {
        System.out.printf("Customers name : ");
        String name = input.nextLine();
        System.out.printf("Booking ID     : ");
        int bookingID = input.nextInt();
        input.nextLine();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId() == bookingID && customers.get(i).getName().equalsIgnoreCase(name)) {
                customers.remove(i);
                rooms.get(i).setBooked(false);
                bookings.remove(i);
                System.out.println("Done ! ");
            }
        }

    }

    //Room methods.

    public void editRoomInformation() {
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

    public void viewAllRoom() {
        System.out.println("-----All room at the hotel-----");
        System.out.println("\u001b[33m" + "Room Number\t\tBed System\t\tBalcony\t\tPrice per night" + "\u001b[0m");
        for (int i = 1; i < rooms.size(); i++) {

            String isBookedStatus = rooms.get(i).isHasBalcony() ? "Yes" : "No";

            System.out.println(rooms.get(i).getRoomNumber() + "\t\t\t\t" + rooms.get(i).getTypeOfBed() + "\t\t" +
                    isBookedStatus + "\t\t\t" + rooms.get(i).getPrice());
        }
    }

    public void availableRooms() {

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

    public void viewBookedRoom() {

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

    public void addNewRoom() {

        boolean hasBalcony = false;
        System.out.println("////You adding new room to the hotel/////");
        System.out.println("Enter the room number do you want to add");
        int roomNumber = Integer.parseInt(input.nextLine());

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

        System.out.println("What is the price per night");
        double price = input.nextDouble();

        Room room = new Room(roomNumber, typeOfBed, hasBalcony, false, price);
        rooms.add(room);

        // add room to text file as data base to recall them if we restart the program.
        // it is worked as a database to the room objects.

        System.out.println("Thank you");
        System.out.println("You added new room to the hotel");
        save();


    }

    public void outOfOrder() {
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

    public void save() {
        ReadAndWrite readAndWrite = new ReadAndWrite();
        readAndWrite.saveUsersCustomer(customers);
        readAndWrite.saveUsersEmployee(employees);
        readAndWrite.saveBookings(bookings);
        readAndWrite.saveRooms(rooms);
    }

    public void load() {
        ReadAndWrite readAndWrite = new ReadAndWrite();
        customers = readAndWrite.readCustomers();
        employees = readAndWrite.readEmployees();
        bookings = readAndWrite.readBookings();
        rooms = readAndWrite.readRooms();

    }

    public void testInformation() {
        Employee employee1 = new Employee("1234", "1234", "Muhannad ", "0768837489", 1);
        employees.add(employee1);
        Employee employee2 = new Employee("4321", "4321", "Wills", "0768837489", 2);
        employees.add(employee2);


        Customer customer1 = new Customer("121212-1212", "Johan", "Kristianstad",
                "0722880025", "johan@gmail.se", 111, "johann");
        customers.add(customer1);

        Customer customer2 = new Customer("999999-9999", "Adam", "Kristianstad",
                "0788226699", "adam@gmail.se", 111, "johan");
        customers.add(customer2);

        save();
    }

    public void populateRoomArrayList() {                   // Create 24 rooms
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
    }                                                                                     // Create 24 rooms and save them in the ArrayList room.

}

