package se_practice.week2.registrar.registrar.query;

public class SchoolsSearch extends Query {
    private String name;
    private String deanLastName;
    private String deanFirstName;

    public SchoolsSearch(int userID) {
        super(userID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeanLastName() {
        return deanLastName;
    }

    public void setDeanLastName(String deanLastName) {
        this.deanLastName = deanLastName;
    }

    public String getDeanFirstName() {
        return deanFirstName;
    }

    public void setDeanFirstName(String deanFirstName) {
        this.deanFirstName = deanFirstName;
    }
}
