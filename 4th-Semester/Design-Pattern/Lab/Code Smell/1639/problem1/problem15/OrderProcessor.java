public class OrderProcessor {
    private Customer customer;
    private OrderItem item;
    private ShippingDetails shipping;

    public OrderProcessor(Customer customer, OrderItem orderItem, ShippingDetails shippingDetails) {
        this.customer = customer;
        this.item = orderItem;
        this.shipping = shippingDetails;
    }
    public void processOrder() {
        validateOrder();
        printOrderSummary();
    }

    private void validateOrder() {
        System.out.println("Validating order for: " + customer.getName());
    }

    private void printOrderSummary() {
        System.out.println("Customer : " + customer.getName() + " (" + customer.getEmail() + ")");
        System.out.println("Address  : " + customer.getAddress());
        System.out.println("Product  : " + item.getProductName());
        System.out.println("Qty      : " + item.getQuantity() + item.getPrice());
        System.out.println("Subtotal : " + item.calculateTotal());
        System.out.println("Payment  : " + shipping.getPaymentMethod());
        System.out.println("Shipping : " + shipping.getShippingMethod());
    }
}
