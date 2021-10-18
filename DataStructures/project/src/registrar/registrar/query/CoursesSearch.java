package se_practice.week2.registrar.registrar.query;

public class CoursesSearch extends Query {
    private String name;
    private String number;
    private String school;
    private String department;

    public CoursesSearch(int userID) {
        super(userID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}