public abstract class MenuItem{
    private String name;
    private double price;
    private boolean isAval;
    public MenuItem(String Name, double Price, boolean isAva){
        if(Price<0){
            throw new IllegalArgumentException("Invalid price");
        }
        name=Name;
        price=Price;
        isAval=isAva;
    }
    public abstract double getPrice();

    public String getName(){return name;};
    public double getBasePrice(){return price;};
    public boolean getAval() { return isAval; }
    public void setAval(boolean available) { isAval= available; }
}