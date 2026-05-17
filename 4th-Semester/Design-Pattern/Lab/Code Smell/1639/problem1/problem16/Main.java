public class Main {
    public static void main(String[] args) {

        Address  address  = new Address("42 Gulshan Ave", "Dhaka", "1212", "Bangladesh");
        Customer customer = new Customer("Alice Smith", "alice@example.com", address);
        Order    order    = new Order("ORD-001", customer, 1250.00);
    
        String zip     = order.getCustomerZip();
        String city    = order.getCustomerCity();
        String name    = order.getCustomerName();
        String email   = order.getCustomerEmail();

        System.out.println("Order   : " + order.getId());
        System.out.println("Customer: " + name + " (" + email + ")");
        System.out.println("Ship to : " + order.getCustomerStreet() + ", " + city + " " + zip);
        System.out.println("Country : " + order.getCustomerCountry());
        System.out.println("Total   : " + order.getTotal());
    }
}
