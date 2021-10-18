package se_practice.week2.registrar.registrar.model;

import java.util.Set;

public class ImmutableCourseOffering extends CourseOffering {
    private final CourseOffering offering;
    public ImmutableCourseOffering(CourseOffering offering){
        super();
        this.offering = offering;
    }

    public Professor getProfessor() {
        return this.offering.getProfessor();
    }

    public void setProfessor(Professor professor) {
        throw new UnauthorizedActionException();
    }

    public boolean addStudent(Student student){
        throw new UnauthorizedActionException();
    }

    public boolean removeStudent(Student student){
        throw new UnauthorizedActionException();
    }

    public Set<Student> getStudents() {
        return this.offering.getStudents();
    }
    public Course getCourse() {
        return this.offering.getCourse();
    }

    public void setCourse(Course course) {
        throw new UnauthorizedActionException();
    }

    public int getYear() {
        return this.offering.getYear();
    }

    public void setYear(int year) {
        throw new UnauthorizedActionException();
    }

    public Semester getSemester() {
        return this.offering.getSemester();
    }

    public void setSemester(Semester semester) {
        throw new UnauthorizedActionException();
    }
}