import java.time.LocalDate;
import java.time.Period;

public class Util {
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}