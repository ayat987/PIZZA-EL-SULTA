public class WhatsAppNotifier implements NotificationService{
    @Override
    public void notify(Customer customer, String message){
        System.out.println("Sending WhatsApp to "+ customer.getNumber());
        System.out.println("Message: "+message);
    }
}