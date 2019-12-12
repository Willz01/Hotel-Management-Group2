// all choices lead to a method call!!
public void loginMenu() throws IOException {
        System.out.println("====Login menu===");
        System.out.println("1> Customer     |");
        System.out.println("2> Employee     |");
        System.out.println("3> Exit         |");
        System.out.println("=================");
        boolean done = false;
        int choice = 0;
        while (!done) {
            try {
                System.out.printf(">>>> ");
                choice = input.nextInt();
                input.nextLine();
                done = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid input, Try again !");
            }
        }
        if (choice == 1) {
            customersLogin();
        } else if (choice == 2) {
            System.out.printf("User name : ");
            String userName = input.nextLine();
            System.out.printf("Password  : ");
            String employeePassWord = input.nextLine();
            if (employeePassWord.equals("1234")) {
                employee employee = new employee(userName, employeePassWord);
                FileWriter fw = new FileWriter("Employees.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(employee);
                pw.close();
                employeesMenu();
            } else {
                System.out.println("Invalid Password !");
                loginMenu();
            }

        } else if (choice == 3) {
            System.out.println("Thanks for now !! ");
            System.exit(0);
        } else {
            System.out.println("Invalid option! ");
            loginMenu();
        }
    }
