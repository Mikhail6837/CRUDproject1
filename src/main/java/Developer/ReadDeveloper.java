package Developer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadDeveloper {
    private static final String FILENAME = "/Users/mikhail/IdeaProjects/CRUDproject/src/main/java/Developer/Developer.txt";
    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); // Предполагается, что строки разделены запятой и пробелом
                if (parts.length >= 3) { // Проверяем, что есть как минимум два элемента в массиве parts
                    Developer developer = new Developer();
                    developer.setId(Integer.parseInt(parts[0]));
                    developer.setName(parts[1]);
                    developer.setLastName(parts[2]);
                    developers.add(developer);
                  //  System.out.println(developers);
                } else {
                    System.err.println("Некорректный формат строки: " + line);
                }
            } for (Developer developer : developers) {
                System.out.println(developer.getId() + ", " +  developer.getName() + ", " + developer.getLastName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
    }
}
