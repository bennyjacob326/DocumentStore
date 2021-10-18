package se_practice.week2.registrar.registrar.model;

import java.util.HashSet;
import java.util.Set;

public class Major {
    private String name;
    private School school;
    private Set<Course> requiredCourses;

    protected Major(){
    }

    public Major(String name, School school, Set<Course> requiredCourses) {
        this.name = name;
        this.school = school;
        this.requiredCourses = requiredCourses;
    }

    public Major(String name, School school) {
        this(name,school, null);
    }

    public boolean addRequiredCourse(Course co){
        if(this.requiredCourses == null){
            this.requiredCourses = new HashSet<>();
        }
        return this.requiredCourses.add(co);
    }

    public boolean removeRequiredCourse(Course co){
        if(this.requiredCourses == null) {
            return false;
        }
        return this.requiredCourses.remove(co);
    }

    public Set<Course> getRequiredCourses() {
        HashSet<Course> courses =  new HashSet<>();
        if(this.requiredCourses != null) {
            courses.addAll(this.requiredCourses);
        }
        return courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
