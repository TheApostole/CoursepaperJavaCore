import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {

    /**
     * Конструктор
     */
    public DailyTask(String title, Type type, String description) throws IncorrectArgumentException {
        super(title, type, description);
    }

    /**
     * Метод
     */
    public boolean appearsIn (LocalDate time) {
        return true;
    }

}
