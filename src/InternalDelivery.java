public class InternalDelivery implements assignDriver,DeliveryTrack{
    @Override
    public void track(){
        System.out.println("Tracking InternalDelivery ");
    }
    @Override
    public void  estimateDeliveryTime(){
        System.out.println("Estimated delivery time: 30 minutes");
    }
    @Override
    public void assigndriver(){
        System.out.println("Driver assigned");
    }
}