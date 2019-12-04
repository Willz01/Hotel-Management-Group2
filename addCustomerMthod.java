public void addCustomer() throws IOException {
        System.out.printf("Name     : ");
        String name = input.nextLine();
        System.out.printf("SSN      : ");
        String ssn = input.nextLine();
        System.out.printf("Address  : ");
        address = input.nextLine();
        System.out.printf("Password : ");
        password = input.nextLine();
        bookingID = 0;
        boolean done = false;
        while (!done) {
            try {
                System.out.printf("Booking ID     : ");
                bookingID = input.nextInt();
                input.nextLine();
                done = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid booking ID, Try again !");
            }
        }
        int checkIN = 0;
        boolean done1 = false;
        while (!done1) {
            try {
                System.out.printf("Check in date  : ");
                checkIN = input.nextInt();
                input.nextLine();
                done1 = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid date (DD), Try again !");
            }
        }

        int checkOut = 0;
        boolean done2 = false;
        while (!done2) {
            try {
                System.out.printf("Check out date : ");
                checkOut = input.nextInt();
                input.nextLine();
                done2 = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid date (DD), Try again !");
            }
        }
        System.out.println("Rooms description : ");
        System.out.println("----------------------------------------------------");
        System.out.println("Single bed rooms       : 0  - 6  (Price : 300 SEK) |");
        System.out.println("Double bed rooms       : 7  - 12 (Price : 400 SEK) |");
        System.out.println("Single bed and balcony : 13 - 16 (Price : 450 SEK) |");
        System.out.println("Double beds & balcony  : 17 - 24 (Price : 550 SEK) |");
        System.out.println("----------------------------------------------------");

        for (int j = 0; j < rooms.length; j++) {
            System.out.printf(" %s[%2d] : %s%n", "room", j, (rooms[j] == 0) ? "not booked" : "booked");
        }

        boolean done4 = false;
           int roomNum1 = 0;
        while (!done4) {
            try {
                System.out.printf("room number          : ");
                roomNum1 = input.nextInt();
                input.nextLine();
                done4 = true;
                if (rooms[roomNum1] == 0) {
                    rooms[roomNum1] = bookingID;
                } else {
                    System.out.println("Room has already been booked!! ");
                    done4 = false;
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid room number ! ");
            }
        }
        int pricePerNight = 0;
        if (roomNum1 >= 0 && roomNum1 <= 6){
            pricePerNight = 300;
        }else if (roomNum1 >= 7 && roomNum1 <= 12){
            pricePerNight = 400;
        }else if (roomNum1 >= 13 && roomNum1 <= 16){
            pricePerNight = 450;
        }else if (roomNum1 >= 17 && roomNum1 <= 24){
            pricePerNight = 550;
        }

        System.out.println("Done !");
        int totalPrice = pricePerNight * (checkOut - checkIN);
        booking booking = new booking(bookingID, checkIN, checkOut, roomNum1, pricePerNight, totalPrice);
        bookingArrayList.add(booking);
        customer customer = new customer(ssn, name, address, password, bookingArrayList);
        customerArrayList.add(customer);

        try {
            FileWriter fw = new FileWriter("Customer.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(customer);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
