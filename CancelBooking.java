
    public void cancelBooking() {
        System.out.printf("Customers name : ");
        String name = input.nextLine();
        System.out.printf("Booking ID     : ");
        int bookingID = input.nextInt();
        input.nextLine();
        for (int i = 0; i < bookingArrayList.size(); i++) {
            if (bookingArrayList.get(i).getBookingID() == bookingID && customerArrayList.get(i).getName().equalsIgnoreCase(name)) {
                rooms.set(bookingArrayList.get(i).getRoomNum(),0);
                bookingArrayList.remove(i);
            }
        }
        /**
         * removing a booking from a customer                                                       (done)
         * should'nt affect other customers booking                                                 (done)
         * should provide max security/privacy to other customers booking                           (done)
         * should display customer's booking in the case of customer having several bookings        (reviewing)
         * removing booking can act on the index of the booking (displayed)                         (better plan implemented, still also possible review xD)
         */
    }
