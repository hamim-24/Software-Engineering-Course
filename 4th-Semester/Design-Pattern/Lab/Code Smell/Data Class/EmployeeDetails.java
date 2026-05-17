public class EmployeeDetails {
    private String department;
    private int salary;
    public EmployeeDetails(String department, int salary) {
        this.department = department;
        this.salary = salary;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getDepartment() {
        return this.department;
    }
    public int getSalary() {
        return this.salary;
    }
    @Override
    public String toString() {
        return " Department: " + department + " Salary: " + salary;
    }
}
