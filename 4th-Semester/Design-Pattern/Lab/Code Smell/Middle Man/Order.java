public class Order {
    private final String id;
    private final Customer customer;
    private final double total;

    public Order(String id, Customer customer, double total) {
        this.total = total;
        this.customer = customer;
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public double getTotal() {
        return total;
    }
    public String getCustomerName() {
        return customer.getName();
    }
    public String getCustomerEmail() {
        return customer.getEmail();
    }
    public String getCustomerZip() {
        return customer.getAddress().getZipCode();
    }
    public String getCustomerCity() {
        return customer.getAddress().getCity();
    }
    public String getCustomerStreet() {
        return customer.getAddress().getStreet();
    }
    public String getCustomerCountry() {
        return customer.getAddress().getCountry();
    }
}
