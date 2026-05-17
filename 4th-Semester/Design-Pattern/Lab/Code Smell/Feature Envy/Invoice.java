import java.util.List;

public class Invoice {
    List<LineItem> items;
    public Invoice(List<LineItem> items) {
        this.items = items;
    }
    public List<LineItem> getItems() {
        return items;
    }
    public double getSubtottal() {
        double subtotal = 0;
        for (LineItem item : items) {
            subtotal += item.getPrice();
        } 
        return subtotal;
    }
    public double getTax(double tax) {
        return getSubtottal() * tax;
    }
    public double getTotal(double tax) {
        return getSubtottal() + getTax(tax);
    }
}
