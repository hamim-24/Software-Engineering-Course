public class PermanentEmployeeSalaryCalculator extends EmployeeSalaryCalculator {
    public PermanentEmployeeSalaryCalculator(Employee employee) {
        super(employee);
    }
    @Override
    protected double calculateBasicSalary() {
        return employee.getMonthlyBasicSalary();
    }
    @Override
    protected double calculateAllowances() {
        double houseRent = basicSalary * 0.20;
        double medical = basicSalary * 0.10;
        double transport = basicSalary * 0.05;
        return houseRent + medical + transport;
    }

    @Override
    protected double calculateDeductions() {
        return basicSalary * 0.05;
    }

    @Override
    protected double calculateTax() {
        return basicSalary * 0.10;
    }
}