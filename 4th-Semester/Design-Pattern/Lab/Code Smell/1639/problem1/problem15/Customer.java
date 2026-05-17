public class Customer {
    private final String name;
    private final String address;
    private final String phone;
    private final String email;

    public Customer(String name, String address, String phone, String email) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
}