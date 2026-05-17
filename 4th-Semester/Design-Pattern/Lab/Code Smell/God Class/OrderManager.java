import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private PaymentProcessor paymentProcessor;
    private ShippingService shippingService;
    private InvoiceService invoiceService;
    private EmailService emailService;
    public OrderManager() {
        this.emailService = new EmailService();
        this.invoiceService = new InvoiceService();
        this.orders = new ArrayList<>();
        this.paymentProcessor = new PaymentProcessor();
        this.shippingService = new ShippingService();
    }
    public void createOrder(Order order) {
        orders.add(order);
        paymentProcessor.processPayment(order);
        invoiceService.calculateInvoice(order);
        shippingService.shipOrder(order);
        emailService.sendConfirmationEmail(order);
    }
}
