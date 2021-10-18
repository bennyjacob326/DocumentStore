package se_practice.week2.registrar.registrar.query;

import se_practice.week2.registrar.registrar.model.CourseOffering;

import java.util.Set;

public class ProfessorsSearch extends Query {
    private String school;
    private String department;
    private String firstName;
    private String lastname;
    private Set<CourseOffering> coursesTaught;

    public ProfessorsSearch(int userID) {
        super(userID);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
