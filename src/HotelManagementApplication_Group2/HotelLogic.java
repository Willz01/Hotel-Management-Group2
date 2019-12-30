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


    public HotelLogic() {
//         populateRoomArrayList();                //Here in the problem when I call this method so the java.io.NotSerializableException: HotelManagementApplication_Group2.Room happened.
//         testInformation();                       //User these two calls to create a database one one time. So that why I keep them here as a comment
        load();
    }
    // menu methods.

    public void loginMenu() {
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

            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getUserName().equals(userName) && employees.get(i).getEmployeePassWord().equals(employeePassWord)) {
                    employeesMenu();
                } else {
                    System.out.println("employees Not Found");
                }
            }

        } else if (choice == 3) {
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
                addNewBookingAsEmployee();
            } else if (choice == 2) {
                cancelBooking();
            } else if (choice == 3) {
                viwAllBooking();
            } else if (choice == 4) {
                editBookingAsEmployee();
            }else if (choice ==0){
                return;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input!, please choose from the menu");
            return;
        }
    }


    // Customer methods.
    public HotelManagementApplication_Group2.Customer addCustomer() {
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

        HotelManagementApplication_Group2.Customer customer = new HotelManagementApplication_Group2.Customer(ssn, customerName, customerAddress, customerTelephoneNumber,
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


    private void addNewBookingAsEmployee() throws IOException {
        int userInput;

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

        int lastBookId = 0;
        if (!bookings.isEmpty()) {
            lastBookId = bookings.getLast().getBookingId();
        }

        int bookId = (lastBookId + 1);

        // Print confirmation info

        System.out.println("\n\t\t***Confirmation***");
        System.out.println("Your booking id is: " + bookId);
        System.out.println("Thr room that you chose has the number: " + temp.getRoomNumber());
        System.out.println("Your check in will be at: " + checkinDate);
        System.out.println("Your check out will be at: " + checkoutDate);
        System.out.printf("The room that you chose costs per day %.2f", temp.getPrice());
        System.out.printf(" The total price is:  %.2f", price);
        System.out.print("\nAll information are correct (Y/N)? ");
        String choice = input.nextLine();

        // check input for confirmation and create new booking, print in logg and add booking to customer list of bookings
        if (choice.equalsIgnoreCase("y")) {
            Booking booking = new Booking(bookId, checkinDate, checkoutDate, price, roomNumber);
            booking.setTotalPrice(price);

            bookings.add(booking);
            save();
            new ReadAndWrite().write(booking.getBookingId(), checkinDate, checkoutDate, temp.getRoomNumber(), false);
            System.out.println("Thank you for choosing our hotel!");
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
                System.out.println("invaild input");
                input.nextLine();
            }

        } else {
            System.out.println("There are no bookings to show");
        }
        save();
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

    private void editBookingAsEmployee ( ) throws IOException {
        Date CheckDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        int choice =0;
        int bookingId;
        int bookingNumber =0;
        System.out.println("Enter booking id: ");
        bookingId = input.nextInt();

        for (int i = 0; i <bookings.size(); i++) {
            if (bookingId == bookings.get(i).getBookingId()){
                bookingId = bookingNumber;
                System.out.println("Information about the booking");
                System.out.println("=== === === === === === === === === === === === ");
                System.out.println(bookings.get(bookingNumber));
                System.out.println("=== === === === === === === === === === === === ");
                System.out.println("Which information do you want to change ");

                while (choice !=-1){
                    System.out.print("\n\n" +
                            "[1] Check in date\n" +
                            "[2] Check out date\n" +
                            "[3] Change Entire booking\n" +
                            "[4] Back to menu \n" +
                            "Enter your choice: ");
                    try {
                        choice = input.nextInt();
                    }catch (Exception e){
                        System.out.println("Please choose a number from the menu");
                        input.next();
                        continue;
                    }
                    switch (choice){
                        case 1:{
                            System.out.print("Check in date for this booking is :\t");
                            System.out.print(bookings.get(bookingNumber).getCheckInDate());
                            input.nextLine();
                            System.out.println("Enter new check in date (yyyy-MM-dd)");
                            date  = input.nextLine();
                            try {
                                CheckDate = dateFormat.parse(date);
                            } catch (ParseException e) {
                                System.out.println("\nInvalid Date\n");
                                return;
                            }
                            for (int j = 0; j <bookings.size() ; j++) {
                                LinkedList<Integer> list = viewAvailableRoomDate(CheckDate, bookings.get(bookingNumber).getCheckOutDate(),
                                        false, bookings.get(bookingNumber).getBookingId());
                                if (list != null && !list.contains((bookings.get(bookingNumber).getRoomNbr()))){
                                    if (!checkDates(CheckDate,bookings.get(bookingNumber).getCheckOutDate())){
                                        System.out.println("= == === ==== === == =");
                                        System.out.println("This date is not possible");
                                        System.out.println("= == === ==== === == =");
                                        System.out.println();
                                        return;
                                    }
                                    bookings.get(bookingNumber).setCheckInDate(CheckDate);
                                    updateTotalpriceBooking(bookings.get(bookingNumber));
                                    new ReadAndWrite().write(bookingId, CheckDate, bookings.get(i).getCheckOutDate(),
                                            bookings.get(bookingNumber).getRoomNbr(), true);
                                }else {
                                    System.out.println("The date is not available ");
                                    viewAvailableRoomDate(CheckDate, bookings.get(bookingNumber).getCheckOutDate(),
                                            true, bookings.get(bookingNumber).getBookingId());
                                }

                            }
                            break;
                        }
                        case 2:{
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
                                    CheckDate, false,  bookings.get(bookingNumber).getBookingId());
                            if (list!=null && !list.contains(bookings.get(bookingNumber).getRoomNbr())){
                                if (!checkDates(bookings.get(bookingNumber).getCheckInDate(),CheckDate)){
                                    System.out.println("= == === ==== === == =");
                                    System.out.println("This date is not possible");
                                    System.out.println("= == === ==== === == =");
                                    System.out.println();
                                    return;
                                }
                                bookings.get(bookingNumber).setCheckOutDate(CheckDate);
                                updateTotalpriceBooking(bookings.get(bookingNumber));
                                new ReadAndWrite().write(bookingId, CheckDate, bookings.get(i).getCheckOutDate(),
                                        bookings.get(bookingNumber).getRoomNbr(), true);
                            }else {
                                System.out.println("\u001b[33m"+"The date is not available "+"\u001b[0m");
                                viewAvailableRoomDate(bookings.get(bookingNumber).getCheckInDate(), CheckDate, true,
                                        bookings.get(bookingNumber).getBookingId());
                            }
                        }
                        break;
                        case 3: {
                            input.nextLine();
                            addNewBookingAsEmployee();
                            try {
                                for (Booking booking : bookings){
                                    if (booking.getBookingId() == bookingNumber){
                                        bookings.remove(booking);
                                        System.out.println("\u001b[33m"+
                                                "The booking is removed from the system"
                                                +"\u001b[0m");
                                        break;
                                    }
                                }
                            }catch (ConcurrentModificationException e){
                                System.out.println("\u001b[33m"+  "Something went wrong"+  "\u001b[0m");
                                continue;
                            }
                        }
                        break;
                        case 4:{
                            choice = -1;
                            break;
                        }
                    }
                }

            }
        }
    }

    private void updateTotalpriceBooking(Booking booking) {
        int nbrOfDays = (int) ((booking.getCheckOutDate().getTime() - booking.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24));

        for (Room room : rooms) {
            if (room.getRoomNumber() == booking.getRoomNbr()) {
                booking.setTotalPrice((room.getPrice() * nbrOfDays));
            }
        }
        System.out.println("\u001b[33m"+"\nThe booking updated\n"+"\u001b[0m");

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

        HotelManagementApplication_Group2.Room room = new HotelManagementApplication_Group2.Room(roomNumber, typeOfBed, hasBalcony, false, price);
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
        HotelManagementApplication_Group2.ReadAndWrite readAndWrite = new HotelManagementApplication_Group2.ReadAndWrite();
        readAndWrite.saveUsersCustomer(customers);
        readAndWrite.saveUsersEmployee(employees);
        readAndWrite.saveBookings(bookings);
        readAndWrite.saveRooms(rooms);
    }

    public void load() {
        HotelManagementApplication_Group2.ReadAndWrite readAndWrite = new HotelManagementApplication_Group2.ReadAndWrite();
        customers = readAndWrite.readCustomers();
        employees = readAndWrite.readEmployees();
        bookings = readAndWrite.readBookings();
        rooms = readAndWrite.readRooms();

    }

    public void testInformation() {
        HotelManagementApplication_Group2.Employee employee1 = new HotelManagementApplication_Group2.Employee("1234", "1234", "Muhannad ", "0768837489", 1);
        employees.add(employee1);
        HotelManagementApplication_Group2.Employee employee2 = new HotelManagementApplication_Group2.Employee("4321", "4321", "Wills", "0768837489", 2);
        employees.add(employee2);


        HotelManagementApplication_Group2.Customer customer1 = new HotelManagementApplication_Group2.Customer("121212-1212", "Johan", "Kristianstad",
                "0722880025", "johan@gmail.se", 111, "johann");
        customers.add(customer1);

        HotelManagementApplication_Group2.Customer customer2 = new HotelManagementApplication_Group2.Customer("999999-9999", "Adam", "Kristianstad",
                "0788226699", "adam@gmail.se", 111, "johan");
        customers.add(customer2);

        save();
    }

    public void populateRoomArrayList() {                   // Create 24 rooms
        HotelManagementApplication_Group2.Room store_room = new HotelManagementApplication_Group2.Room(0, "Store room", false, true, 0);       // unused room but need for easy printing the ArryList.
        rooms.add(store_room);


        for (int i = 1; i <= 6; i++) {
            HotelManagementApplication_Group2.Room room = new HotelManagementApplication_Group2.Room(i, "Single bed", false, false, 300);
            rooms.add(room);

        }
        for (int i = 7; i <= 12; i++) {
            HotelManagementApplication_Group2.Room room = new HotelManagementApplication_Group2.Room(i, "Single bed", true, false, 400);
            rooms.add(room);

        }
        for (int i = 13; i <= 18; i++) {
            HotelManagementApplication_Group2.Room room = new HotelManagementApplication_Group2.Room(i, "Double bed", false, false, 450);
            rooms.add(room);

        }
        for (int i = 19; i <= 24; i++) {
            HotelManagementApplication_Group2.Room room = new HotelManagementApplication_Group2.Room(i, "Double bed", true, false, 550);
            rooms.add(room);
        }
        save();
    }                                                                                     // Create 24 rooms and save them in the ArrayList room.

}

