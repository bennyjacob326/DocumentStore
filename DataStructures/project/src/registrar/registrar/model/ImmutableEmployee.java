package se_practice.week2.registrar.registrar.model;

public class ImmutableEmployee extends Employee{
    private final Employee employee;
    public ImmutableEmployee(Employee employee){
        super();
        this.employee = employee;
    }

    public Role getRole() {
        return this.employee.getRole();
    }

    public void setRole(Role role) {
        throw new UnauthorizedActionException();
    }

    public School getSchool() {
        return this.employee.getSchool();
    }
    public void setSchool(School school) {
        throw new UnauthorizedActionException();
    }

    public String getFirstName() {
        return this.employee.getFirstName();
    }

    public void setFirstName(String firstName) {
        throw new UnauthorizedActionException();
    }

    public String getLastName() {
        return this.employee.getLastName();
    }

    public void setLastName(String lastName) {
        throw new UnauthorizedActionException();
    }

    public int getId() {
        return this.employee.getId();
    }

    public void setId(int id) {
        throw new UnauthorizedActionException();
    }
}