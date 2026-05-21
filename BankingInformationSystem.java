import java.io.*;
import java.util.*;

public class BankingInformationSystem {

    static Scanner sc = new Scanner(System.in);
    static Authentication auth = new Authentication();
    static BankManager manager = new BankManager();

    public static void main(String[] args) {

        manager.loadAccounts();
        manager.loadTransactions();
        manager.loadInterestState();

        while (true) {

            System.out.println("\n=================================================");
            System.out.println("         BANKING INFORMATION SYSTEM");
            System.out.println("=================================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            int choice = getIntInput();

            switch (choice) {

                case 1:

                    if (auth.adminLogin()) {

                        manager.log("ADMIN LOGIN");

                        adminMenu();

                        manager.log("ADMIN LOGOUT");
                    }

                    break;

                case 2:

                    Account customer =
                            auth.customerLogin(
                                    manager.accounts);

                    if (customer != null) {

                        manager.log(
                                "CUSTOMER LOGIN : "
                                        + customer.accountId);

                        customerMenu(customer);

                        manager.log(
                                "CUSTOMER LOGOUT : "
                                        + customer.accountId);
                    }

                    break;

                case 3:

                    System.out.println(
                            "Exiting System");

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }

    static void adminMenu() {

        while (true) {

            System.out.println("\n=================================================");
            System.out.println("              ADMIN DASHBOARD");
            System.out.println("=================================================");
            System.out.println("1. Create Account");
            System.out.println("2. Update Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Search Account");
            System.out.println("5. View Accounts");
            System.out.println("6. Reports");
            System.out.println("7. Transactions");
            System.out.println("8. Sort Accounts");
            System.out.println("9. Export Statement");
            System.out.println("10. Update Status");
            System.out.println("11. Apply Interest");
            System.out.println("12. Logout");

            int choice = getIntInput();

            switch (choice) {

                case 1:
                    manager.createAccount();
                    break;

                case 2:
                    manager.updateAccount();
                    break;

                case 3:
                    manager.deleteAccount();
                    break;

                case 4:
                    manager.searchAccount();
                    break;

                case 5:
                    manager.viewAccounts();
                    break;

                case 6:
                    manager.reports();
                    break;

                case 7:
                    manager.viewTransactions();
                    break;

                case 8:
                    manager.sortAccounts();
                    break;

                case 9:
                    manager.exportStatement();
                    break;

                case 10:
                    manager.updateStatus();
                    break;

                case 11:
                    manager.applyInterest();
                    break;

                case 12:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void customerMenu(Account customer) {

        while (true) {

            System.out.println("\n=================================================");
            System.out.println("            CUSTOMER DASHBOARD");
            System.out.println("=================================================");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Inquiry");
            System.out.println("4. Transfer");
            System.out.println("5. Mini Statement");
            System.out.println("6. Transaction History");
            System.out.println("7. Account Details");
            System.out.println("8. Logout");

            int choice = getIntInput();

            switch (choice) {

                case 1:
                    manager.deposit(
                            customer.accountId);
                    break;

                case 2:
                    manager.withdraw(
                            customer.accountId);
                    break;

                case 3:
                    System.out.println(
                            "Balance : "
                                    + customer.balance);
                    break;

                case 4:
                    manager.transfer(
                            customer.accountId);
                    break;

                case 5:
                    manager.miniStatement(
                            customer.accountId);
                    break;

                case 6:
                    manager.transactionHistory(
                            customer.accountId);
                    break;

                case 7:
                    System.out.println(customer);
                    break;

                case 8:
                    return;
            }
        }
    }

    static int getIntInput() {

        while (true) {

            try {

                return Integer.parseInt(
                        sc.nextLine());

            } catch (Exception e) {

                System.out.print(
                        "Enter valid number: ");
            }
        }
    }
}

class Account {

    int accountId;
    String name;
    String password;
    String type;
    double balance;
    String phone;
    String address;
    String status;

    Account(
            int id,
            String n,
            String p,
            String t,
            double b,
            String ph,
            String add,
            String st) {

        accountId = id;
        name = n;
        password = p;
        type = t;
        balance = b;
        phone = ph;
        address = add;
        status = st;
    }

    public String toString() {

        return "\nID : " + accountId +
                "\nName : " + name +
                "\nType : " + type +
                "\nBalance : " + balance +
                "\nPhone : " + phone +
                "\nAddress : " + address +
                "\nStatus : " + status;
    }

    String fileData() {

        return accountId + "," +
                name + "," +
                password + "," +
                type + "," +
                balance + "," +
                phone + "," +
                address + "," +
                status;
    }
}

class Transaction {

    String transactionId;
    String type;
    double amount;
    String details;

    Transaction(
            String id,
            String t,
            double a,
            String d) {

        transactionId = id;
        type = t;
        amount = a;
        details = d;
    }

    public String toString() {

        return transactionId +
                " | " +
                type +
                " | " +
                amount +
                " | " +
                details;
    }

    String fileData() {

        return transactionId +
                "," +
                type +
                "," +
                amount +
                "," +
                details;
    }
}

class Authentication {

    String adminUser = "admin";
    String adminPass = "admin123";

    boolean adminLogin() {

        Scanner sc =
                new Scanner(System.in);

        System.out.print(
                "Username: ");

        String u =
                sc.nextLine();

        System.out.print(
                "Password: ");

        String p =
                sc.nextLine();

        return u.equals(adminUser)
                &&
                p.equals(adminPass);
    }

    Account customerLogin(
            ArrayList<Account> accounts) {

        try {

            Scanner sc =
                    new Scanner(
                            System.in);

            System.out.print(
                    "Account ID: ");

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            if (id <= 0)
                return null;

            System.out.print(
                    "Password: ");

            String pass =
                    sc.nextLine();

            for (Account a :
                    accounts) {

                if (
                        a.accountId == id
                                &&
                                a.password.equals(
                                        pass)
                ) {

                    if (
                            !a.status.equalsIgnoreCase(
                                    "Active")
                    )
                        return null;

                    return a;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid input");
        }

        return null;
    }
}

class BankManager {

    ArrayList<Account> accounts =
            new ArrayList<>();

    ArrayList<Transaction> transactions =
            new ArrayList<>();

    Scanner sc =
            new Scanner(
                    System.in);

    int transactionCounter = 1;

    boolean interestApplied =
            false;

    void createAccount() {

        try {

            System.out.print(
                    "Account ID: ");

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            if (
                    id <= 0
                            ||
                            findAccount(id)
                                    != null
            )
                return;

            System.out.print(
                    "Name: ");

            String name =
                    sc.nextLine();

            System.out.print(
                    "Password: ");

            String pass =
                    sc.nextLine();

            System.out.print(
                    "Type(Savings/Current): ");

            String type =
                    sc.nextLine();

            if (
                    !type.equalsIgnoreCase(
                            "Savings")
                            &&
                            !type.equalsIgnoreCase(
                                    "Current")
            )
                return;

            System.out.print(
                    "Balance: ");

            double bal =
                    Double.parseDouble(
                            sc.nextLine());

            if (
                    bal < 0
            )
                return;

            System.out.print(
                    "Phone: ");

            String phone =
                    sc.nextLine();

            if (
                    !phone.matches(
                            "\\d{10}")
            )
                return;

            System.out.print(
                    "Address: ");

            String address =
                    sc.nextLine();

            accounts.add(
                    new Account(
                            id,
                            name,
                            pass,
                            type,
                            bal,
                            phone,
                            address,
                            "Active"));

            saveAccounts();

        } catch (Exception e) {

            System.out.println(
                    "Invalid input");
        }
    }

    void updateAccount() {

        try {

            System.out.print("ID: ");

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            Account a =
                    findAccount(id);

            if (a == null)
                return;

            System.out.print(
                    "Name: ");
            String name =
                    sc.nextLine();

            System.out.print(
                    "Password: ");
            String pass =
                    sc.nextLine();

            System.out.print(
                    "Type: ");
            String type =
                    sc.nextLine();

            System.out.print(
                    "Phone: ");
            String phone =
                    sc.nextLine();

            System.out.print(
                    "Address: ");
            String address =
                    sc.nextLine();

            if (
                    !type.equalsIgnoreCase(
                            "Savings")
                            &&
                            !type.equalsIgnoreCase(
                                    "Current")
            )
                return;

            if (
                    !phone.matches(
                            "\\d{10}")
            )
                return;

            a.name = name;
            a.password = pass;
            a.type = type;
            a.phone = phone;
            a.address = address;

            saveAccounts();

        } catch (Exception e) {

            System.out.println(
                    "Invalid input");
        }
    }

    void deleteAccount() {

        try {

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            Account a =
                    findAccount(id);

            if (a == null)
                return;

            accounts.remove(a);

            saveAccounts();

        } catch (Exception e) {
        }
    }

    void searchAccount() {

        try {

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            System.out.println(
                    findAccount(id));

        } catch (Exception e) {
        }
    }

    void viewAccounts() {

        for (
                Account a :
                accounts
        )
            System.out.println(a);
    }

    void deposit(int id) {

        try {

            Account a =
                    findAccount(id);

            if (a == null)
                return;

            System.out.print(
                    "Amount: ");

            double amt =
                    Double.parseDouble(
                            sc.nextLine());

            if (
                    amt <= 0
            )
                return;

            a.balance += amt;

            addTransaction(
                    "Deposit",
                    amt,
                    String.valueOf(id));

            saveAccounts();

        } catch (Exception e) {

            System.out.println(
                    "Invalid input");
        }
    }

    void withdraw(int id) {

        try {

            Account a =
                    findAccount(id);

            if (a == null)
                return;

            System.out.print(
                    "Amount: ");

            double amt =
                    Double.parseDouble(
                            sc.nextLine());

            if (
                    amt <= 0
                            ||
                            amt > a.balance
            )
                return;

            a.balance -= amt;

            addTransaction(
                    "Withdraw",
                    amt,
                    String.valueOf(id));

            saveAccounts();

        } catch (Exception e) {

            System.out.println(
                    "Invalid input");
        }
    }

    void transfer(int from) {

        try {

            int to =
                    Integer.parseInt(
                            sc.nextLine());

            if (
                    to <= 0
                            ||
                            from == to
            )
                return;

            Account s =
                    findAccount(from);

            Account d =
                    findAccount(to);

            if (
                    s == null
                            ||
                            d == null
            )
                return;

            double amt =
                    Double.parseDouble(
                            sc.nextLine());

            if (
                    amt <= 0
                            ||
                            amt >
                                    s.balance
            )
                return;

            s.balance -= amt;
            d.balance += amt;

            addTransaction(
                    "Transfer",
                    amt,
                    from + "->" + to);

            saveAccounts();

        } catch (Exception e) {
        }
    }

    void reports() { }

    void updateStatus() {

        try {

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            Account a =
                    findAccount(id);

            if (a == null)
                return;

            String status =
                    sc.nextLine();

            if (
                    !status.equalsIgnoreCase(
                            "Active")
                            &&
                            !status.equalsIgnoreCase(
                                    "Inactive")
                            &&
                            !status.equalsIgnoreCase(
                                    "Blocked")
            )
                return;

            a.status =
                    status.substring(
                            0,
                            1)
                            .toUpperCase()
                            +
                            status.substring(
                                    1)
                                    .toLowerCase();

            saveAccounts();

        } catch (Exception e) {
        }
    }

    void applyInterest() {

        if (
                interestApplied
        )
            return;

        for (
                Account a :
                accounts
        ) {

            if (
                    a.type.equalsIgnoreCase(
                            "Savings")
            ) {

                double interest =
                        a.balance
                                * 0.03;

                a.balance +=
                        interest;

                addTransaction(
                        "Interest",
                        interest,
                        String.valueOf(
                                a.accountId));
            }
        }

        interestApplied =
                true;

        saveInterestState();

        saveAccounts();
    }

    void transactionHistory(
            int id) { }

    void miniStatement(
            int id) { }

    void viewTransactions() { }

    void sortAccounts() {

        accounts.sort(
                Comparator.comparing(
                        (Account a)
                                -> a.name));
    }

    void exportStatement() { }

    Account findAccount(
            int id) {

        for (
                Account a :
                accounts
        )
            if (
                    a.accountId
                            == id
            )
                return a;

        return null;
    }

    void addTransaction(
            String type,
            double amt,
            String details) {

        transactions.add(
                new Transaction(
                        "TXN"
                                +
                                transactionCounter++,
                        type,
                        amt,
                        details));

        saveTransactions();
    }

    void saveAccounts() { }

    void saveTransactions() { }

    void loadAccounts() { }

    void loadTransactions() { }

    void saveInterestState() {

        try {

            FileWriter fw =
                    new FileWriter(
                            "interest.txt");

            fw.write(
                    String.valueOf(
                            interestApplied));

            fw.close();

        } catch (Exception e) {
        }
    }

    void loadInterestState() {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "interest.txt"));

            interestApplied =
                    Boolean.parseBoolean(
                            br.readLine());

            br.close();

        } catch (Exception e) {
        }
    }

    void log(String msg) { }
}
