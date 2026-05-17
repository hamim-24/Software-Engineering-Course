public abstract class Worker implements Compensable {
    private final String name;
    private int compensation;

    public Worker(String name, int compensation) {
        this.compensation = compensation;
        this.name = name;
    }
    @Override
    public void adjustCompensation(int amount) {
        compensation += amount;
    }
    @Override
    public int getCompensation() {
        return compensation;
    }
    public String getSummary() {
        return String.format("[%s] %s | Compensation: $%d",
            getWorkerType(), name, compensation);
    }
    public String getName() {
        return name;
    }
    public abstract String getWorkerType();
}
