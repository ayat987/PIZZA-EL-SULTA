public class OpeningPromotion implements Promotion{
    @Override
    public double calculateDiscount(Order order){
        if(order.calculateSubtotal()>=500){
            return order.calculateSubtotal()*0.10;
        }
        return 0;
    }
}