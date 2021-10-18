package se_practice.week2.registrar.registrar.model;

import java.util.Set;

public class ImmutableMajor extends Major {
    private final Major major;
    public ImmutableMajor(Major major) {
        super();
        this.major = major;
    }

    public boolean addRequiredCourse(Course co){
        throw new UnauthorizedActionException();
    }

    public boolean removeRequiredCourse(Course co){
        throw new UnauthorizedActionException();
    }
    public Set<Course> getRequiredCourses() {
        return this.major.getRequiredCourses();
    }

    public String getName() {
        return this.major.getName();
    }

    public void setName(String name) {
        throw new UnauthorizedActionException();

    }

    public School getSchool() {
        return this.major.getSchool();
    }

    public void setSchool(School school) {
        throw new UnauthorizedActionException();

    }
}