public class Booking {
    private int bookingId;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;

    public int getBookingId() {
        return bookingId;
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

    public Booking(int bookingId, String checkInDate, String checkOutDate, double totalPrice) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }
}
