import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {

    /**
     * Конструктор
     */
    public MonthlyTask(String title, Type type, String description) throws IncorrectArgumentException {
        super(title, type, description);
    }

    /**
     * Метод
     */
    public boolean appearsIn (LocalDate date) {
        return getDateTime().getDayOfMonth() == (date.getDayOfMonth());
    }
}
