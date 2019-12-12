package HotelManagementApplication_Group2;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HotelLogic {

    Customer customer;
    String ssn;
    int checkInDate = 0;
    int userInput = 0;
    int checkOutDate = 0;
    String fileContent = null;
    int numberOfNight = 0;


    File file;
    FileWriter fileWriter;                                               // save all the information in text file, the project database.

    Random random = new Random();
    Scanner input = new Scanner(System.in);
    ArrayList<Room> roomArrayList = new ArrayList<>();
    ArrayList<Customer> customerArrayList = new ArrayList<>();
    ArrayList<Booking> bookingArrayList = new ArrayList<>();

    public HotelLogic(ArrayList<Room> roomArrayList, ArrayList<Customer> customerArrayList, ArrayList<Booking> bookingArrayList) throws IOException {
        this.roomArrayList = roomArrayList;
        this.customerArrayList = customerArrayList;
        this.bookingArrayList = bookingArrayList;
    }

    public HotelLogic() throws IOException {
        populateRoomArrayList();
    }


    private void populateRoomArrayList() {                   // Create 24 rooms
        Room store_room = new Room(0, "Store room", false, true, 0);       // unused room but need for easy printing the arryList.
        roomArrayList.add(store_room);

        for (int i = 1; i <= 6; i++) {
            Room room = new Room(i, "Single bed", false, false, 300);
            roomArrayList.add(room);
        }
        for (int i = 7; i <= 12; i++) {
            Room room = new Room(i, "Single bed", true, false, 400);
            roomArrayList.add(room);
        }
        for (int i = 13; i <= 18; i++) {
            Room room = new Room(i, "Double bed", false, false, 450);
            roomArrayList.add(room);
        }
        for (int i = 19; i <= 24; i++) {
            Room room = new Room(i, "Double bed", true, false, 550);
            roomArrayList.add(room);
        }
    }                                                                                     // Create 24 rooms and save them in the ArrayList room.

    public void addNewBooking() {

        System.out.println("Rooms description : ");
        System.out.println("----------------------------------------------------");
        System.out.println("Single bed rooms               : (1  - 6 ) ");
        System.out.println("Single bed rooms & balcony     : (7  - 12)  ");
        System.out.println("Single bed and balcony         : (13 - 18) ");
        System.out.println("Double beds & balcony          : (19 - 24) ");
        System.out.println("----------------------------------------------------");

        for (int i = 1; i < roomArrayList.size(); i++) {
            System.out.println("[" + (i) + "]" + roomArrayList.get(i));
        }


        try {
            System.out.print("which room would you like to book:  ");                                                     // Check if the user input is not integer so will fix it.
            String userInputString = input.nextLine();
            userInput = Integer.parseInt(userInputString);
        } catch (NumberFormatException e) {
            System.out.println("! Invalid room number , enter a room number from the list");
            return;
        }

        if ((userInput < roomArrayList.size() && userInput >= 1)) {

            if (!roomArrayList.get(userInput).isBooked()) {

//                Customer customer = addCustomer();
                addCustomerAfterCheckIfTheCustomerExist();

                boolean done = false;
                while (!done) {
                    try {
                        System.out.printf("Check in date  : ");
                        String checkINString = input.nextLine();
                        checkInDate = Integer.parseInt(checkINString);
                        done = true;

                    } catch (NumberFormatException e) {

                        input.nextLine();
                        System.out.println("Invalid date (DD), Try again !");


                    }
                }
                boolean done1 = false;
                while (!done1) {
                    try {
                        System.out.printf("Check out date  : ");
                        String checkOutString = input.nextLine();
                        int checkOutDate = Integer.parseInt(checkOutString);
                        done1 = true;

                    } catch (NumberFormatException e) {
                        input.nextLine();
                        System.out.println("Invalid date (DD), Try again !");

                    }
                }

                boolean done2 = false;
                while (!done2) {
                    try {
                        System.out.println("How many nigh? :");
                        String numberOfNightString = input.nextLine();
                        int numberOfNight = Integer.parseInt(numberOfNightString);
                        done2 = true;

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number , Try again !");

                    }
                }
                int bookingNumber = random.nextInt(1000);                                                           // generate a random booking number and then save it in the text file.
                int customerPassword = random.nextInt(9999);

                System.out.println("-- -- Reservation confirmation -- --");
                System.out.println("Booking number " + bookingNumber + ",check in date: " + checkInDate + ",check out date: " + checkOutDate + ",total price" + totalPrice());

                System.out.println("Confirm the booking (YES or No)");
                String confirm = input.nextLine();
                confirm = confirm.toUpperCase();
                if (confirm.equals("YES")) {
                    //print the booking information to confirm all information and total price.

                    roomArrayList.get(userInput).setBooked(true);
                    Booking booking = new Booking(bookingNumber, checkInDate, checkOutDate, totalPrice());
                    bookingArrayList.add(booking);

                    System.out.println("Thanks for your booking");

                    try {
                        new FileWriter("CustomerList.txt", true);
                        new File("CustomersList.txt");
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        fileContent = ("Customer name: " + customer.getName() + ", SSN:" + customer.getSsn() + ", Address: " + customer.getAddress()
                                + ", Telephone: " + customer.getCustomerTelephoneNumber() + ", E-mail" + customer.getEmail() + "Booking Number" + bookingNumber + "\n");
                        writer.write(fileContent);
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                } else if (confirm.equals("NO")) {
                    System.out.println("The reservation has not been confirmed, thank you!");
                }

            } else if (roomArrayList.get(userInput).isBooked()) {
                System.out.println("----The room already booked!----");
                System.out.println("------Choose another room-------");

            }
        } else {                                                                                                        // if the user input invalid , room number in this case will be between 1 and 24 / we have only now 24 rooms.
            System.out.println("! Invalid input, enter a room number");
        }
    }

    public double totalPrice() {
        numberOfNight = checkOutDate - checkInDate;
        double totalPrise = numberOfNight * (roomArrayList.get(userInput).getPrice());
        return totalPrise;
    }

    public void viwBooking() {
        System.out.println("All booking in the hotel");
        for (int i = 0; i < bookingArrayList.size(); i++) {
            System.out.println("[" + i + "]" + bookingArrayList.get(i));
        }
    }

    public Customer addCustomer() {
        System.out.println("Customer's name: ");
        String customerName = input.nextLine();
        System.out.println("Customer's address: ");
        String customerAddress = input.nextLine();
        System.out.println("TelephoneNumber: ");
        String customerTelephoneNumber = input.nextLine();
        System.out.println("E-mail: ");
        String customerEmail = input.nextLine();

        Customer customer = new Customer(ssn, customerName, customerAddress, customerTelephoneNumber, customerEmail);       // Create new customer object and save it in the customer ArrayList
        customerArrayList.add(customer);
        return customer;
    }

    public void viewCustomer() {
        System.out.println("---- All customer in the hotel ----");
        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println("Customer : " + customerArrayList.get(i));
        }
    }

    public void cancelBooking() {
        System.out.printf("Customers name : ");
        String name = input.nextLine();
        System.out.printf("Booking ID     : ");
        int bookingID = input.nextInt();
        input.nextLine();
        for (int i = 0; i < bookingArrayList.size(); i++) {
            if (bookingArrayList.get(i).getBookingId() == bookingID && customerArrayList.get(i).getName().equalsIgnoreCase(name)) {
                customerArrayList.remove(i);
                roomArrayList.get(i).setBooked(false);
                bookingArrayList.remove(i);
                System.out.println("Done ! ");
            }
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
                for (int i = 0; i < customerArrayList.size(); i++) {
                    if (customerArrayList.get(i).getName().equalsIgnoreCase(name) && bookingArrayList.get(i).getBookingId() == bookingID) {
                        System.out.println("++++++++++++++++++++++++++");
                        System.out.println("Customer's info : ");
                        System.out.println("Name     : " + customerArrayList.get(i).getName());
                        System.out.println("SSN      : " + customerArrayList.get(i).getSsn());
                        System.out.println("Address  : " + customerArrayList.get(i).getAddress());
                        System.out.println("++++++++++++++++++++++++++");
                        System.out.println("Customer's booking info : ");
                        System.out.println("Booking ID      : " + bookingArrayList.get(i).getBookingId());
                        System.out.println("Check in date   : " + bookingArrayList.get(i).getCheckInDate());
                        System.out.println("Check out date  : " + bookingArrayList.get(i).getCheckOutDate());
                        System.out.println("Room number     : " + roomArrayList.get(i).getRoomNumber());
                        System.out.println("Price per night : " + roomArrayList.get(i).getPrice() + " SEK");
                        System.out.println("Total price     : " + bookingArrayList.get(i).getTotalPrice() + " SEK");
                        System.out.println("++++++++++++++++++++++++++");
                    }
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid booking ID, Try again !");
            }
        }

    }

    public void editRoomInformation() {
        int roomNumber;

        for (int i = 1; i < roomArrayList.size(); i++) {
            System.out.println("[" + (i) + "]" + roomArrayList.get(i));
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
            roomArrayList.get(roomNumber).setHasBalcony(true);
        } else if (yesOrNo.equals("no")) {
            roomArrayList.get(roomNumber).setHasBalcony(false);
        }

        System.out.println("Which type of bed has the room, (single bed or double bed?)");
        String typeOfBed = input.nextLine();
        typeOfBed = typeOfBed.substring(0, 1).toUpperCase() + typeOfBed.substring(1).toLowerCase();
        roomArrayList.get(roomNumber).setTypeOfBed(typeOfBed);

        System.out.println("Price per night for this room:");
        double pricePerNight = input.nextDouble();
        roomArrayList.get(roomNumber).setPrice(pricePerNight);
    }

    public void viewAllRoom() {
        System.out.println("-----All room at the hotel-----");
        for (int i = 1; i < roomArrayList.size(); i++) {

            String isBookedStatus =  roomArrayList.get(i).isHasBalcony() ? " With balcony" : " Without balcony ";

            System.out.println( roomArrayList.get(i)+", "+roomArrayList.get(i).getTypeOfBed()+", "+isBookedStatus);
        }
    }

    public void availableRooms() {

        System.out.println("-- -- -- -- -- -- -- -- -- ");
        System.out.println("----All available rooms---- ");
        System.out.println("-- -- -- -- -- -- -- -- -- ");


        for (int i = 0; i < roomArrayList.size(); i++) {

            if (!roomArrayList.get(i).getBooked()) {

                System.out.println(roomArrayList.get(i));
            }

        }
    }

    public void viewBookedRoom() {
        System.out.println("-- -- -- -- -- -- -- -- -- ");
        System.out.println("---- All booked room ----");
        System.out.println("-- -- -- -- -- -- -- -- -- ");
        for (int i = 1; i < roomArrayList.size(); i++) {
            if (roomArrayList.get(i).getBooked()) {

                System.out.println(roomArrayList.get(i));
            } else {
                System.out.println();
                System.out.println("There are no booked room");
                break;
            }
        }
    }

    public void editCustomerInformation() {
        int oneOrTwo;
        int customerNumber;                                                                                     // Modify information for customer which have booking in the hotel
        System.out.println("--- ---Change the information of customers currently in the hotel--- ---");
        System.out.println();

        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println("[" + i + "]" + customerArrayList.get(i));
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
            customerArrayList.get(customerNumber).setName(name);
            customerArrayList.get(customerNumber).setSsn(ssn);
            customerArrayList.get(customerNumber).setAddress(address);
            customerArrayList.get(customerNumber).setCustomerTelephoneNumber(customerTele);
            customerArrayList.get(customerNumber).setEmail(customerMail);
        } else if (oneOrTwo == 2) {
            System.out.println("Which information do you want to change");
            System.out.println("Name?, Address ? , Phone?, SSN? or Email?");
            System.out.println("Enter you choice: ");
            String choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("name")) {
                System.out.println("Enter the name");
                String name = input.nextLine();
                customerArrayList.get(customerNumber).setName(name);
                System.out.println("The name changed");
            } else if (choice.equals("ssn")) {
                System.out.println("Enter the SSN:");
                String ssn = input.nextLine();
                customerArrayList.get(customerNumber).setSsn(ssn);
                System.out.println("The SSN  changed");
            } else if (choice.equals("address")) {
                System.out.println("Enter the address: ");
                String address = input.nextLine();
                customerArrayList.get(customerNumber).setAddress(address);
                System.out.println("The address changed");
            } else if (choice.equals("phone")) {
                System.out.println("Enter the telephone number");
                String phone = input.nextLine();
                customerArrayList.get(customerNumber).setCustomerTelephoneNumber(phone);
                System.out.println("The telephone number changed");
            } else if (choice.equals("email")) {
                System.out.println("Enter the email");
                String email = input.nextLine();
                customerArrayList.get(customerNumber).setEmail(email);
                System.out.println("The email changed ");

            } else {
                System.out.println("Enter one of the above options please.");
            }
        }

    }

    public void addCustomerAfterCheckIfTheCustomerExist() {                                     // This method will take ssn as input and check if the ssn exist in the CustomerArrayList
        boolean isEmpty = customerArrayList.isEmpty();
        if (isEmpty) {
            System.out.println("Enter customer's SSN, SSN must to be 12 digit numbers");        // Check first if the arraylsit is empty or not
            System.out.println("Customer SSN: ");
            ssn = input.nextLine();
            addCustomer();
            isEmpty = false;

        } else {
            System.out.println("Enter customer's SSN, SSN must to be 12 digit numbers");
            System.out.print("Customer SSN:  ");
            ssn = input.nextLine();

            for (int i = 0; i < customerArrayList.size(); i++) {
                if (customerArrayList.get(i).getSsn().equals(ssn)) {
                    System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
                    System.out.println("---- !The customer already exist in the system! ----");
                    System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");

                } else {

                    addCustomer();
                }
            }

        }
    }

    public void loginMenu() throws IOException {

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
            System.out.printf("User name : ");
            String userName = input.nextLine();
            System.out.printf("Password  : ");
            String employeePassWord = input.nextLine();
            if (employeePassWord.equals("1234")) {
                Employee employee = new Employee(userName, employeePassWord);
                FileWriter fw = new FileWriter("Employees.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(employee);
                pw.close();
                employeesMenu();
            } else {
                System.out.println("Invalid Password !");
                loginMenu();
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

    public void addNewRoom() {

        boolean hasBalcony = false;
        System.out.println("////You adding new room to the hotel/////");
        System.out.println("Enter the room number do you want to add");
        int roomNumber = Integer.parseInt(input.nextLine());

        System.out.println("Which type of bed the room have? (Single bed or double bed)");
        String typeOfBed = input.nextLine();
        typeOfBed = typeOfBed.substring(0, 1).toUpperCase() + typeOfBed.substring(1).toLowerCase();

        System.out.println("Does the room have a balcony? ");
        System.out.println("yes or no");
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
        roomArrayList.add(room);

        // add room to text file as data base to recall them if we restart the program.
        // it is worked as a database to the room objects.

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("rooms.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            //write a room in the text file
            objectOutputStream.writeObject(room);

            objectOutputStream.close();


            FileInputStream fileInputStream = new FileInputStream(new File("rooms.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Room room1 = (Room) objectInputStream.readObject();
            System.out.println(room.toString());

            objectInputStream.close();
            fileInputStream.close();


            System.out.println("Thank you1");
            System.out.println("You added new room to the hotel");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
//            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void outOfOrder() {
        System.out.println("/// Remove a room from the system ///");
        System.out.println("Which room do you want to remove?");
        for (int i = 1; i < roomArrayList.size(); i++) {
            System.out.println(roomArrayList.get(i));
        }
        System.out.println("Enter room number: ");
        int roomNumber = input.nextInt();
        input.nextLine();
        System.out.printf("Are you sure want to remove the room %s ?", roomNumber + "Yes or No");

        String userAnswer = input.nextLine();
        userAnswer = userAnswer.toLowerCase();
        if (userAnswer.equals("yes")) {

            roomArrayList.get(roomNumber).setRoomNumber(roomNumber);
            roomArrayList.get(roomNumber).setPrice(0);
            roomArrayList.get(roomNumber).setBooked(true);
            roomArrayList.get(roomNumber).setHasBalcony(false);
            roomArrayList.get(roomNumber).setTypeOfBed("Not available room");

            System.out.println("-- -- -- -- -- --");
            System.out.println("The room removed");
            System.out.println("-- -- -- -- -- --");
        } else if (userAnswer.equals("no")) {
            System.out.println("-- -- -- -- -- -- -- -- -- -- -- --");
            System.out.println("The room still exist in the system");
            System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- ");
        } else {
            System.out.println("Invalid input!");
        }


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
        System.out.println("----- Menu of employee ---- ");
        System.out.println("1- Customer management");
        System.out.println("2- Booking management");
        System.out.println("3- Room management");
        System.out.println("Enter your choice:");

    }

    public void roomManagement() {
        System.out.println("1> View available rooms");
        System.out.println("2> View all booked room");
        System.out.println("3> View rooms information");
        System.out.println("4> Edit room information");
        System.out.println("5> Add new room");
        System.out.println("6> A room out of service");
        System.out.println("0> Return to the menu");

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
        System.out.println("1> Add new customer");
        System.out.println("2> List of customers");
        System.out.println("3> Search a customer");
        System.out.println("4> Edit customer's information ");
        System.out.println("0> Return to the menu ");

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
        System.out.println("1> Add booking");
        System.out.println("2> Cancel booking");
        System.out.println("3> View all booking");
        System.out.println("0> Return to the menu ");
        int choice;

        try {
            System.out.print("Enter your choice: ");
            String choiceString = input.nextLine();
            choice = Integer.parseInt(choiceString);
            if (choice == 1) {
                addNewBooking();
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
}

