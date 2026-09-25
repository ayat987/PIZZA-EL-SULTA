public class MarketingPromotion implements Promotion{
    @Override
    public double calculateDiscount(Order order){
        if(order.calculateSubtotal()>300 && order.pizzaCount()>=2){
            return order.calculateSubtotal()*0.15;
        }
        return 0;
    }
}