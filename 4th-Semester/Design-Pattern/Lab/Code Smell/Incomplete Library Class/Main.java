import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(LocalDate.of(2005, 6, 24));
        System.out.println("Age: " + employee.getAge());
    }
}
