// you have ti change the variables names ti watever name you are using
public void editCustomerInformation() throws IOException {
        System.out.printf("Customer's name : ");
        String nameEdit = input.nextLine();
        for (int i = 0; i < customerWithBookingArrayList.size(); i++) {
            if (customerWithBookingArrayList.get(i).getName().equalsIgnoreCase(nameEdit)) {
                System.out.println("Enter index of info. to change ");
                System.out.println("-------------");
                System.out.println("1> Name ");
                System.out.println("2> SSN");
                System.out.println("3> Address");
                System.out.println("4> Password");
                System.out.println("-------------");
                System.out.printf(">>> ");
                int choice1 = input.nextInt();
                input.nextLine();
                if (choice1 == 1) {
                    System.out.printf("Enter new name : ");
                    String nameNew = input.nextLine();
                    for (int j = 0; i < customerWithBookingArrayList.size(); j++) {
                        if (customerWithBookingArrayList.get(j).getName().equalsIgnoreCase(nameEdit)) {
                            customerWithBookingArrayList.get(j).setName(nameNew);
                            System.out.println("Done! ");
                            employeesMenu();
                        }
                    }
                } else if (choice1 == 2) {
                    System.out.printf("Enter new SSN  : ");
                    String ssnNew = input.nextLine();
                    for (int j = 0; j < customerWithBookingArrayList.size(); j++) {
                        if (customerWithBookingArrayList.get(j).getName().equalsIgnoreCase(nameEdit)) {
                            customerWithBookingArrayList.get(j).setSSN(ssnNew);
                            System.out.println("Done! ");
                            employeesMenu();
                        }
                    }
                } else if (choice1 == 3) {
                    System.out.printf("Enter new address : ");
                    String addNew = input.nextLine();
                    for (int j = 0; j < customerWithBookingArrayList.size(); j++) {
                        if (customerWithBookingArrayList.get(j).getName().equalsIgnoreCase(nameEdit)) {
                            customerWithBookingArrayList.get(j).setAddress(addNew);
                            System.out.println("Done! ");
                            employeesMenu();
                        }
                    }
                } else if (choice1 == 4) {
                    System.out.printf("Enter new password : ");
                    String passNew = input.nextLine();
                    for (int j = 0; j < customerWithBookingArrayList.size(); j++) {
                        if (customerWithBookingArrayList.get(j).getName().equalsIgnoreCase(nameEdit)) {
                            customerWithBookingArrayList.get(j).setPassword(passNew);
                            System.out.println("Done! ");
                            employeesMenu();
                        }
                    }
                }
            }
        }
