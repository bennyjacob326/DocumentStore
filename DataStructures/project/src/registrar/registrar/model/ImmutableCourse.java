package se_practice.week2.registrar.registrar.model;

import java.util.Set;

public class ImmutableCourse extends Course {
    private final Course course;
    public ImmutableCourse(Course course){
        super();
        this.course = course;
    }
    public void setName(String name) {
        throw new UnauthorizedActionException();
    }
    public boolean addPrerequisite(Course prereq){
        throw new UnauthorizedActionException();
    }
    public boolean removePrerequisite(Course prereq){
        throw new UnauthorizedActionException();
    }
    public void setDepartment(Department department) {
        throw new UnauthorizedActionException();
    }
    public void setNumber(int number) {
        throw new UnauthorizedActionException();
    }

    public String getName() {
        return this.course.getName();
    }
    public School getSchool() {
        return getDepartment().getSchool();
    }
    public Set<Course> getPrerequisites() {
        return this.course.getPrerequisites();
    }

    public Department getDepartment() {
        return this.course.getDepartment();
    }
    public int getNumber() {
        return this.course.getNumber();
    }

}
