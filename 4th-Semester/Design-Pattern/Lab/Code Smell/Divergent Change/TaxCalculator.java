public class TaxCalculator {
    public double calculateTax(Employee employee) {
        return employee.getSalary() * employee.getTaxRate();
    }
}
