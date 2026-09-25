public class Customer{
    private String name;
    private String number;
    private String Email;
    private String address;
    public Customer(String name,String number,String Email,String address){
        this.name=name;
        this.Email=Email;
        this.number=number;
        this.address=address;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return Email;
    }
}