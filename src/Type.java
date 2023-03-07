import java.util.Objects;

public enum Type {

    WORK("Рабочая"),
    PERSONAL("Личная");
    private final String type;

    /**
     * Конструктор
     */
    Type(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return type;
    }
}
