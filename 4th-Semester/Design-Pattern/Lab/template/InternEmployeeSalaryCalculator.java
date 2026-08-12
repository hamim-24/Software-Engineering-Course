public class InternEmployeeSalaryCalculator extends EmployeeSalaryCalculator {
    public InternEmployeeSalaryCalculator(Employee employee) {
        super(employee);
    }

    @Override
    protected double calculateBasicSalary() {
        return employee.getMonthlyBasicSalary();
    }

    @Override
    protected double calculateAllowances() {
        return 1000;
    }

    @Override
    protected double calculateDeductions() {
        return 0;
    }

    @Override
    protected double calculateTax() {
        return 0;
    }
}
