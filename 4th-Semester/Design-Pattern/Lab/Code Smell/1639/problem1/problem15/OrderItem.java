public class OrderItem {
    private final String productName;
    private final int    quantity;
    private final double price;
    private final double discount;
    public OrderItem(String productName, int quantity, double price, double discount) {
        this.productName = productName;
        this.quantity    = quantity;
        this.price       = price;
        this.discount    = discount;
    }
    public double calculateTotal() {
        return price * quantity * (1 - discount);
    }
    public double getDiscount() {
        return discount;
    }
    public double getPrice() {
        return price;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantity() {
        return quantity;
    }
}
