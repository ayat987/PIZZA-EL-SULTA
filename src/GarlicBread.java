public class GarlicBread extends MenuItem{
    public GarlicBread(String name,double price,boolean isAva){
        super(name,price,isAva);
    }
    @Override
    public double getPrice() {
        return getBasePrice();
    }
}