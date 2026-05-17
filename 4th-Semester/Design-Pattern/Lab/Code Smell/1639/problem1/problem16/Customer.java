public class Customer {
    private final String  name;
    private final String  email;
    private final Address address;

    public Customer(String name, String email, Address address) {
        this.name    = name;
        this.email   = email;
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
}
