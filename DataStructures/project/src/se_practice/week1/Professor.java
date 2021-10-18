package se_practice.week1;

import java.util.*;

public class Professor extends User{
    private ArrayList<Classes> classes = new ArrayList<>();
    public Professor(String firstName, String middleName, String lastName, int bannerID, String password){
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setBannerID(bannerID);
        this.setPassword(password);
    }
    public void addClass(Classes clas){
        this.classes.add(clas);
    }
    public List getClasses(){
        return this.classes;
    }
    public void changeClassSize(Classes clas, int newSize){
        if(classes.contains(clas)) {
            clas.setClassSize(newSize);
        }
        //throw an exception?
    }
    public int getClassSize(Classes clas){
        if(classes.contains(clas)) {
            return clas.getClassSize();
        }
        return 0;
    }

    //check if a professor offers a certain course
}
