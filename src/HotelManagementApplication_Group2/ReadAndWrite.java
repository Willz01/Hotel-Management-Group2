package HotelManagementApplication_Group2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ReadAndWrite {
    public void write(int bookId, Date checkinDate, Date checkoutDate, int roomNbr, boolean update) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Chooses file
            PrintWriter pw = new PrintWriter(new FileWriter("Booking_LOGG.txt", true));
            //Print information to text file
            pw.println("---------------------------------------------------------------------------");
            if(update){
                pw.println("\t***** Updated By Customer *****");
            }
            pw.println("|Booking Id: " + bookId + "|Check In: " + dateFormat.format(checkinDate) + "|Check Out: " + dateFormat.format(checkoutDate) + "|Room Number: " + roomNbr + "|");
            pw.println("---------------------------------------------------------------------------");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBooking(Booking booking, Customer user){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "BookingId-" + (booking.getBookingId() + "-" + String.valueOf(user.getName())) + ".txt";

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filename, true));

            pw.println("----------------------------------------------------------------------");
            pw.println("*** *** *** Your Booking Information from Hotel *** *** ***");
            pw.println("----------------------------------------------------------------------");
            pw.println("\n Booking ID: " + booking.getBookingId());
            pw.println(" Check in Date: " + dateFormat.format(booking.getCheckInDate()));
            pw.println(" Check Out Date: " + dateFormat.format(booking.getCheckOutDate()));
            pw.println(" Room Number: " + booking.getRoomNbr());
            pw.println(" Price in Total:  " + booking.getTotalPrice() + "\n");
            pw.println("   --- --- --- --- --- Your Personal Information --- --- --- --- ---");
            pw.println(" Mr/Mrs: " + user.getName());
            pw.println(" Address: " + user.getAddress());
            pw.println(" TFN: " + user.getCustomerTelephoneNumber());
            pw.println(" Personal ID: " + user.getSsn());
            pw.println("\n --------------------------------------------------------------------");
            pw.println(" We are very glad that you have chosen our Hotel ");
            pw.println(" Thank for you!");
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList read(File file) {
        if (file.exists()) {
            try {
              //  file.createNewFile();
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                LinkedList list = (LinkedList) objectInputStream.readObject();

                return list;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void writeList(LinkedList list, File file) {

        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUsersCustomer(LinkedList<Customer> customers) {
        File file = new File("customers.txt");
        writeList(customers, file);
    }

    public void saveUsersEmployee(LinkedList<Employee> employees) {
        File file = new File("employees.txt");
        writeList(employees, file);
    }

    public void saveRooms(LinkedList<Room> rooms) {
        File file = new File("rooms.txt");
        writeList(rooms, file);
    }

    public void saveBookings(LinkedList<Booking> bookings) {
        File file = new File("bookings.txt");
        writeList(bookings, file);
    }

    public LinkedList readCustomers() {
        File file = new File("customers.txt");
        LinkedList list = read(file);

        return list;
    }

    public LinkedList readEmployees (){
        File file = new File ("employees.txt");
        LinkedList list = read(file);
        return list;
    }

    public LinkedList readRooms() {
        File file = new File("rooms.txt");
        LinkedList list = read(file);

        return list;
    }

    public LinkedList readBookings() {
        File file = new File("bookings.txt");
        LinkedList list = read(file);

        return list;
    }

}
