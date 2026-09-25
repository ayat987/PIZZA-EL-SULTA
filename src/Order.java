import java.util.ArrayList;
import java.util.List;

public class Order{
    private Customer customer;
    private List<OrderItem> items=new ArrayList<>();
    private OrderType orderType;
    private Orderstate status;
    private Promotion promotion;
    private PaymentMethod paymentMethod;
    private DeliveryTrack deliveryService;
    public void setDeliveryService(DeliveryTrack deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void trackDelivery() {
        if (orderType != OrderType.delivery) {
            throw new IllegalStateException("This is not a delivery order");
        }

        deliveryService.track();
        deliveryService.estimateDeliveryTime();
    }
    public void assignDriver() {
        if (deliveryService instanceof assignDriver) {
            ((assignDriver) deliveryService).assigndriver();
        } else {
            throw new IllegalStateException(
                    "This delivery service controls driver assignment"
            );
        }
    }
    public Order(Customer customer, OrderType orderType) {
        this.customer = customer;
        this.orderType=orderType;
        status=Orderstate.pending;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    public Customer getCustomer() {
        return customer;
    }
    public OrderType getorderType(){
        return orderType;
    }
    public List<OrderItem> getItems(){
        return items;
    }
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void addItem(MenuItem item,int quantity) {
        if(!item.getAval()){
            throw new IllegalStateException("Item is unavailable");
        }
        items.add(new OrderItem(item, quantity));
    }
    public int pizzaCount(){
        int c=0;
        for(OrderItem i:items){
            if(i.getItem() instanceof Pizza)c+=i.getQuantity();
        }
        return c;
    }
    public double calculateFulfillmentCharge(){
        if(orderType==OrderType.delivery){
            return 30;
        }
        return 0;
    }
    public double calculateSubtotal() {
        double Subtotal = 0;
        for(OrderItem i:items){
            Subtotal+=i.calculatePrice();
        }
        return Subtotal;
    }

    public double calculateDiscount() {
        if(promotion==null){
            return 0;
        }
        return promotion.calculateDiscount(this);
    }

    public double calculateTotal() {
        double d= calculateSubtotal() - calculateDiscount()+calculateFulfillmentCharge();
        return Math.max(0,d);
    }
    public Orderstate getStatus() {
        return status;
    }
    public void pay() {
        double total=calculateTotal();
        boolean success= paymentMethod.pay(total);
        if(success) {
            status = Orderstate.paid;
        }
    }

    public void cancel() {
        if (status == Orderstate.pending) {
            status = Orderstate.cancelled;
        } else {
            throw new IllegalStateException("Order cannot be cancelled");
        }
    }

    public void complete() {
        if(status==Orderstate.ready) {
            status = Orderstate.completed;
        }else{
            throw new IllegalStateException("Order is not ready");
        }
    }
    public void markReady(NotificationService notificationService) {
        if (status != Orderstate.paid) {
            throw new IllegalStateException(
                    "Order must be paid before becoming ready"
            );
        }

        status = Orderstate.ready;

        notificationService.notify(
                customer,
                "Your order is ready!"
        );
    }


}