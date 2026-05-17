public class Main {
    public static void main(String[] args) {
          Employee employee = new Employee("Hamim", 100, .12);
          SalaryManager salaryManager = new SalaryManager();
          TaxCalculator taxCalculator = new TaxCalculator();

          System.out.println("Before salary: " + employee.getSalary());
          System.out.println("Tax: " + taxCalculator.calculateTax(employee));
          System.out.println("After rise 20: " + salaryManager.applyRise(20, employee));
    }
}
