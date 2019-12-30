package HotelManagementApplication_Group2;

import java.io.*;
import java.util.LinkedList;

public class ReadAndWrite {

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
