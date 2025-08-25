import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class BankAccount {
    private String name;
    private int id;
    private double balance;

    public BankAccount(String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(double withdrawAmount) throws IllegalArgumentException {
        if (withdrawAmount > balance) {
            throw new IllegalArgumentException("Not enough balance");
        }
        this.balance -= withdrawAmount;
        System.out.println("Successful deposit. New balance: " + this.balance);
    }

    public void deposit(double depositAmount) throws IllegalArgumentException {
        if (depositAmount < 0) {
            throw new IllegalArgumentException("Negative deposit amount");
        }
        this.balance += depositAmount;
        System.out.println("Successful withdrawal. New balance: " + this.balance);
    }

    public double getBalance() {
        return this.balance;
    }

    public int getId() {
        return this.id;
    }

    public void printAccountDetails() {
        System.out.println("Id: " + this.id + "\nName: " + this.name);
    }

}

class DirectDepositProcessor {
    private BankAccount account;
    private Map<LocalDateTime, Double> depositHistory;

    public DirectDepositProcessor(BankAccount account) {
        this.account = account;
        this.depositHistory = new HashMap<>();
    }

    public void depositSalary(double salary) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        account.deposit(salary);
        depositHistory.put(currentDateTime, salary);
        System.out.println("Successfully put salary into account: ");
        account.printAccountDetails();
        System.out.println("New balance: " + account.getBalance());
    }

    public void printSalaryHistory() {
        for (Map.Entry<LocalDateTime, Double> entry : depositHistory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}

class CheckProcessor {
    private BankAccount account;

    public CheckProcessor(BankAccount account) {
        this.account = account;
    }

    public void validateCheque(BankAccount sender, double amount) throws IllegalArgumentException {
        if (sender.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds. Cheque bounced");
        }
        if (sender.getId() == account.getId()) {
            throw new IllegalArgumentException("Cannot send cheque to receiver");
        }
        sender.withdraw(amount);
        account.deposit(amount);
        generateReceipt(sender, amount);
    }

    private void generateReceipt(BankAccount sender, double amount) {
        System.out.println("-------------------");
        System.out.println(LocalDateTime.now().toString() + "\n");
        System.out.println("===================");
        System.out.println("Sender Information:");
        sender.printAccountDetails();
        System.out.println("Receiver Information:");
        account.printAccountDetails();
        System.out.println("Amount transferred: " + amount);
        System.out.println("-------------------");
    }

}

public class SSGE1 {
    public static void main(String[] args) {
        BankAccount john = new BankAccount("John Appleseed", 1, 1000);
        BankAccount yash = new BankAccount("Yash Burshe", 2, 50);
        DirectDepositProcessor ddp = new DirectDepositProcessor(john);
        CheckProcessor cp = new CheckProcessor(yash);
        ddp.depositSalary(1000);
        ddp.depositSalary(1000);
        ddp.printSalaryHistory();
        try {
            cp.validateCheque(yash, 25);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            cp.validateCheque(john, 50000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            cp.validateCheque(john, 500);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
