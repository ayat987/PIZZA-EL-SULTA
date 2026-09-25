public class receipt{
    public void print(Order order){
        System.out.println("-_-_-_-_-_- PIZZA EL SULTAN -_-_-_-_-_-");
        System.out.println("Customer  : " +order.getCustomer().getName());
        System.out.println("Order Type: " + order.getorderType());
        System.out.println("-------------------------------------");
        System.out.println("Items:");
        for(OrderItem i:order.getItems()){
            System.out.println(
                    i.getItem().getName() + " x" + i.getQuantity() + " = " + i.calculatePrice() + " EGP"
            );
        }
        System.out.println("-------------------------------------");
        System.out.println("Subtotal: "
                + order.calculateSubtotal() + " EGP");

        System.out.println("Discount: "
                + order.calculateDiscount() + " EGP");

        System.out.println("Fulfillment: "
                + order.calculateFulfillmentCharge() + " EGP");

        System.out.println("Final Total: "
                + order.calculateTotal() + " EGP");

        System.out.println("Payment: "
                + order.getStatus());

        System.out.println("=====================================");
    }
}