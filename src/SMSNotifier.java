public class SMSNotifier implements NotificationService{
    @Override
    public void notify(Customer customer, String message){
        System.out.println("Sending SMS to "+ customer.getNumber());
        System.out.println("Message: "+message);
    }
}