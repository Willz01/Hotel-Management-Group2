package HotelManagementApplication_Group2;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {
    private int bookingId;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private int roomNbr;

    public Booking(int bookingId, Date checkInDate, Date checkOutDate, double totalPrice,int roomNbr) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.roomNbr = roomNbr;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getRoomNbr() {
        return roomNbr;
    }

    @Override
    public String toString() {
        return
                "Room number"+
                "Booking number =" + bookingId +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", totalPrice=" + totalPrice;
    }
}
