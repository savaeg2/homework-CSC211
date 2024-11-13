// Payment interface that defines required methods for all payment types
public interface PaymentProcessor {
    boolean processPayment(double amount);
    boolean refundPayment(double amount);
    double getBalance();
    String getTransactionHistory();
    void addFunds(double amount);
    String getPaymentType();
}
