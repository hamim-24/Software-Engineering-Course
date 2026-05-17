public class Manager extends Worker implements Bonusable {
    private int bonus;
    public Manager(int bonus, Worker managers) {
        super(managers.getName(), bonus);
        this.bonus = bonus;
    }
    @Override
    public void assignBonus(int bonus) {
        this.bonus = bonus;
    }
    @Override
    public int getBonus() {
        return bonus;
    }
    @Override
    public String getWorkerType() {
        return "Manager";
    }

}
