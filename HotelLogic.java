import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HotelLogic {


    String fileContent = null;
    File file = new File("c:/Users/Muhannad/HotelManagementApplication_Group2/CustomersList.txt");
    FileWriter fileWriter = new FileWriter("CustomerList.txt",true);                                                 // save all the information in text file, the project database.
    //FileReader  fileReader= new FileReader("c:/Users/Muhannad/HotelManagementApplication_Group2/CustomersList.txt");

    Random random = new Random();
    Scanner input = new Scanner(System.in);
    ArrayList<Room> roomArrayList = new ArrayList<>();
    ArrayList<Customer> customerArrayList = new ArrayList<>();

    public HotelLogic() throws IOException {
    }


    public void existingRooms() {                   // Create 24 rooms
        Room store_room = new Room(0, "Store room", false, true, 0);       // unused room but need for easy printing the arryList.
        roomArrayList.add(store_room);

        for (int i = 1; i <= 6; i++) {
            Room room = new Room(i, "Single bed", false, false, 350.00);
            roomArrayList.add(room);
        }
        for (int i = 7; i <= 12; i++) {
            Room room = new Room(i, "Single bed", true, false, 450.00);
            roomArrayList.add(room);
        }
        for (int i = 13; i <= 18; i++) {
            Room room = new Room(i, "Double bed", false, false, 650.00);
            roomArrayList.add(room);
        }
        for (int i = 19; i <= 24; i++) {
            Room room = new Room(i, "Double bed", true, false, 850.00);
            roomArrayList.add(room);
        }
    }                                                                                     // Create 24 rooms and save them in the ArrayList room.

    public void addNewBooking() {



        while (true) {
            System.out.println("--Rooms at the hotel--");
            System.out.println("Room numbers (1-6) Single bed without balcony ");
            System.out.println("Room numbers (7-12) single bed with balcony");
            System.out.println("Room numbers (13-18) Double bed without balcony");
            System.out.println("Room numbers (19-24) Double bed with balcony");
            for (int i = 1; i < roomArrayList.size(); i++) {
                System.out.println("[" + (i) + "]" + roomArrayList.get(i));
            }

            try {
                System.out.print("which room would you like to book:  ");                                                        // choosing a room number to book it.
                int userInput = input.nextInt();
                input.nextLine();

                if ((userInput < roomArrayList.size() && userInput >= 1)) {                                                     // handle invalid room number input.
                    while (true) {
                        if (roomArrayList.get(userInput).isBooked() == false) {                                                 // check if the room already  booked or not.
                            System.out.println("Customer's name: ");
                            String customerName = input.nextLine();
                            System.out.println("SSN: ");
                            String ssn = input.nextLine();
                            System.out.println("Customer's address: ");
                            String customerAddress = input.nextLine();
                            System.out.println("TelephoneNumber: ");
                            String telephoneNumber = input.nextLine();
                            System.out.println("E-mail: ");
                            String email = input.nextLine();

                            Customer customer = new Customer(ssn, customerName, customerAddress, telephoneNumber, email);       // Create new customer object and save it in the customer ArrayList
                            customerArrayList.add(customer);
                            roomArrayList.get(userInput).setBooked(true);
                            System.out.println("Thanks for your booking");


                            int bookingNumber = random.nextInt(1000);                                                    // generate a random booking number and then save it in the text file.
                            int customerPassword = random.nextInt(9999);                                                 // generate a random password and will save it in a new text file which the employ while print it and give it to the customer

                            try {                                                                                               // save all the information in text file, the project database.
                                BufferedWriter writer = new BufferedWriter(fileWriter);
                                fileContent = ("Customer name: " + customerName + ", SSN:" + ssn + ", Address: " + customerAddress
                                        + ", Telephone: " + telephoneNumber + ", E-mail" + email + "Booking Number" + bookingNumber + "\n");
                                writer.write(fileContent);
                                writer.flush();
                                //fileReader.read();


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        } else if (roomArrayList.get(userInput).isBooked()) {
                            System.out.println("----The room already booked!----");
                            System.out.println("------Choose another room-------");
                            break;
                        }
                    }

                } else {                                                                                                        // if the user input invalid , room number in this case will be between 1 and 24 / we have only now 24 rooms.
                    System.out.println("! Invalid input, enter a room number");

                }

            } catch (InputMismatchException e) {
                System.out.println("! Invalid room number , enter a room number from the list");

            }
        }
    }
}






