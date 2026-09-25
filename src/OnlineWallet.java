public class  OnlineWallet implements PaymentMethod,Refundable{
    @Override
    public boolean pay(double amount){
        return true;
    }
    @Override
    public void refund(double amount){
        if(amount<=0){
            throw  new IllegalArgumentException("Invalid refund amount");
        }
        System.out.println("Refunding "+amount+" EGP to Your Online Wallet...");
        System.out.println("OnlineWallet refund successful");
        System.out.println("Done");
    }
}