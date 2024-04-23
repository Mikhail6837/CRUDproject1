package Developer;
import java.io.*;
import java.util.List;
import java.util.Scanner;


public class DeveloperCreate implements Create {
    private static final String FILENAME = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/Developer.txt";
    private static final String LAST_ID_FILE = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/last_id.txt";

    private final Scanner scanner;

    public DeveloperCreate() {
        this.scanner = new Scanner(System.in);
    }

    private void saveLastIdToFile(int lastId) {
        try (BufferedWriter idWriter = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
            idWriter.write(String.valueOf(lastId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int generateNextId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_ID_FILE))) {
            String lastIdStr = reader.readLine();
            if (lastIdStr != null) {
                return Integer.parseInt(lastIdStr) + 1;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private void saveDevelopersToFile(Developer developer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true)))
        {
            writer.write(String.format("%d, %s, %s, %s", developer.getId(), developer.getName(), developer.getLastName(), developer.getProgrammingLanguage()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void findById () {
        System.out.print("Введите ID для поиска: ");
        int searchId = scanner.nextInt();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Предполагается, что строки разделены запятой и пробелом
                if (parts.length >= 3) { // Проверяем, что есть как минимум три элемента в массиве parts
                    int id = Integer.parseInt(parts[0]);
                    if (id == searchId) {
                        found = true;
                        System.out.println("Результат поиска:");
                        System.out.println("ID: " + id);
                        System.out.println("Имя: " + parts[1]);
                        System.out.println("Фамилия: " + parts[2]);
                        System.out.println("Дополнительные данные: " + (parts.length > 3 ? parts[3] : ""));
                        break; // Прерываем цикл, так как найдено совпадение
                    }
                } else {
                    System.err.println("Некорректный формат строки: " + line);
                }
            }
            if (!found) {
                System.out.println("По указанному ID ничего не найдено.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void createDeveloper() {
        Developer developer = new Developer();
        System.out.print("Введите имя : ");
        developer.setName(scanner.nextLine());
        System.out.print("Введите Фамилию : ");
        developer.setLastName(scanner.nextLine());
        System.out.print("Enter programming language: ");
        developer.setProgrammingLanguage(scanner.nextLine());
        developer.setId(generateNextId());
        if (developer.getId() >= 0) {
            saveDevelopersToFile(developer);
            saveLastIdToFile(developer.getId());
        }


    }

    @Override
    public void deleteById(Object o) {

    }
}

