public class Drink extends MenuItem {
    public Drink(String name,double price,boolean isAva){
        super(name,price,isAva);
    }
    @Override
    public double getPrice(){
        return getBasePrice();
    }
}