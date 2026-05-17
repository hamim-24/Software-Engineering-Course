public class Employee {
    private String name;
    private Money salary;
    private HireDate date;

    public Employee(String name, Money salary, HireDate date) {
        this.date = date;
        this.name = name;
        this.salary = salary;
    }
    public Money getSalary() {
        return salary;
    }
    public HireDate getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
}
