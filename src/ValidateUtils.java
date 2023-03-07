public class ValidateUtils {

    /**
     * Метод
     */
    public static String validate(String parameter) throws IncorrectArgumentException  {
        if (parameter == null || parameter.isEmpty() || parameter.isBlank()) {
            throw new IncorrectArgumentException("Некорректный ввод");
        }else {
            return parameter;
        }
    }
}
