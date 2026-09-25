public class OrderItem {
    private MenuItem item;
    private int quantity;
    public OrderItem (MenuItem item ,int quantity){
        if(quantity<=0){
            throw new IllegalArgumentException("Invalid amount");
        }
        this.item=item;
        this.quantity=quantity;
    }
    public double calculatePrice(){
        return quantity*item.getPrice();
    }
    public MenuItem getItem() {
        return item;
    }
    public int getQuantity() {
        return quantity;
    }
}