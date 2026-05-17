public class Constructor extends Worker {
    public Constructor(String name, int salary) {
        super(name, salary);
    }
    public void increaseHourlyRate(int increment) {
        adjustCompensation(increment);
    }
    @Override
    public String getWorkerType() {
        return "Constructor";
    }
}
