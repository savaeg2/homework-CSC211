// Credit Card payment implementation
class CreditCardPayment implements PaymentProcessor {
    private String cardNumber;
    private double balance;
    private StringBuilder transactionHistory;
    private double creditLimit;

    public CreditCardPayment(String cardNumber, double creditLimit) {
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = creditLimit;
        this.transactionHistory = new StringBuilder();
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addToHistory("Payment: -$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean refundPayment(double amount) {
        if (balance + amount <= creditLimit) {
            balance += amount;
            addToHistory("Refund: +$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    @Override
    public void addFunds(double amount) {
        if (balance + amount <= creditLimit) {
            balance += amount;
            addToHistory("Payment to card: +$" + String.format("%.2f", amount));
        }
    }

    @Override
    public String getPaymentType() {
        return "Credit Card (**** " + cardNumber.substring(cardNumber.length() - 4) + ")";
    }

    private void addToHistory(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }
}

// Digital Wallet payment implementation
class DigitalWallet implements PaymentProcessor {
    private String email;
    private double balance;
    private StringBuilder transactionHistory;

    public DigitalWallet(String email, double initialBalance) {
        this.email = email;
        this.balance = initialBalance;
        this.transactionHistory = new StringBuilder();
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addToHistory("Payment: -$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean refundPayment(double amount) {
        balance += amount;
        addToHistory("Refund: +$" + String.format("%.2f", amount));
        return true;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    @Override
    public void addFunds(double amount) {
        balance += amount;
        addToHistory("Funds added: +$" + String.format("%.2f", amount));
    }

    @Override
    public String getPaymentType() {
        return "Digital Wallet (" + email + ")";
    }

    private void addToHistory(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }
}

// Bank Account payment implementation
class BankAccount implements PaymentProcessor {
    private String accountNumber;
    private double balance;
    private StringBuilder transactionHistory;
    private double overdraftLimit;

    public BankAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.overdraftLimit = overdraftLimit;
        this.transactionHistory = new StringBuilder();
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            addToHistory("Payment: -$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean refundPayment(double amount) {
        balance += amount;
        addToHistory("Refund: +$" + String.format("%.2f", amount));
        return true;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    @Override
    public void addFunds(double amount) {
        balance += amount;
        addToHistory("Deposit: +$" + String.format("%.2f", amount));
    }

    @Override
    public String getPaymentType() {
        return "Bank Account (**** " + accountNumber.substring(accountNumber.length() - 4) + ")";
    }

    private void addToHistory(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }
}

// Payment processor demonstration
public class PaymentDemo {
    public static void main(String[] args) {
        // Create different payment methods
        PaymentProcessor creditCard = new CreditCardPayment("1234567890123456", 5000.0);
        PaymentProcessor digitalWallet = new DigitalWallet("user@email.com", 1000.0);
        PaymentProcessor bankAccount = new BankAccount("9876543210", 2000.0, 500.0);

        // Test each payment method
        testPaymentMethod(creditCard);
        System.out.println("\n" + "=".repeat(50) + "\n");
        testPaymentMethod(digitalWallet);
        System.out.println("\n" + "=".repeat(50) + "\n");
        testPaymentMethod(bankAccount);
    }

    private static void testPaymentMethod(PaymentProcessor paymentMethod) {
        System.out.println("Testing " + paymentMethod.getPaymentType());
        System.out.println("Initial balance: $" + String.format("%.2f", paymentMethod.getBalance()));

        // Test payment
        double paymentAmount = 500.0;
        System.out.println("\nAttempting payment of $" + paymentAmount);
        if (paymentMethod.processPayment(paymentAmount)) {
            System.out.println("Payment successful");
        } else {
            System.out.println("Payment failed - insufficient funds");
        }

        // Test refund
        double refundAmount = 200.0;
        System.out.println("\nProcessing refund of $" + refundAmount);
        if (paymentMethod.refundPayment(refundAmount)) {
            System.out.println("Refund successful");
        } else {
            System.out.println("Refund failed");
        }

        // Add funds
        double addAmount = 1000.0;
        System.out.println("\nAdding $" + addAmount);
        paymentMethod.addFunds(addAmount);

        // Show final balance and transaction history
        System.out.println("\nFinal balance: $" + String.format("%.2f", paymentMethod.getBalance()));
        System.out.println("\nTransaction History:");
        System.out.println(paymentMethod.getTransactionHistory());
    }
}