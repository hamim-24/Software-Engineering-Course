public class HourlyEmployeeSalaryCalculator extends EmployeeSalaryCalculator {
    public HourlyEmployeeSalaryCalculator(Employee employee) {
        super(employee);
    }
    @Override
    protected double calculateBasicSalary() {
        return employee.getHoursWorked() * employee.getHourlyRate();
    }
    @Override
    protected double calculateAllowances() {
        return 1000;
    }

    @Override
    protected double calculateDeductions() {
        return basicSalary * 0.02;
    }

    @Override
    protected double calculateTax() {
        return basicSalary * 0.02;
    }
}