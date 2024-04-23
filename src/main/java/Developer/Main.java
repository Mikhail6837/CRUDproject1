package Developer;

public class Main {
    public static void main(String[] args) {
        DeveloperCreate developerDAO = new DeveloperCreate();
        developerDAO.createDeveloper();
        ReadDeveloper read = new ReadDeveloper();
        read.getAllDevelopers();
    }}

