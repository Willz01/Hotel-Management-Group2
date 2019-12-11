package HotelManagementApplication_Group2;

public class Booking {
    private int bookingId;
    private int checkInDate;
    private int checkOutDate;
    private double totalPrice;

    public int getBookingId() {
        return bookingId;
    }

    public int getCheckInDate() {
        return checkInDate;
    }

    public int getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Booking(int bookingId, int checkInDate, int checkOutDate, double totalPrice) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return
                "Booking number =" + bookingId +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", totalPrice=" + totalPrice;
    }
}
