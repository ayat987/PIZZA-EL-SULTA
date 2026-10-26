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
                new Order(customer, OrderType.TAKEAWAY);
        PaymentService paymentService=new PaymentService();
        NotificationService notifier = new SMSNotifier();

        takeawayOrder.addItem(margherita, 2);
        takeawayOrder.addItem(cola, 1);

        takeawayOrder.setPromotion(new OpeningPromotion());

        //paymentService.pay(takeawayOrder, new cashPayment());
        if (paymentService.pay(takeawayOrder, new cashPayment())) {
            takeawayOrder.markReady();
            notifier.notify(takeawayOrder.getCustomer(), "Your order is ready!");
            takeawayOrder.complete();
        } else {
            System.out.println("Payment failed. Order is still NEW.");
        }

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
                new Order(deliveryCustomer, OrderType.DELIVERY);
        DeliveryService deliveryService = new DeliveryService();

        deliveryOrder.addItem(chickenRanch, 2);
        deliveryOrder.addItem(garlicBread, 1);
        deliveryOrder.addItem(cola, 1);

        deliveryOrder.setPromotion(new MarketingPromotion());

        if (paymentService.pay(deliveryOrder, new cardPayment())) {
            deliveryOrder.markReady();
            notifier.notify(deliveryOrder.getCustomer(), "Your order is ready!");
            deliveryService.deliver(deliveryOrder, new InternalDelivery());
            deliveryOrder.complete();
        } else {
            System.out.println("Payment failed. Order is still NEW.");
        }

        receipt.print(deliveryOrder);

        PaymentMethod alwaysFail = amount -> {
            System.out.println("Card authorization FAILED");
            return false;
        };
        PaymentMethod alwaysSucceed = amount -> {
            System.out.println("Card payment authorized");
            return true;
        };

        System.out.println("\n===== PHASE 2 DEMO =====");

        Order retryOrder = new Order(customer, OrderType.TAKEAWAY);
        retryOrder.addItem(cola, 2);

        if (!paymentService.pay(retryOrder, alwaysFail)) {
            System.out.println("Payment failed. Status stays: " + retryOrder.getStatus());
            System.out.println("Retrying with wallet...");

            paymentService.pay(retryOrder, alwaysSucceed);
        }
        System.out.println("Status now: " + retryOrder.getStatus());


        OnlineWallet walletForRefund = new OnlineWallet();

        walletForRefund.pay(60);
        walletForRefund.refund(60);

        NotificationService whatsapp = new WhatsAppNotifier();
        whatsapp.notify(customer, "Your order is ready!");


        Order expressOrder = new Order(deliveryCustomer, OrderType.DELIVERY);
        expressOrder.addItem(garlicBread, 1);
        if (paymentService.pay(expressOrder, alwaysSucceed)) {
            expressOrder.markReady();
            deliveryService.deliver(expressOrder, new SultanExpress());
            expressOrder.complete();
        }
        /***************************^9+9^*********************************/
        System.out.println("\n===== REFUND DEMO =====");
        Order refundDemoOrder = new Order(customer, OrderType.TAKEAWAY);
        refundDemoOrder.addItem(cola, 1);

        if (paymentService.pay(refundDemoOrder, new OnlineWallet())) {
            paymentService.refund(refundDemoOrder, 30);
            System.out.println("Refund successful!");
        }
        tryCase("Refund order that was never paid", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, 1);
            paymentService.refund(o, 30);
        });
        tryCase("Refund more than paid", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, 1);
            if (paymentService.pay(o, new OnlineWallet())) {
                paymentService.refund(o, 1000);
            } else {
                throw new IllegalStateException("Wallet payment failed, retry demo");
            }
        });

        tryCase("Refund a cash payment", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, 1);
            paymentService.pay(o, new cashPayment());
            paymentService.refund(o, 30);
        });
        /******************************^9+9^******************************************/
        tryCase("Empty order", () -> {
            Order empty = new Order(customer, OrderType.TAKEAWAY);
            paymentService.pay(empty, new cashPayment());
        });

        tryCase("Quantity 0", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, 0);
        });

        tryCase("Negative quantity", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, -2);
        });

        tryCase("Delivery without address/phone", () -> {
            Customer noInfo = new Customer("Sara", "", "sara@gmail.com", "");
            new Order(noInfo, OrderType.DELIVERY);
        });

        tryCase("Modify order after payment", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, 1);
            paymentService.pay(o, new cashPayment());
            o.addItem(water, 1);
        });

        tryCase("Pay twice", () -> {
            Order o = new Order(customer, OrderType.TAKEAWAY);
            o.addItem(cola, 1);
            paymentService.pay(o, new cashPayment());
            paymentService.pay(o, new cashPayment());
        });
    }
    private static void tryCase(String title, Runnable action) {
        System.out.println("\n--- Edge case: " + title + " ---");
        try {
            action.run();
            System.out.println("Unexpected: no error was thrown!");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException e) {
            System.out.println("Rejected correctly: " + e.getMessage());
        }
    }

}