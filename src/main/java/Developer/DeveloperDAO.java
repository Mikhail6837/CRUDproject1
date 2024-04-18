package Developer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeveloperDAO {
    private static final String FILENAME = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/Developer.txt";
    private static final String LAST_ID_FILE = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/last_id.txt";

    private final Scanner scanner;

    public DeveloperDAO() {
        this.scanner = new Scanner(System.in);
    }

    public void createDeveloper() {

        Developer developer = new Developer();
        System.out.print("Enter developer name: ");
        developer.setName(scanner.nextLine());
        System.out.print("Enter programming language: ");
        developer.setProgrammingLanguage(scanner.nextLine());
        developer.setId(generateNextId());
        if (developer.getId() >= 0) {
            saveDevelopersToFile(developer);
            saveLastIdToFile(developer.getId());
        }
    }

    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Предполагается, что строки разделены запятой и пробелом
                if (parts.length >= 2) { // Проверяем, что есть как минимум два элемента в массиве parts
                    Developer developer = new Developer();
                    developer.setName(parts[0]);
                    developer.setProgrammingLanguage(parts[1]);
                    developers.add(developer);
                } else {
                    System.err.println("Некорректный формат строки: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
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


    // return (int) (Math.random() * 900000) + 100000;


    private void saveDevelopersToFile(Developer developer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true));
             BufferedWriter idWriter = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
                writer.write(String.format("%06d, %s, %s", developer.getId(), developer.getName(), developer.getProgrammingLanguage()));
                writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

       }
        }

