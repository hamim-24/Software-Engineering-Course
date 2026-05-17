public class InvoicePrinter {
    public void printInvoive(Invoice invoice,double tax) {

        System.out.println("Subtotal: " + invoice.getSubtottal());
        System.out.println("Tax: " + invoice.getTax(tax));
        System.out.println("Total: " + invoice.getTotal(tax));
    }
}
