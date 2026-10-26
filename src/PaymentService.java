import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
public class PaymentService {
    private final Map<Order,Double>paid=new HashMap<>();
    private final Map<Order,PaymentMethod>method=new HashMap<>();
    public boolean pay(Order order,PaymentMethod payment){
        Objects.requireNonNull(order,"order");
        Objects.requireNonNull(payment,"payment");
        order.requirePayable();
        Double amount=order.calculateTotal();
        boolean success=payment.pay(order.calculateTotal());
        if(success){
            order.markPaid();
            paid.put(order,amount);
            method.put(order,payment);
        }
        return success;
    }
    public void refund(Order order, double amount){
        Objects.requireNonNull(order,"order");
        Double remaining =paid.get(order);
        if(remaining==null){
            throw new IllegalStateException("Order was not paid");
        }
        if(amount<=0 || amount>remaining){
            throw new IllegalStateException("Invalid amount of money");
        }
        PaymentMethod payment =method.get(order);
        payment.refund(amount);
        paid.put(order,remaining-amount);

    }
}
