package se_practice.week2.registrar.registrar.model;

import java.util.*;

public class Student extends User{
    private Major major;
    private School school;
    private Set<CourseOffering> coursesTaken;
    private GregorianCalendar graduationDate;
    private Map<CourseOffering,Double> grades;
    protected Student(){
    }
    public Student(String firstName, String lastName, int id) {
        super(firstName, lastName, id);
    }

    public boolean addCoursesTaken(CourseOffering courseOffering){
        if(this.coursesTaken == null){
            this.coursesTaken = new HashSet<>();
        }
        return this.coursesTaken.add(courseOffering);
    }
    public boolean removeCoursesTaken(CourseOffering courseOffering){
        if(this.coursesTaken == null){
            return false;
        }
        return this.coursesTaken.remove(courseOffering);
    }
    public Set<CourseOffering> getCoursesTaken() {
        HashSet<CourseOffering> coursesTaken =  new HashSet<>();
        if(this.coursesTaken != null){
            coursesTaken.addAll(this.coursesTaken);
        }
        return coursesTaken;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public GregorianCalendar getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(GregorianCalendar graduationDate) {
        this.graduationDate = graduationDate;
    }

    public void setGrade(CourseOffering course, double grade){
        if(this.grades == null){
            this.grades = new HashMap<>();
        }
        this.grades.put(course,grade);
    }

    /**
     *
     * @param course
     * @return -1 if the student doesn't have a grade in the course
     * @throws IllegalStateException if the student did not take that course
     */
    public double getGrade(CourseOffering course){
        if(this.grades == null || !this.grades.containsKey(course)){
            throw new IllegalStateException("student " + getId() + "did not take that course" + course.getCourse().getNumber() + ", " + course.getSemester() + ", " + course.getYear());
        }
        Double grade = this.grades.get(course);
        if(grade == null){
            return -1;
        }
        return grade;
    }
}