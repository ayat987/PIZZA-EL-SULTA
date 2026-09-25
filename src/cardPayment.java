public class cardPayment implements PaymentMethod,Refundable{
    @Override
    public boolean pay(double amount){
        return Math.random()>0.3;
    }
    @Override
    public void refund(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Invalid refund amount");
        }
        System.out.println("Refunding "+amount+" EGP to card...");
        System.out.println("Card refund successful");
        System.out.println("Done");
    }
}