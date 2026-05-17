import java.time.LocalDate;
import java.time.Period;

public final class HireDate {
    private final LocalDate date;
    public HireDate(int day, int month, int year) {
        this.date = LocalDate.of(year, month, month);
    }
    public LocalDate getDate() {
        return date;
    }
    public int getYear() {
        return Period.between(this.date, LocalDate.now()).getYears();
    }
}
