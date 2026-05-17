public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Apple", 100);
        Item item2 = new Item("Banana", 110);
        Order order = new Order();
        order.addItem(item1);
        order.addItem(item2);
        Calculator calculator = new Calculator();
        ReceiptService receiptService = new ReceiptService(calculator);
        InvoiceService invoiceService = new InvoiceService(calculator);
        receiptService.printReceipt(order);
        invoiceService.printInvoice(order);
    }
}
