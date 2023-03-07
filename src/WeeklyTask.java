import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    /**
     * Конструктор
     */
    public WeeklyTask(String title, Type type, String description) throws IncorrectArgumentException {
        super(title, type, description);
    }

    /**
     * Метод
     */
    public boolean appearsIn (LocalDate time) {
        return getDateTime().getDayOfWeek().equals(time.getDayOfWeek());
    }
}
