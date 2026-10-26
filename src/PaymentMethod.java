public interface PaymentMethod{
    boolean pay(double amount);
    default void refund(double amount){
        throw new IllegalStateException("This payment method does not support refunds.");
    }
}