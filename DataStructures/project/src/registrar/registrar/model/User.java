package se_practice.week2.registrar.registrar.model;

/**
 * User classes model how the registration systems views the users and all the data about them
 */
public class User {
    private String firstName;
    private String lastName;
    private int id;

    protected User(){
    }
    public User(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
