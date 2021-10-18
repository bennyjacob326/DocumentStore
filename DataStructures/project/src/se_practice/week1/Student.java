package se_practice.week1;

import java.util.*;

public class Student extends User{
    HashSet<Classes> previousClasses = new HashSet<>();
    HashSet<Classes> classes = new HashSet<>();
    HashMap<Classes, Integer> grades = new HashMap<>();
    public Student(String firstName, String middleName, String lastName, int bannerID, String password, School school){
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setBannerID(bannerID);
        this.setPassword(password);
        this.setSchool(school);
    }
    public Set<Classes> getClasses(){
        return this.classes;
    }
    public void addClass(Classes clas){
        //check his elibility, fulfilled prereqs, etc.

        this.classes.add(clas);
    }
    public int getGrade(Classes clas){
        //have to change grades so that it actually interats with the correct hashMap in Classes.
        return grades.get(clas);

    }
    public boolean dropClass(Classes clas){
        //check if it's contained in his classes, check the current date or if it's too late
        return false;//only if he cannot drop the class; display different messages for why he cannot
    }
    //something to move the classes to the previous classes, check if he has a prereq, etc.




}