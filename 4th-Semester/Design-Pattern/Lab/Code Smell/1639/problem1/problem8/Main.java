import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LineItem item1 = new LineItem("Apple", 100);
        LineItem item2 = new LineItem("Banana", 110);
        List<LineItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        Invoice invoice = new Invoice(items);

        InvoicePrinter invoicePrinter = new InvoicePrinter();
        invoicePrinter.printInvoive(invoice, .2);
    }
}
