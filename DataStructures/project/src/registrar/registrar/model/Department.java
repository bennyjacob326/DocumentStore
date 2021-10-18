package se_practice.week2.registrar.registrar.model;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private String name;
    private School school;
    private Set<Course> courses;
    private Set<Professor> professors;

    protected Department(){
    }
    public Department(String  name, School school) {
        this.school = school;
        this.name = name;
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

    public boolean addProfessor(Professor professor){
        if(this.professors == null){
            this.professors = new HashSet<>();
        }
        return this.professors.add(professor);
    }
    public boolean removeProfessor(Professor professor){
        if(this.professors == null) {
            return false;
        }
        return this.professors.remove(professor);
    }
    public Set<Professor> getProfessors() {
        HashSet<Professor> professors =  new HashSet<>();
        if(this.professors != null) {
            professors.addAll(this.professors);
        }
        return professors;
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