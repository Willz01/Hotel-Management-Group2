// change variable names!!
public void search() throws IOException {
       System.out.printf("Customers name : ");
       String name = input.nextLine();
       System.out.printf("SSN            : ");
       String ssn = input.nextLine();
       for (int i = 0; i < customerWithBookingArrayList.size(); i++) {
           //customers with booking
           if (customerWithBookingArrayList.get(i).getName().equalsIgnoreCase(name) && customerWithBookingArrayList.get(i).getSSN().equalsIgnoreCase(ssn)) {
               System.out.println("++++++++++++++++++++++++++");
               System.out.println("Customer's info : ");
               System.out.println("Name     : " + customerWithBookingArrayList.get(i).getName());
               System.out.println("SSN      : " + customerWithBookingArrayList.get(i).getSSN());
               System.out.println("Address  : " + customerWithBookingArrayList.get(i).getAddress());
               System.out.println("Password : " + customerWithBookingArrayList.get(i).getPassword());
               System.out.println("++++++++++++++++++++++++++");
               System.out.println("Customer's booking info : ");
               System.out.printf("Booking ID      : %03d%n", bookingArrayList.get(i).getBookingID());
               System.out.println("Check in date   : " + bookingArrayList.get(i).getCheckIN());
               System.out.println("Check out date  : " + bookingArrayList.get(i).getCheckOutDate());
               System.out.println("Room number     : " + bookingArrayList.get(i).getRoomNum());
               System.out.println("Price per night : " + bookingArrayList.get(i).getPricePerNight() + " SEK");
               System.out.println("Total price     : " + bookingArrayList.get(i).getTotalPrice() + " SEK");
               System.out.println("++++++++++++++++++++++++++");
               employeesMenu();
           }
       }
     }
