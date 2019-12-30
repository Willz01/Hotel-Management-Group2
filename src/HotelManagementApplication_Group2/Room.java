package HotelManagementApplication_Group2;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private String typeOfBed;
    private boolean hasBalcony;
    private boolean booked;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getTypeOfBed() {
        return typeOfBed;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public boolean getBooked() {
        return booked;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setTypeOfBed(String typeOfBed) {
        this.typeOfBed = typeOfBed;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public Room(int roomNumber, String typeOfBed, boolean hasBalcony, boolean booked, double price) {
        this.roomNumber = roomNumber;
        this.typeOfBed = typeOfBed;
        this.hasBalcony = hasBalcony;
        this.booked = booked;
        this.price = price;
    }


    @Override
    public String toString() {
        String isBookedStatus = booked == true ? " Booked" : " Available ";
        return   "\u001b[34m"+"|"+"\u001b[0m"
                + roomNumber +"\t\t\t\t" + isBookedStatus + "\t\t\t" + price+"\t" +
                "\u001b[34m"+  "|" + "\u001b[0m";
    }
}
