package se_practice.week2.registrar.registrar.model;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private String name;
    private Department department;
    private int number;
    private Set<Course> prerequisites;

    protected Course(){
    }
    public Course(String name, Department department, int number) {
        this(name,department,number, null);
    }

    public Course(String name, Department department, int number, Set<Course> prerequisites) {
        this.name = name;
        this.department = department;
        this.number = number;
        this.prerequisites = prerequisites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return getDepartment().getSchool();
    }

    public boolean addPrerequisite(Course prereq){
        if(this.prerequisites == null){
            this.prerequisites = new HashSet<>();
        }
        return this.prerequisites.add(prereq);
    }

    public boolean removePrerequisite(Course prereq){
        if(this.prerequisites == null){
            return false;
        }
        return this.prerequisites.remove(prereq);
    }

    public Set<Course> getPrerequisites() {
        HashSet<Course> prereqs =  new HashSet<>();
        if(this.prerequisites != null) {
            prereqs.addAll(this.prerequisites);
        }
        return prereqs;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
