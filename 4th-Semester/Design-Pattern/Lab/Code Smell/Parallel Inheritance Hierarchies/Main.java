public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Hamim", 1000);
        Constructor constructor = new Constructor("Rafiq", 100);
        Manager employeeManager = new Manager(100, employee);
        Manager constructorManager = new Manager(10, constructor);

        employee.giveRise(10);;
        constructor.increaseHourlyRate(20);
        employeeManager.assignBonus(500);

        System.out.println(employee.getSummary());
        System.out.println(constructor.getSummary());
        System.out.println(employeeManager.getSummary());
        System.out.println(constructorManager.getSummary());
    }
}
