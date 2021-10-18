package se_practice.week1;

public class Registar extends User{

    public Registar(int bannerID, String password){
        this.setBannerID(bannerID);
        this.setPassword(password);
        this.setSchool(School.ALL);
    }
    public boolean addClassToCatelog(Classes clas){
        //check if it's in the classes that we can teach
        //then add the class to the catelog.
        return false;
    }
    //make a default max for all classes?
    //
}
