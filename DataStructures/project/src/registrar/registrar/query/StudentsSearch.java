package se_practice.week2.registrar.registrar.query;

import se_practice.week2.registrar.registrar.model.CourseOffering;

import java.util.GregorianCalendar;
import java.util.Set;

public class StudentsSearch extends Query {
    private String firstName;
    private String lastname;
    private String major;
    private Set<CourseOffering> coursesTaken;
    private GregorianCalendar graduationDate;

    public StudentsSearch(int userID) {
        super(userID);
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Set<CourseOffering> getCoursesTaken() {
        return coursesTaken;
    }

    public void setCoursesTaken(Set<CourseOffering> coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

    public GregorianCalendar getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(GregorianCalendar graduationDate) {
        this.graduationDate = graduationDate;
    }
}
