import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    /**
     * Конструктор
     */
    public YearlyTask(String title, Type type, String description) throws IncorrectArgumentException {
        super(title, type, description);
    }

    /**
     * Метод
     */
    public boolean appearsIn (LocalDate time) {
        return getDateTime().getDayOfYear() == (time.getDayOfYear());
    }
}
