package se_practice.week2.registrar;
import se_practice.week2.registrar.registrar.model.*;
import se_practice.week2.registrar.registrar.query.QuerySearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class QuerySearcherTest {
    private School school;//assume that I inputted info into this school
    private Set<Course> courses = new HashSet<>();
    private Set<Professor> professors = new HashSet<>();
    private Set<Student> students = new HashSet<>();
    private Major major1, major2, major3;
    private Course introCS;
    private Professor prof1, prof2, prof3;
    private Student student1, student2, student3;
    private Employee dean, registrar;
    private Department department = new Department("Science", school);
    private Set<Major> majors;
    private QuerySearcher studentSearcher;
    private QuerySearcher teacherSearcher;
    private QuerySearcher deanSearcher;
    private QuerySearcher registrarSearcher;

    @BeforeEach
    public void setAll(){
        introCS = new Course("Intro to CS", department, 40);
        school = new School("YC");
        department = new Department("Science", school);
        courses = new HashSet<>();
        courses.add(new Course("Bio 1", department, 30));
        courses.add(new Course("Chem 1", department, 23));
        courses.add(new Course("Chem 2", department, 35));
        major1 = new Major("Math", school, courses);
        major2 = new Major("Pre-Med", school, courses);
        major3 = new Major("Engineering", school, courses);
        majors.add(major1);
        majors.add(major2);
        majors.add(major3);
        professors.add(new Professor("Joe", "DiMaggio", 7654, department));
        professors.add(new Professor("Pete", "Alonso", 6745, department));
        student1 = new Student("Bob", "Gibson",13475);
        student2 = new Student("Bobby", "Bollina",13489);
        students.add(student1);
        students.add(student2);
        dean = new Employee("Babe", "Ruth", 55555, Employee.Role.DEAN, school);
        registrar = new Employee("Rob", "Manfred", 66666, Employee.Role.REGISTRAR, school);

        studentSearcher = new QuerySearcher(13475);
        teacherSearcher = new QuerySearcher(7654);
        deanSearcher = new QuerySearcher(55555);
        registrarSearcher = new QuerySearcher(66666);
    }

    @Test
    public void NullRetunrs(){
        assertNull(studentSearcher.getStudents(school, majors, professors));
        assertNull(teacherSearcher.getPrereqs(major1));
        assertNull(deanSearcher.getPrereqs(major2));
        assertNull(registrarSearcher.getPrereqs(major2));
    }

    @Test
    public void ThrowUnauthorizedAction(){
        try {
            for (Student s : teacherSearcher.getStudents(school, majors, professors)) {
                s.setMajor(major2);
            }
            fail("teacher doesn't have the ability to change a student's major!");
        }catch(UnauthorizedActionException e) {

        }
        try {
            for (Professor p : studentSearcher.checkProfessors(school, majors, professors, courses)) {
                p.setDepartment(department);
            }
            fail("a student can't change anything about a professor! ");
        }catch(UnauthorizedActionException e) {

        }
        try {
            for(Course c: teacherSearcher.checkClassesInPlace(school, majors, professors)){
                c.addPrerequisite(introCS);
            }
            fail("A teacher can't change the course's prereqs!");
        }catch(UnauthorizedActionException e){

        }
    }

    @Test
    public void CorrectTypeReturned(){
        try {
            for (Professor p : registrarSearcher.checkProfessors(school, majors, professors, courses)) {
                p.setDepartment(department);
            }

        }catch(UnauthorizedActionException e) {
            fail("the registrar should be able to change which department the professor is in.");
        }
        try {
            for(Course c: deanSearcher.checkClassesInPlace(school, majors, professors)){
                c.addPrerequisite(introCS);
            }
        }catch(UnauthorizedActionException e){
            fail("A dean can change the course's prereqs!");
        }
    }


}
