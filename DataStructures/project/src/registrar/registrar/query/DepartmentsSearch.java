package se_practice.week2.registrar.registrar.query;

public class DepartmentsSearch extends Query {
    private String name;
    private String school;
    public DepartmentsSearch(int userID) {
        super(userID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
