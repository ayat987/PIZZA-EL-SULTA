import java.util.Objects;

public class PaymentService {
    public boolean pay(Order order,PaymentMethod payment){
        Objects.requireNonNull(order);
        Objects.requireNonNull(payment);
        order.requirePayable();
        boolean success=payment.pay(order.calculateTotal());
        if(success){
            order.markPaid();
        }
        return success;
    }
}
