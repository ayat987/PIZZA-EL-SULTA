import java.util.ArrayList;
import java.util.*;

public class Pizza extends MenuItem{
    private pizzaSize size;
    private double exPrice;
    private List<topping> toppings = new ArrayList<>();
    public void addTopping(topping top) {
        toppings.add(top);
    }
    public Pizza(String name ,double Price, boolean isAva,pizzaSize size){
        super(name,Price,isAva);
        this.size=size;
    }
    private double getExPrice(){
        if(size==pizzaSize.small){
            return 0;
        }else if(size==pizzaSize.medium){
            return 30;
        }else return 60;
    }
    @Override
    public double getPrice() {
        double Toppingprice=0;
        for(topping i:toppings){
            Toppingprice+=i.getPrice();
        }
        return getBasePrice()+getExPrice()+Toppingprice;
    }
    public pizzaSize getSize() { return size; }
}