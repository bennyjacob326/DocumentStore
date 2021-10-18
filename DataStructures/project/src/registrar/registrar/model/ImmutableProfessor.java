package se_practice.week2.registrar.registrar.model;

import java.util.Set;

public class ImmutableProfessor extends Professor {
    private final Professor professor;
    public ImmutableProfessor(Professor professor){
        super();
        this.professor = professor;
    }
    public Department getDepartment() {
        return this.professor.getDepartment();
    }

    public void setDepartment(Department department) {
        throw new UnauthorizedActionException();
    }

    public boolean addCourseTaught(CourseOffering co){
        throw new UnauthorizedActionException();
    }
    public boolean removeCourseTaught(CourseOffering co){
        throw new UnauthorizedActionException();
    }

    public Set<CourseOffering> getCoursesTaught() {
        return this.professor.getCoursesTaught();
    }
    public Role getRole() {
        return this.professor.getRole();
    }

    public void setRole(Role role) {
        throw new UnauthorizedActionException();
    }

    public School getSchool() {
        return this.professor.getSchool();
    }
    public void setSchool(School school) {
        throw new UnauthorizedActionException();
    }

    public String getFirstName() {
        return this.professor.getFirstName();
    }

    public void setFirstName(String firstName) {
        throw new UnauthorizedActionException();
    }

    public String getLastName() {
        return this.professor.getLastName();
    }

    public void setLastName(String lastName) {
        throw new UnauthorizedActionException();
    }

    public int getId() {
        return this.professor.getId();
    }

    public void setId(int id) {
        throw new UnauthorizedActionException();
    }
}