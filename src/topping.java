public class topping {
    private String name ;
    private double price;
    topping(String name,double price){
        if(price<0){
            throw new IllegalArgumentException("Invalid price");
        }
        this.name=name;
        this.price=price;
    }
    public String getName(){return name;};
    public double getPrice(){return price;};

}