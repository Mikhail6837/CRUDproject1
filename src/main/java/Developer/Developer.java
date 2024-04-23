package Developer;
import java.io.Serializable;

public class Developer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String lastname;
    private String programmingLanguage;

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
