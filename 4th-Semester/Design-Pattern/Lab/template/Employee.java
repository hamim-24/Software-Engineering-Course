public class Employee {

    private String employeeId;
    private String name;
    private String designation;
    private String employeeType;
    private String department;

    private double monthlyBasicSalary;
    private double hourlyRate;
    private int hoursWorked;

    public Employee(
            String employeeId,
            String name,
            String designation,
            String employeeType,
            String department,
            double monthlyBasicSalary,
            double hourlyRate,
            int hoursWorked) {

        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.employeeType = employeeType;
        this.department = department;
        this.monthlyBasicSalary = monthlyBasicSalary;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getDepartment() {
        return department;
    }

    public double getMonthlyBasicSalary() {
        return monthlyBasicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
}