package se_practice.week1;

import java.util.ArrayList;
import java.util.HashMap;

public class Classes {
    enum Days{//need to use this for the different days that the class meets
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }
    private String name;
    private String dates;
    private int credits;
    private int classSize;
    private ArrayList<Student> studentsInClass = new ArrayList<>();
    private ArrayList<Student> waitList = new ArrayList<>();
    public int waitListMax = 5;
    private Classes prereq;
    private Professor professor;
    private HashMap<Student, Integer> grades = new HashMap<>();
    //create a hashmap of students and
    private Classes(String name, int credits, Professor professor){
        this(name, credits, null, professor);
    }
    private Classes(String name, int credits, Classes prereq, Professor professor){
        this.name = name;
        this.credits = credits;
        this.prereq = prereq;
        this.professor = professor;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getClassSize(){
        return this.classSize;
    }
    public void setClassSize(int classSize){
        this.classSize = classSize;
    }
    public void setProfessor(Professor p){
        this.professor = p;
    }
    public Professor getProfessor(Professor p){
        return this.professor;
    }
    public void setCredits(int credits){
        this.credits = credits;
    }
    public int getCredits(){
        return this.credits;
    }
    public String StudentToClass(Student student){
        //check for prereqs
        if(studentsInClass.size() == classSize){
            if(this.waitListMax < this.waitList.size()){

                waitList.add(student);
            }else{
                //throw an error
            }
        }else{

            studentsInClass.add(student);
        }
        return " ";
    }
    public int setGrade(Student s, int grade){//what about when his grade is incomplete?
        if(studentsInClass.contains(s)) {
            int old = grades.put(s, grade);
            if(old != 0){
                return old;
            }else{
                return 1;
            }
        }else{
            return 0;
        }

    }

}
