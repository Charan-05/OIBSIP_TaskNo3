import java.util.Scanner;

class Task_ATMInterface {
    public static void main(String[] args) {
        System.out.println("\n WELCOME TO Oasis Infobyte ATM \n");
        System.out.println("\n Please register to continue to Oasis Infobyte ATM \n");
        System.out.println("Enter 1 to Register yourself in our bank");
        System.out.print("Enter Your Choice : ");
        int choice = Input(2);
        if (choice == 1) {
            InterfaceOfAtm user = new InterfaceOfAtm();
            user.register();
            while (true) {
                System.out.println("\n1.Login\n2.Exit");
                System.out.print("Enter your choice :");
                int ch = Input(2);
                if (ch == 1) {
                    if (user.login()) {
                        System.out.println("\n\n Welcome " + user.name + "\n\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println(
                                    "\n1.Deposit\n2.Withdraw\n3.Transfer\n4.Transaction History\n5.Check Balance\n6.Exit to login page");
                            System.out.print("Enter your choice: ");
                            int c = Input(7);
                            switch (c) {
                                case 1:
                                    user.deposit();
                                    break;
                                case 2:
                                    user.withdraw();
                                    break;
                                case 3:
                                    user.transfer();
                                    break;
                                case 4:
                                    user.transHistory();
                                    break;
                                case 5:
                                    user.checkBalance();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                                default:
                                    System.out.println("\nInput Invalid");
                            }
                        }
                    }

                } else
                    System.exit(0);
            }
        } else
            System.exit(0);
    }

    public static int Input(int lmt) {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (flag && input > lmt || input < 1) {
                    System.out.println("Choose a number from given options: " + lmt);
                    flag = false;
                } else if (input == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter integer value only");
                flag = false;
            }
        }
        ;
        return input;
    }
}

class InterfaceOfAtm {
    String name;
    String userName;
    String password;
    String accountNo;
    int transactions = 0;
    String transactionHistory = " ";
    double balance = 7000000.00;

    // User Registration

    public void register() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Name (as per your Aadhaar Card): ");
        this.name = sc.nextLine();
        System.out.println("\nEnter Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter the Password : ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistered Successfully.");
    }

    // User Login
    public boolean login() {
        boolean Login = false;
        Scanner sc = new Scanner(System.in);
        while (!Login) {
            System.out.print("\nPlease enter your Username: ");
            String username = sc.nextLine();
            if (username.equals(userName)) {
                while (!Login) {
                    System.out.print("\nEnter Your Password : ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.println("\nLogin successful!");
                        Login = true;
                    } else
                        System.out.println("\nSorry! Your password is incorrect.");
                }
            } else
                System.out.println("\nUsername not found! Please enter a valid one.");
        }
        return Login;
    }

    // withdraw money
    public void withdraw() {

        System.out.println("\nEnter the amount to withdraw: ");
        Scanner sc = new Scanner(System.in);

        double amount = sc.nextDouble();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("\nWithdraw Completed Successfully!");
                String str = "Rs " + amount + " withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
                System.out.println("Remaining Balance: " + balance);
            } else
                System.out.println("\nInsufficient Balance.");

        } catch (Exception e) {
        }
        System.out.println("Your balance is: " + balance + "/-");
    }

    // Deposit
    public void deposit() {
        System.out.println("\nEnter amount to deposit: ");
        Scanner sc = new Scanner(System.in);
        double amount = sc.nextDouble();

        try {
            if (amount <= 100000.00) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit successful !");
                String str = "Rs" + amount + " deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else
                System.out.println("\nLimit Exceeded!");
        } catch (Exception e) {
        }
        System.out.println("Your balance is: " + balance + "/-");
    }

    // Transfer
    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the recepient name: ");
        String recepient = sc.nextLine();
        System.out.println("\nEnter account number of the recipient: ");
        Scanner inp = new Scanner(System.in);
        double recp_acc = inp.nextDouble();
        System.out.println("\nEnter amount to be transferred: ");
        double amount = sc.nextDouble();
        try {
            if (balance >= amount) {
                if (amount <= 50000.00) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully transferred to " + recepient);
                    String str = "Rs " + amount + " transferred to " + recepient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else
                    System.out.println("\n Transfer limit exceeded");
            } else
                System.out.println("\nInsufficient Balance.");
        } catch (Exception e) {
        }
        System.out.println("Your balance is: " + balance + "/-");
    }

    // Check balance
    public void checkBalance() {
        System.out.println("You have " + "Rs " + balance + "/-" + " left in your account!");
    }

    // transaction history
    public void transHistory() {
        System.out.println("Your balance is: " + balance + "/-");
        if (transactions == 0)
            System.out.println("\nYou have not made any transactions yet.");
        else
            System.out.println("\n" + transactionHistory);
    }
}
