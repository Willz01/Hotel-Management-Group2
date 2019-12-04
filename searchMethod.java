public void search() {
    System.out.printf("Customers name : ");
    String name = input.nextLine();
    int bookingID = 0;
    boolean done = false;
    while (!done) {
        try {
            System.out.printf("Booking ID     : ");
            bookingID = input.nextInt();
            input.nextLine();
            done = true;
            for (int i = 0; i < customerArrayList.size(); i++) {
                if (customerArrayList.get(i).getName().equalsIgnoreCase(name) && bookingArrayList.get(i).getBookingID() == bookingID) {
                    System.out.println("++++++++++++++++++++++++++");
                    System.out.println("Customer's info : ");
                    System.out.println("Name     : " + customerArrayList.get(i).getName());
                    System.out.println("SSN      : " + customerArrayList.get(i).getSSN());
                    System.out.println("Address  : " + customerArrayList.get(i).getAddress());
                    System.out.println("Password : " + customerArrayList.get(i).getPassword());
                    System.out.println("++++++++++++++++++++++++++");
                    System.out.println("Customer's booking info : ");
                    System.out.println("Booking ID      : " + bookingArrayList.get(i).getBookingID());
                    System.out.println("Check in date   : " + bookingArrayList.get(i).getCheckIN());
                    System.out.println("Check out date  : " + bookingArrayList.get(i).getCheckOutDate());
                    System.out.println("Room number     : " + bookingArrayList.get(i).getRoomNum());
                    System.out.println("Price per night : " + bookingArrayList.get(i).getPricePerNight() + " SEK");
                    System.out.println("Total price     : " + bookingArrayList.get(i).getTotalPrice() + " SEK");
                    System.out.println("++++++++++++++++++++++++++");
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Invalid booking ID, Try again !");
        }
    }

}
