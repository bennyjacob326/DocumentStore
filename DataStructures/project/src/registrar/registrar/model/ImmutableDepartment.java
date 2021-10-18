package se_practice.week2.registrar.registrar.model;

import java.util.Set;

public class ImmutableDepartment extends Department {
    private final Department department;
    public ImmutableDepartment(Department department){
        super();
        this.department = department;
    }
    public boolean addCourse(Course course){
        throw new UnauthorizedActionException();
    }
    public boolean removeCourse(Course course){
        throw new UnauthorizedActionException();
    }
    public Set<Course> getCourses() {
        return this.department.getCourses();
    }
    public boolean addProfessor(Professor professor){
        throw new UnauthorizedActionException();
    }
    public boolean removeProfessor(Professor professor){
        throw new UnauthorizedActionException();
    }
    public Set<Professor> getProfessors() {
        return this.department.getProfessors();
    }

    public String getName() {
        return this.department.getName();
    }
    public void setName(String name) {
        throw new UnauthorizedActionException();
    }

    public School getSchool() {
        return this.department.getSchool();
    }
    public void setSchool(School school) {
        throw new UnauthorizedActionException();
    }
}