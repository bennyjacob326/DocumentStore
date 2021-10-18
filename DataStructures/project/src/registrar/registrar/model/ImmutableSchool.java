package se_practice.week2.registrar.registrar.model;

import java.util.Set;

public class ImmutableSchool extends School{
    private final School school;
    public ImmutableSchool(School school){
        super();
        this.school = school;
    }
    public boolean addCourseOffering(CourseOffering courseOffering){
        throw new UnauthorizedActionException();
    }
    public boolean removeCourseOffering(CourseOffering courseOffering){
        throw new UnauthorizedActionException();
    }
    public Set<CourseOffering> getCourseOfferings() {
        return this.school.getCourseOfferings();
    }

    public boolean addCoreRequirement(Course course){
        throw new UnauthorizedActionException();
    }
    public boolean removeCoreRequirement(Course course){
        throw new UnauthorizedActionException();
    }
    public Set<Course> getCoreRequirements() {
        return this.school.getCoreRequirements();
    }

    public boolean addCourse(Course course){
        throw new UnauthorizedActionException();
    }
    public boolean removeCourse(Course course){
        throw new UnauthorizedActionException();
    }
    public Set<Course> getCourses() {
        return this.school.getCourses();
    }
    public boolean addMajor(Major major){
        throw new UnauthorizedActionException();
    }
    public boolean removeMajor(Major major){
        throw new UnauthorizedActionException();
    }
    public Set<Major> getMajors() {
        return this.school.getMajors();
    }

    public boolean addDepartment(Department department){
        throw new UnauthorizedActionException();
    }
    public boolean removeDepartment(Department department){
        throw new UnauthorizedActionException();
    }
    public Set<Department> getDepartments() {
        return this.school.getDepartments();
    }

    public Employee getDean() {
        return this.school.getDean();
    }

    public void setDean(Employee dean) {
        throw new UnauthorizedActionException();
    }

    public String getName() {
        return this.school.getName();
    }

    public void setName(String name) {
        throw new UnauthorizedActionException();
    }
}