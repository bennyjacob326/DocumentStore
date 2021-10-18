package se_practice.week1;

public abstract class User{
    private String firstName;
    private String middleName;
    private String lastName;
    private int bannerID;
    private String password;
    private School school;
    enum School{
        SYMS, YC, ALL
    }
    public School getSchool(){
        return this.school;
    }
    public void setSchool(School s){
        this.school = s;
    }

    public String setFirstName(String name){
        String lastVal = this.firstName;
        this.firstName = name;
        return lastVal;
    }

    public String setMiddleName(String name){
        String lastVal = this.firstName;
        this.middleName = name;
        return lastVal;
    }

    public String setLastName(String name){
        String lastVal = this.firstName;
        this.lastName = name;
        return lastVal;
    }

    public String getFullName(){
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getMiddleName(){
        return this.middleName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public int getbannerID(){
        return this.bannerID;
    }

    public int setBannerID(int bannerID){
        int banner = this.bannerID;
        this.bannerID = bannerID;
        return banner;//returns the old one
    }
    protected String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
