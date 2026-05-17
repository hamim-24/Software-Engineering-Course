public class Employee {
    private String name;
    private int salary;
    private double taxRate;
    
    public Employee(String name, int salary, double taxRate) {
        this.salary = salary;
        this.name = name;
        this.taxRate = taxRate;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    public int getSalary() {
        return salary;
    }
    public double getTaxRate() {
        return taxRate;
    }
}