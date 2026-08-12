public class Payslip {

    private Employee employee;

    private double basicSalary;
    private double allowances;
    private double deductions;
    private double tax;
    private double netSalary;

    public Payslip(Employee employee, double basicSalary, double allowances, double deductions, double tax) {
        this.employee = employee;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.tax = tax;
        this.netSalary = basicSalary + allowances - deductions - tax;
    }

    public void display() {

        System.out.println("\n========================================");
        System.out.println("              EMPLOYEE PAYSLIP");
        System.out.println("========================================");

        System.out.println("Employee ID  : " + employee.getEmployeeId());
        System.out.println("Name         : " + employee.getName());
        System.out.println("Designation  : " + employee.getDesignation());
        System.out.println("Department   : " + employee.getDepartment());
        System.out.println("Employee Type: " + employee.getEmployeeType());

        System.out.println("----------------------------------------");

        System.out.printf("Basic Salary : %.2f%n", basicSalary);
        System.out.printf("Allowances   : %.2f%n", allowances);
        System.out.printf("Deductions   : %.2f%n", deductions);
        System.out.printf("Tax          : %.2f%n", tax);

        System.out.println("----------------------------------------");

        System.out.printf("Net Salary   : %.2f%n", netSalary);

        System.out.println("========================================");
    }
}