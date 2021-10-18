package se_practice.week2.registrar.registrar.model;

import java.util.HashSet;
import java.util.Set;

public class School {
    private String name;
    private Employee dean;
    private Set<Department> departments;
    private Set<Major> majors;
    private Set<Course> courses;
    private Set<Course> coreRequirements;
    private Set<CourseOffering> courseOfferings;

    protected School(){
    }
    public School(String name) {
        this.name = name;
    }

    public boolean addCourseOffering(CourseOffering courseOffering){
        if(this.courseOfferings == null){
            this.courseOfferings = new HashSet<>();
        }
        return this.courseOfferings.add(courseOffering);
    }
    public boolean removeCourseOffering(CourseOffering courseOffering){
        if(this.courseOfferings == null){
            return false;
        }
        return this.courseOfferings.remove(courseOffering);
    }
    public Set<CourseOffering> getCourseOfferings() {
        HashSet<CourseOffering> courseOfferings =  new HashSet<>();
        if(this.courseOfferings != null){
            courseOfferings.addAll(this.courseOfferings);
        }
        return courseOfferings;
    }

    public boolean addCoreRequirement(Course course){
        if(this.coreRequirements == null){
            this.coreRequirements = new HashSet<>();
        }
        return this.coreRequirements.add(course);
    }
    public boolean removeCoreRequirement(Course course){
        if(this.coreRequirements == null){
            return false;
        }
        return this.coreRequirements.remove(course);
    }
    public Set<Course> getCoreRequirements() {
        HashSet<Course> coreRequirements =  new HashSet<>();
        if(this.coreRequirements!=null){
            coreRequirements.addAll(this.coreRequirements);
        }
        return coreRequirements;
    }

    public boolean addCourse(Course course){
        if(this.courses == null){
            this.courses = new HashSet<>();
        }
        return this.courses.add(course);
    }
    public boolean removeCourse(Course course){
        if(this.courses == null){
            return false;
        }
        return this.courses.remove(course);
    }
    public Set<Course> getCourses() {
        HashSet<Course> courses =  new HashSet<>();
        if(this.courses != null) {
            courses.addAll(this.courses);
        }
        return courses;
    }
    public boolean addMajor(Major major){
        if(this.majors == null){
            this.majors = new HashSet<>();
        }
        return this.majors.add(major);
    }
    public boolean removeMajor(Major major){
        if(this.majors==null){
            return false;
        }
        return this.majors.remove(major);
    }
    public Set<Major> getMajors() {
        HashSet<Major> majors =  new HashSet<>();
        if(this.majors!=null) {
            majors.addAll(this.majors);
        }
        return majors;
    }

    public boolean addDepartment(Department department){
        if(this.departments == null){
            this.departments = new HashSet<>();
        }
        return this.departments.add(department);
    }
    public boolean removeDepartment(Department department){
        if(this.departments == null){
            return false;
        }
        return this.departments.remove(department);
    }
    public Set<Department> getDepartments() {
        HashSet<Department> departments =  new HashSet<>();
        if(this.departments != null) {
            departments.addAll(this.departments);
        }
        return departments;
    }

    public Employee getDean() {
        return dean;
    }

    public void setDean(Employee dean) {
        this.dean = dean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}