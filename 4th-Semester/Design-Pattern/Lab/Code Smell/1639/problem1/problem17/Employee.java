public class Employee extends Worker{
    public Employee(String name, int salary) {
        super(name, salary);
    }
    public void giveRise(int riseAmount) {
        adjustCompensation(riseAmount);
    }
    @Override
    public String getWorkerType() {
        return "Employee";
    }
}
