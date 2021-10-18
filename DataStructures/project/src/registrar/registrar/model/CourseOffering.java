package se_practice.week2.registrar.registrar.model;

import java.util.HashSet;
import java.util.Set;

public class CourseOffering {
    public enum Semester{SPRING,SUMMER,FALL};
    private Course course;
    private int year;
    private Semester semester;
    private Professor professor;
    private Set<Student> students;

    protected CourseOffering(){
    }
    public CourseOffering(Course course, int year, Semester semester, Professor professor){
        this(course,year,semester);
        this.professor = professor;
    }

    public CourseOffering(Course course, int year, Semester semester) {
        this.course = course;
        this.year = year;
        this.semester = semester;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean addStudent(Student student){
        if(this.students == null){
            this.students = new HashSet<>();
        }
        return this.students.add(student);
    }

    public boolean removeStudent(Student student){
        if(this.students == null) {
            return false;
        }
        return this.students.remove(student);
    }

    public Set<Student> getStudents() {
        HashSet<Student> studs =  new HashSet<>();
        if(this.students != null) {
            studs.addAll(this.students);
        }
        return studs;
    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}