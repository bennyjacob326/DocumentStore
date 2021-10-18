package se_practice.week2.registrar.registrar.model;

import java.util.GregorianCalendar;
import java.util.Set;

public class ImmutableStudent extends Student{
    private final Student student;
    public ImmutableStudent(Student student){
        this.student = student;
    }

    public boolean addCoursesTaken(CourseOffering courseOffering){
        throw new UnauthorizedActionException();
    }
    public boolean removeCoursesTaken(CourseOffering courseOffering){
        throw new UnauthorizedActionException();
    }
    public Set<CourseOffering> getCoursesTaken() {
        return this.student.getCoursesTaken();
    }

    public Major getMajor() {
        return this.student.getMajor();
    }

    public void setMajor(Major major) {
        throw new UnauthorizedActionException();
    }

    public School getSchool() {
        return this.student.getSchool();
    }

    public void setSchool(School school) {
        throw new UnauthorizedActionException();
    }

    public GregorianCalendar getGraduationDate() {
        return this.student.getGraduationDate();
    }

    public void setGraduationDate(GregorianCalendar graduationDate) {
        throw new UnauthorizedActionException();
    }

    public void setGrade(CourseOffering course, double grade){
        throw new UnauthorizedActionException();
    }

    public double getGrade(CourseOffering course){
        return super.getGrade(course);
    }

    public String getFirstName() {
        return this.student.getFirstName();
    }

    public void setFirstName(String firstName) {
        throw new UnauthorizedActionException();
    }

    public String getLastName() {
        return this.student.getLastName();
    }

    public void setLastName(String lastName) {
        throw new UnauthorizedActionException();
    }

    public int getId() {
        return this.student.getId();
    }

    public void setId(int id) {
        throw new UnauthorizedActionException();
    }
}