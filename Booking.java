package com.company.Model;

public class Booking {
    private int bookingID;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;

    public Booking(int bookingID, String checkInDate, String checkOutDate, double totalPrice) {
        this.bookingID = bookingID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking ID      : " + bookingID + System.lineSeparator() +
                "Check IN date   : " + checkInDate + System.lineSeparator() +
                "Check OUT date  : " + checkOutDate + System.lineSeparator() +
                "Total price     : " + totalPrice+ '\n' + "-----------------";

    }
}
