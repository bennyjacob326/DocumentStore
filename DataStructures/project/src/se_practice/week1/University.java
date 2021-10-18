package se_practice.week1;

import java.util.*;

public class University {
    private String name;
    private HashSet<Classes> classesWeTeach = new HashSet<>();
    private HashSet<Classes> courseCatalog = new HashSet<>();
    private HashSet<Professor> professors = new HashSet<>();
    private HashSet<Student> students = new HashSet<>();

    public University(String name) {
        this.name = name;
    }

    public Set<Classes> getCourseCatalog() {
        return this.courseCatalog;//figure out when to add a class to the course catelog
    }

    public void addClass(Classes clas) {
        this.classesWeTeach.add(clas);
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student s) {
        this.students.add(s);
    }

    public Set<Professor> getProfessors() {
        return this.professors;
    }

    public void addProfessor(Professor p){
        professors.add(p);
        classesWeTeach.addAll(p.getClasses());
    }
}
