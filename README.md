1. Briefly describe your architecture.
   the system follows an object-oriented architecture based on SOLID principles. The main Order class manages orders, customers, items, payments, promotions, and delivery. I use interfaces such as PaymentMethod, DeliveryTrack, NotificationService, and Promotion to reduce coupling and allow different implementations to be added easily. Inheritance is used for menu items such as Pizza, Drink, and GarlicBread, while composition is used between Order and OrderItem

2. Give one example where you applied SRP.
   Each class is used for one responsibility, such as receipt, which is used only for printing the order details.

3. How can a new feature or type be added without heavily modifying existing code?
   I used interfaces, so for any new feature, I can make a new class and implement the interface instead of modifying the existing class , Like PaymentMethod interface

4. Did you use inheritance? If yes, explain why the child can genuinely substitute for its parent.
   Yes, because child has the same fields and methods that exist in the parent. After inheritance, I override the function in the child class. The child can also maintain the contract of the parent. In this system, Pizza, Drink, and GarlicBread extend MenuItem, so they can be used as MenuItem.

5. Did every implementation actually need every method defined by its interface?
   Yes, because I separated the interfaces based on their responsibilities, so each implementation only implements the methods it needs.

6. Which high-level parts of your system depend on abstractions rather than concrete implementations?
   In the MenuItem class, I made the class abstract because the classes that extend it, like Pizza and Drink, calculate the price differently. Pizza has extra attributes compared to Drink, so I made the getPrice() function abstract and override it in each child class.

For example, in the Order class, it depends on interfaces like PaymentMethod, Promotion, and DeliveryTrack, so for any new feature, I can make a new class and implement the interface instead of changing the implementation of the Order class.

7. Where did you choose composition instead of inheritance, and why?
   I used composition between Order and OrderItem because an Order contains a list of OrderItem objects. I used composition instead of inheritance because an OrderItem is not a type of Order it is a part of an Order.


8. Which Phase 2 requirement caused the largest change to your original architecture?
   There was no major change because I designed the project in a flexible way from the beginning, using interfaces and SOLID principles, so adding new requirements did not require many changes to the original architecture.

9. What changed between your Initial UML and Final UML?
   Some new features were added to some classes, like a new class called GarlicBread that extends MenuItem, and a new feature for paying with an online wallet. I also added a new interface called Refundable, which is implemented by only two classes: cardPayment and OnlineWallet.

10. If Pizza El Sultan requested five additional payment methods or notification channels tomorrow,
    approximately how much existing code would need modification?

No modification would be needed. I would expand the system by adding new classes that implement the existing interfaces.

11. Which part of your architecture are you least satisfied with, and why?
    I am least satisfied with the Order class because it contains several responsibilities. However, these responsibilities are closely related to the order itself, so I decided not to split them into different classes.

12. If you restarted the assignment today, what would you design differently?
    If I restarted the assignment today, I would design the UML first before writing the code. I also learned that I should separate the code into different classes instead of putting everything in one class. The rest of the design, I am satisfied with.
