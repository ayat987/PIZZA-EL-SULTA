public class InternalDelivery implements assignDriver,DeliveryTrack{
    @Override
    public void assigndriver(){
        System.out.println("Driver assigned");
    }
    @Override
    public void track(){
        assigndriver();
        System.out.println("Tracking InternalDelivery ");
    }
    @Override
    public void  estimateDeliveryTime(){
        System.out.println("Estimated delivery time: 30 minutes");
    }

}