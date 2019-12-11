package HotelManagementApplication_Group2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    File file = new File("c:/Users/Muhannad/HotelManagementApplication_Group2/CustomersList.txt");
    FileWriter fileWriter = new FileWriter("CustomerList.txt", true);                                                 // save all the information in text file, the project database.


    Random random = new Random();
    Scanner input = new Scanner(System.in);
    ArrayList<Room> roomArrayList = new ArrayList<>();
    ArrayList<Customer> customerArrayList = new ArrayList<>();
    ArrayList<Booking> bookingArrayList = new ArrayList<>();

    public HotelLogic() throws IOException {
    }


    public void existingRooms() {                   // Create 24 rooms
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

    public void addBooking() {

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

                roomArrayList.get(userInput).setBooked(true);
                Booking booking = new Booking(bookingNumber, checkInDate, checkOutDate, totalPrice());
                bookingArrayList.add(booking);
                System.out.println("Thanks for your booking");
                try {

                    BufferedWriter writer = new BufferedWriter(fileWriter);
                    fileContent = ("Customer name: " + customer.getName() + ", SSN:" + customer.getSsn() + ", Address: " + customer.getAddress()
                            + ", Telephone: " + customer.getCustomerTelephoneNumber() + ", E-mail" + customer.getEmail() + "Booking Number" + bookingNumber + "\n");
                    writer.write(fileContent);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();

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
        System.out.println("SSN: ");
        String ssn = input.nextLine();
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
            System.out.println("Room number " + "[" + i + "]" + roomArrayList.get(i));
        }
    }

    public void availableRooms() {
        System.out.println("-- -- -- -- -- -- -- -- -- ");
        System.out.println("----All available rooms---- ");
        System.out.println("-- -- -- -- -- -- -- -- -- ");
        for (int i = 1; i < roomArrayList.size(); i++) {
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
            }
        }
    }

    public void editCustomerInformation() {
        int oneOrTwo;
        int customerNumber;                                                                                     // Modify information for customer which have booking in the hotel
        viewCustomer();
        System.out.println("--- ---Change the information of customers currently in the hotel--- ---");
        System.out.println();

        try {
            System.out.println("Which customer information do you want to chane?");
            String customerNumberString = input.nextLine();
            customerNumber = Integer.parseInt(customerNumberString);
        } catch (NumberFormatException e) {
            System.out.println("! Invalid input , enter which customer do you want to edit");
            return;
        }

        try {
            System.out.println("1-Change all the information)");
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
            choice.toLowerCase();

            if (choice.equals("name")) {
                System.out.println("Enter the name");
                String name = input.nextLine();
                customerArrayList.get(customerNumber).setName(name);
            } else if (choice.equals("ssn")) {
                System.out.println("Enter the SSN:");
                String ssn = input.nextLine();
                customerArrayList.get(customerNumber).setSsn(ssn);

            } else if (choice.equals("address")) {
                System.out.println("Enter the address: ");
                String address = input.nextLine();
                customerArrayList.get(customerNumber).setAddress(address);
            } else if (choice.equals("phone")) {
                System.out.println("Enter the telephone number");
                String phone = input.nextLine();
                customerArrayList.get(customerNumber).setCustomerTelephoneNumber(phone);
            } else if (choice.equals("email")) {
                System.out.println("Enter the email");
                String email = input.nextLine();
                customerArrayList.get(customerNumber).setEmail(email);

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
}

