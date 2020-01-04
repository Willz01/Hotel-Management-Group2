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



    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRoomNbr(int roomNbr) {
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



    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return

                "\u001b[34m"+"Booking number = " +"\u001b[0m" + bookingId +
                        "\u001b[34m"+", checkInDate='" +"\u001b[0m"+ checkInDate + '\''
                       + "\u001b[34m"+", checkOutDate='" + "\u001b[0m" + checkOutDate + '\'' +
                        "\u001b[34m"+", totalPrice=" + "\u001b[0m" + totalPrice;
    }
}
