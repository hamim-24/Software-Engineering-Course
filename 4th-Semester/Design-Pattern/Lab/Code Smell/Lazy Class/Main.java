public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.cancelOrder();
        orderService.placeOrder();
    }
}
