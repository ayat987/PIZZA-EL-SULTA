import java.util.Objects;
public class DeliveryService {

    public void deliver(Order order ,DeliveryTrack deliveryTrack){
        Objects.requireNonNull(order,"Order");
        Objects.requireNonNull(deliveryTrack,"delivery Track");
        if (order.getorderType()!=OrderType.DELIVERY){
            throw new IllegalStateException("This is not a delivery order");
        }
        deliveryTrack.track();
        deliveryTrack.estimateDeliveryTime();
    }
}
