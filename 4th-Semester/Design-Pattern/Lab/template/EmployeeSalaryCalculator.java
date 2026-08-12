public abstract class EmployeeSalaryCalculator {

    protected Employee employee;

    protected double basicSalary;
    protected double allowances;
    protected double deductions;
    protected double tax;

    public EmployeeSalaryCalculator(Employee employee) {
        this.employee = employee;
    }
    public final Payslip calculateSalary() {
        loadEmployeeInformation();
        basicSalary = calculateBasicSalary();
        allowances = calculateAllowances();
        deductions = calculateDeductions();
        tax = calculateTax();
        return generatePayslip();
    }
    protected void loadEmployeeInformation() {
        System.out.println( "\nProcessing salary for: " + employee.getName() );
    }
    protected abstract double calculateBasicSalary();
    protected abstract double calculateAllowances();
    protected abstract double calculateDeductions();
    protected abstract double calculateTax();

    protected Payslip generatePayslip() {
        System.out.println("Generating payslip for " + employee.getName());
        return new Payslip(employee, basicSalary, allowances, deductions, tax);
    }
}