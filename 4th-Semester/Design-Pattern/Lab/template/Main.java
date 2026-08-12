public class Main {

    public static void main(String[] args) {
        Employee permanentEmployee = new Employee("E001","Alice","Software Engineer","Permanent","Engineering", 60000,0, 0);
        EmployeeSalaryCalculator permanentCalculator = new PermanentEmployeeSalaryCalculator( permanentEmployee);
        Payslip permanentPayslip = permanentCalculator.calculateSalary();
        permanentPayslip.display();
        Employee contractEmployee = new Employee( "E002", "Bob", "Backend Developer", "Contract", "Engineering", 50000, 0, 0);

        EmployeeSalaryCalculator contractCalculator = new ContractEmployeeSalaryCalculator( contractEmployee);

        Payslip contractPayslip = contractCalculator.calculateSalary();

        contractPayslip.display();

        Employee hourlyEmployee = new Employee("E003", "Charlie", "Support Engineer", "Hourly", "Support", 0, 500, 160);

        EmployeeSalaryCalculator hourlyCalculator = new HourlyEmployeeSalaryCalculator(hourlyEmployee);

        Payslip hourlyPayslip = hourlyCalculator.calculateSalary();

        hourlyPayslip.display();

        Employee internEmployee = new Employee("E004", "David", "Software Engineering Intern", "Intern", "Engineering", 15000, 0, 0);

        EmployeeSalaryCalculator internCalculator = new InternEmployeeSalaryCalculator(internEmployee);

        Payslip internPayslip = internCalculator.calculateSalary();

        internPayslip.display();
    }
}