import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order{
    private static final double DELIVERY_FEE=30;
    private Customer customer;
    private List<OrderItem> items=new ArrayList<>();
    private OrderType orderType;
    private OrderState status;
    private Promotion promotion;

    public Customer getCustomer() { return customer; }
    public OrderType getOrderType() { return orderType; }
    public List<OrderItem> getItems() { return List.copyOf(items); }

    public Order(Customer customer, OrderType orderType) {
        this.customer=Objects.requireNonNull(customer,"customer");
        this.orderType=Objects.requireNonNull(orderType,"ordertype");
        if (orderType==OrderType.DELIVERY && !customer.hasDeliveryInfo()){
            throw new IllegalStateException("Delivery requires address and phone");
        }
        status= OrderState.NEW;
    }

    public void requirePayable() {
        requireNew();
        if (items.isEmpty()) {
            throw new IllegalStateException("Order has no items");
        }
    }
    public OrderState getStatus() {
        return status;
    }
    public void requireNew(){
        if(status!= OrderState.NEW){
            throw new IllegalStateException("Order is not payable");
        }
    }

    public OrderType getorderType(){
        return orderType;
    }

    public void setPromotion(Promotion promotion) {
        requireEditable();
        this.promotion = promotion;
    }
    private void requireEditable(){
        if (status != OrderState.NEW)
            throw new IllegalStateException("Order can no longer be modified");
    }
    public void cancel() {
        if (status == OrderState.NEW) {
            status = OrderState.CANCELLED;
        } else {
            throw new IllegalStateException("Order cannot be cancelled");
        }
    }

    public void complete() {
        if(status== OrderState.READY) {
            status = OrderState.COMPLETED;
        }else{
            throw new IllegalStateException("Order is not ready");
        }
    }

    public void markPaid() {
        if (status != OrderState.NEW)
            throw new IllegalStateException("Order is not payable");
        status = OrderState.PAID;
    }

    public void markReady() {
        if (status != OrderState.PAID) {
            throw new IllegalStateException(
                    "Order must be paid before becoming ready"
            );
        }
        status = OrderState.READY;
    }
    public void addItem(MenuItem item,int quantity) { // save
        requireEditable();
        Objects.requireNonNull(item,"item");
        if(quantity<=0){
            throw new IllegalStateException("quantity should be positive");
        }
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

        if(orderType==OrderType.DELIVERY){
            return DELIVERY_FEE;
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

}