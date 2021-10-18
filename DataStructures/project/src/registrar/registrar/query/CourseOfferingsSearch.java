package se_practice.week2.registrar.registrar.query;

import se_practice.week2.registrar.registrar.model.CourseOffering;

public class CourseOfferingsSearch extends CoursesSearch {
    private CourseOffering.Semester semester;
    private int year;
    private String professorFirstName;
    private String professorLastName;
    private int professorID;
    private String school;
    private String courseName;
    private String department;

    public CourseOfferingsSearch(int userID) {
        super(userID);
        this.year = -1;
        this.professorID = -1;
    }

    public CourseOffering.Semester getSemester() {
        return semester;
    }

    public void setSemester(CourseOffering.Semester semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProfessorFirstName() {
        return professorFirstName;
    }

    public void setProfessorFirstName(String professorFirstName) {
        this.professorFirstName = professorFirstName;
    }

    public String getProfessorLastName() {
        return professorLastName;
    }

    public void setProfessorLastName(String professorLastName) {
        this.professorLastName = professorLastName;
    }

    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }

    @Override
    public String getSchool() {
        return school;
    }

    @Override
    public void setSchool(String school) {
        this.school = school;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }
}
