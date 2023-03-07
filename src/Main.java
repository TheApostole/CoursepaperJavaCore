import java.util.*;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            listOfFunctions:
            while (true) {
                System.out.println("Выберите действие: ");
                System.out.println("1. Добавить задачу");
                System.out.println("2. Удалить задачу");
                System.out.println("3. Получить список задач на предстоящий день");
                System.out.println("4. Редактировать задачу");
                System.out.println("5. Вывестри задачи по датам");
                System.out.println("6. Вывести удалённые задачи");
                System.out.println("7. Вывести актуальные задачи");
                System.out.println("8. Подсчитать данные строки");
                System.out.println("0. Выход");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu){
                        case 1:
                            TaskService.add(scanner);
                            break;
                        case 2:
                            TaskService.removed(scanner);
                            break;
                        case 3:
                            TaskService.getAllTasksByDate(scanner);
                            break;
                        case 4:
                            TaskService.editTask(scanner);
                            break;
                        case 5:
                            TaskService.getGroupedTask();
                            break;
                        case 6:
                            TaskService.outputOfTasksFromTheArchive();
                            break;
                        case 7:
                            TaskService.outputOfTasks();
                            break;
                        case 8:
                            TaskService.countRowData(scanner);
                            break;
                        case 0:
                            break listOfFunctions;
                    }
                }else {
                    scanner.next();
                    System.out.println("Выберите действие:");
                }
            }
        }
    }
}