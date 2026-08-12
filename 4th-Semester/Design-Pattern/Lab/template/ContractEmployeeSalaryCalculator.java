public class ContractEmployeeSalaryCalculator extends EmployeeSalaryCalculator {
    public ContractEmployeeSalaryCalculator(Employee employee) {
        super(employee);
    }
    @Override
    protected double calculateBasicSalary() {
        return employee.getMonthlyBasicSalary();
    }
    @Override
    protected double calculateAllowances() {
        return 5000;
    }
    @Override
    protected double calculateDeductions() {
        return basicSalary * 0.03;
    }
    @Override
    protected double calculateTax() {
        return basicSalary * 0.05;
    }
}