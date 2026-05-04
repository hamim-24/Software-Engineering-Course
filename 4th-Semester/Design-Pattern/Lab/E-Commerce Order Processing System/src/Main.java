import task5_dip.*;
import task4_isp.*;
import task2_ocp.*;

public class Main {
    public static void main(String[] args) {
        String[][] order = {{"Item1", "50"}, {"Item2", "75"}};

        System.out.println("=== Task 1: SRP ===");
        task1_srp.OrderProcessor srp = new task1_srp.OrderProcessor();
        System.out.println("Total: $" + srp.processOrder(order, "credit", "express"));

        System.out.println("\n=== Task 2: OCP ===");
        task2_ocp.OrderProcessor ocp = new task2_ocp.OrderProcessor();
        System.out.println("Total: $" + ocp.processOrder(order, new task2_ocp.BkashPayment(), new ExpressShipping()));

        System.out.println("\n=== Task 3: LSP ===");
        task3_lsp.OrderProcessor lsp = new task3_lsp.OrderProcessor();
        System.out.println("Total: $" + lsp.processOrder(order, new task3_lsp.BkashPayment(), new StandardShipping()));

        System.out.println("\n=== Task 4: ISP ===");
        task4_isp.OrderProcessor isp = new task4_isp.OrderProcessor();
        System.out.println("Total: $" + isp.processOrder(order, new task4_isp.BkashPayment(), new StandardShipping()));

        System.out.println("\n=== Task 5: DIP ===");
        task5_dip.OrderProcessor dip = new task5_dip.OrderProcessor(
            new OrderCalculator(),
            new PercentageDiscount(),
            new EmailNotifier()
        );
        System.out.println("Total: $" + dip.processOrder(order, new task4_isp.CreditCardPayment(), new ExpressShipping()));
    }
}
