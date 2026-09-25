import java.util.*;
public class Main {
    public static void main(String[] args) {
        Pizza margherita =
                new Pizza("Margherita", 100, true, pizzaSize.medium);

        Pizza chickenRanch =
                new Pizza("Chicken Ranch", 160, true, pizzaSize.large);

        Drink cola =
                new Drink("Cola", 30, true);

        Drink water =
                new Drink("Water", 15, true);

        GarlicBread garlicBread =
                new GarlicBread("Garlic Bread", 50, true);

        margherita.addTopping(new topping("Extra Cheese", 25));
        margherita.addTopping(new topping("Olives", 15));

        Customer customer =
                new Customer(
                        "Alice",
                        "01000000000",
                        "Alice@gmail.com",
                        "codeforces"
                );

        Order takeawayOrder =
                new Order(customer, OrderType.takeaway);

        takeawayOrder.addItem(margherita, 2);
        takeawayOrder.addItem(cola, 1);

        takeawayOrder.setPromotion(new OpeningPromotion());

        takeawayOrder.setPaymentMethod(new cashPayment());

        takeawayOrder.pay();
        receipt receipt = new receipt();
        receipt.print(takeawayOrder);

        Customer deliveryCustomer =
                new Customer(
                        "Bob",
                        "01111111111",
                        "Bob@gmail.com",
                        "codeforces"
                );
        Order deliveryOrder =
                new Order(deliveryCustomer, OrderType.delivery);

        deliveryOrder.addItem(chickenRanch, 2);
        deliveryOrder.addItem(garlicBread, 1);
        deliveryOrder.addItem(cola, 1);

        deliveryOrder.setPromotion(new MarketingPromotion());
        deliveryOrder.setPaymentMethod(new cardPayment());
        deliveryOrder.pay();
        deliveryOrder.setDeliveryService(
                new InternalDelivery()
        );

        deliveryOrder.trackDelivery();
        deliveryOrder.assignDriver();

        deliveryOrder.markReady(new SMSNotifier());
        deliveryOrder.complete();

        receipt.print(deliveryOrder);
    }

}