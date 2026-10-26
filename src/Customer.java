public class Customer{
    private String name;
    private String phone;
    private String Email;
    private String address;
    public Customer(String name,String phone,String Email,String address){
        this.name=name;
        this.Email=Email;
        this.phone=phone;
        this.address=address;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return phone;
    }

    public String getEmail() {
        return Email;
    }
    public boolean hasDeliveryInfo(){
        return address!=null && !address.isBlank()&&phone!=null && !phone.isBlank();
    }
}