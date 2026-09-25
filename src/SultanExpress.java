public class  SultanExpress implements DeliveryTrack{
    @Override
    public void track(){
        System.out.println("Tracking Sultan Express ");
    }
    @Override
    public void estimateDeliveryTime(){
        System.out.println("AL Sultan Express Estimated delivery  time: 70 minutes");    }
}