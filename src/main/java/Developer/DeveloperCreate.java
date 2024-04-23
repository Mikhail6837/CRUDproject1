package Developer;
import java.io.*;
import java.util.Scanner;


public class DeveloperCreate {
    private static final String FILENAME = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/Developer.txt";
    private static final String LAST_ID_FILE = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/last_id.txt";

    private final Scanner scanner;

    public DeveloperCreate() {
        this.scanner = new Scanner(System.in);
    }

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
         //  BufferedWriter idWriter = new BufferedWriter(new FileWriter(LAST_ID_FILE)))
        {
                writer.write(String.format("%d, %s, %s, %s", developer.getId(), developer.getName(), developer.getLastName(),  developer.getProgrammingLanguage()));
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

