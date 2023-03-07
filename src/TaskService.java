import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskService {

    private static final Map<Integer, Task> taskMap = new HashMap<>();
    private static final Map<Integer, Task> archivedTaskMap = new HashMap<>();
    private static final Collection<Task> collection = new ArrayList<>();


    /**
     * Методы
     */
    public static void add(Scanner scanner) {

        try {
            scanner.nextLine();
            System.out.print("Введите наименование: ");
            String title = scanner.nextLine();
            System.out.print("Введите описание: ");
            String description = scanner.nextLine();
            System.out.print("Введите тип задачи WORK(Рабочая) или PERSONAL(Личная): ");
            String typeTask = scanner.nextLine();
            Type type = Type.valueOf(typeTask);
            System.out.print("Напоминание: 0 - однократная, 1 - ежедневная, 2 - еженедельная, 3 - ежемесячная, 4 - ежегодная: ");
            int repeatability = scanner.nextInt();
            createTask(title, type, description, repeatability);
            System.out.print("Выход");
            scanner.nextLine();
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void removed(Scanner scanner) {
        System.out.println("Выберите задачу из списка:");
        outputOfTasks();
        try {
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            if (taskMap.containsKey(id)) {
                Task deleteTask = taskMap.get(id);
                archivedTaskMap.put(id, deleteTask);
            }else {
                throw new TaskNotFoundException();
            }
            collection.removeIf(h -> id == h.getId());
            taskMap.remove(id);
        }catch (TaskNotFoundException u){
            u.printStackTrace();
            System.out.println("Задача не найдена!!!");
        }
    }

    public static void editTask(Scanner scanner) {
        System.out.println("Выберите задачу из списка:");
        outputOfTasks();
        try {
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            if (!taskMap.containsKey(id)) {
                throw new TaskNotFoundException("Задача не найдена!!!");
            }
            System.out.println("Редактор: 0 - заголовок, 1 - описание, 2 - тип");
            int editMenu = scanner.nextInt();
            switch (editMenu) {
                case 0 -> {
                    scanner.nextLine();
                    System.out.print("Введите наименование: ");
                    String title = scanner.nextLine();
                    Task task = taskMap.get(id);
                    task.setTitle(title);
                    collection.removeIf(h -> id == h.getId());
                    collection.add(task);
                   }
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Введите описание: ");
                    String description = scanner.nextLine();
                    Task task = taskMap.get(id);
                    task.setDescription(description);
                    collection.removeIf(h -> id == h.getId());
                    collection.add(task);
                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.print("Введите тип задачи WORK(Рабочая) или PERSONAL(Личная): ");
                    String typeTask = scanner.nextLine();
                    Task task = taskMap.get(id);
                    task.getType().equals(typeTask);
                    collection.removeIf(h -> id == h.getId());
                    collection.add(task);
                }
            }
        }
        catch (TaskNotFoundException t) {
            System.out.println(t.getMessage());
        }
    }

    public static void getGroupedTask() {
        Map<LocalDate, ArrayList<Task>> groupedTask = new HashMap<>();
        for (Map.Entry<Integer, Task> map: taskMap.entrySet()){
            Task task = map.getValue();
            LocalDate date = task.getDateTime().toLocalDate();
            if (groupedTask.containsKey(date)) {
                ArrayList<Task> tasks = groupedTask.get(date);
                tasks.add(task);
            }else {
                groupedTask.put(date, new ArrayList<>(Collections.singletonList(task)));
            }
        }
        for (Map.Entry<LocalDate, ArrayList<Task>> taskMap : groupedTask.entrySet()){
            System.out.println(taskMap.getKey() + " : " + taskMap.getValue());
        }
    }

    public static void getAllTasksByDate(Scanner scanner) {
        System.out.println("Введите дату dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate getDate = LocalDate.parse(date, dateFormat);
            List<Task> foundTasks = getAllByDate(getDate);
            System.out.println("Задачи на " + getDate + ":");
            for (Task mission : foundTasks) {
                System.out.println(mission);
            }
        } catch (DateTimeException i) {
            System.out.println("Неверный вормат(дд.мм.гггг)");
        }
        scanner.nextLine();
        System.out.println("Выход");
    }

    public static List<Task> getAllByDate(LocalDate date) {
        List<Task> list = new ArrayList<>();
        for (Task collection : collection) {
            if (date.equals(collection.getDateTime().toLocalDate())) {
                list.add(collection);
            }
        }
        return list;
    }

    private static void createTask(String title, Type type, String description, int repeatability) throws IncorrectArgumentException {
        switch (repeatability) {
            case 0 -> {
                OneTimeTask oneTimeTask = new OneTimeTask(title, type, description);
                collection.add(oneTimeTask);
                taskMap.put(oneTimeTask.getId(), oneTimeTask);
            }
            case 1 -> {
                DailyTask dailyTask = new DailyTask(title, type, description);
                collection.add(dailyTask);
                taskMap.put(dailyTask.getId(), dailyTask);
            }
            case 2 -> {
                WeeklyTask weeklyTask = new WeeklyTask(title, type, description);
                collection.add(weeklyTask);
                taskMap.put(weeklyTask.getId(), weeklyTask);
            }
            case 3 -> {
                MonthlyTask monthlyTask = new MonthlyTask(title, type, description);
                collection.add(monthlyTask);
                taskMap.put(monthlyTask.getId(), monthlyTask);
            }
            case 4 -> {
                YearlyTask yearlyTask = new YearlyTask(title, type, description);
                collection.add(yearlyTask);
                taskMap.put(yearlyTask.getId(), yearlyTask);
            }
        }
    }

    public static void outputOfTasks() {
        for (Task task : collection) {
            System.out.println(task);
        }
    }

    public static void outputOfTasksFromTheArchive() {
        for (Task task : archivedTaskMap.values()) {
            System.out.println(task);
        }
    }

    public static void countRowData(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Ведите текст:");
        String text = scanner.nextLine();
        Stream<String> stream = Stream.of(text.toLowerCase().split("\\s+")).parallel();
        Map<String, Long> wordFreq = stream.collect(Collectors.groupingBy(String::toString,Collectors.counting()));
        String[] wordArray = text.trim().split("\\s+");
        int count = wordArray.length;
        System.out.println("Количество слов: " + count);
        wordFreq = wordFreq.entrySet().stream().sorted(Comparator.comparingLong(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        for (Map.Entry<String, Long> taskMap : wordFreq.entrySet()){
            System.out.println(taskMap.getValue() + " - " + taskMap.getKey());
        }
        System.out.println("==============================");
    }
}

