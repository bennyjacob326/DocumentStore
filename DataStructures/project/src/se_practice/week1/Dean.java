package se_practice.week1;

public class Dean extends User{
    School school;

    public Dean(String firstName, String middleName, String lastName, int bannerID, String password, School s){
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setBannerID(bannerID);
        this.setPassword(password);
        this.setSchool(s);//check if the person is in YC or SYMS
    }
    public boolean addClassToCatelog(Classes clas){
        //make sure that he has the power to add a class, based on the ENUM
        return false;
    }
}
