public class cashPayment implements PaymentMethod{
    @Override
    public boolean pay(double amount){
        return true;
    }
}