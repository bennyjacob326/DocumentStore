package se_practice.week2.registrar.registrar.model;

public class Employee extends User{
    public enum Role{PROFESSOR,DEAN,REGISTRAR};
    private Role role;
    private School school;

    public Employee(){
    }
    public Employee(String firstName, String lastName, int id, Role role, School school) {
        super(firstName, lastName, id);
        this.role = role;
        this.school = school;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
