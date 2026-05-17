import java.time.LocalDate;

public class Employee {
    private LocalDate birthDate;
    public Employee(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public int getAge() {
        return Util.calculateAge(birthDate);
    }
}
