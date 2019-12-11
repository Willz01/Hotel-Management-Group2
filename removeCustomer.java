public void removeCustomer() throws IOException {
        System.out.printf("Customers name : ");
        String name = input.nextLine();
        System.out.printf("SSN            : ");
        String ssn = input.nextLine();
        for (int i = 0; i < customerWithBookingArrayList.size(); i++) {
            if (customerWithBookingArrayList.get(i).getName().equalsIgnoreCase(name) && customerWithBookingArrayList.get(i).getSSN().equalsIgnoreCase(ssn)) {
                customerWithBookingArrayList.remove(i);
                rooms.set(bookingArrayList.get(i).getRoomNum(), null);
                bookingArrayList.remove(i);
                System.out.println("Done ! ");
                employeesMenu();
            }
        }
