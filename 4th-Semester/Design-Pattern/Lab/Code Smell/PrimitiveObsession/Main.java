public class Main {
    public static void main(String[] args) {
        Currency taka = new Currency("BDT", 124);
        Currency euro = new Currency("EURO", .92);
        Currency doller = new Currency("USD", 1);
        Money money = new Money(1_000_000, taka);

        HireDate date = new HireDate(10, 6, 2020);
        Employee employee = new Employee("Hamim", money, date);

        System.out.println("Salary in BDT: " + employee.getSalary().convertTo(taka));
        System.out.println("Salary in USD: " + employee.getSalary().convertTo(doller));
        System.out.println("Salary in EURO: " + employee.getSalary().convertTo(euro));
        System.out.println("Year of service: " + employee.getDate().getYear());
    }
}
