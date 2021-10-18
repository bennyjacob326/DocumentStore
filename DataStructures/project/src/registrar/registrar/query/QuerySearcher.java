package se_practice.week2.registrar.registrar.query;

import se_practice.week2.registrar.registrar.model.*;

import java.util.HashSet;
import java.util.Set;

public class QuerySearcher {
    //to help check for if the given user has the ability to change a certain thing.

    public QuerySearcher(int userID){

    }

    public Set<Major> checkMajors(School School){
        return  new HashSet<>();//just to make sure it passes
    }

    //returns null any time a student is in the query
    public Set<Course> checkClassesInPlace(School school, Set<Major> m, Set<Professor> p){
        return  new HashSet<>();
    }

    //returns null any time a student is in the query
    public Set<Professor> checkProfessors(School school, Set<Major> m, Set<Professor> p, Set<Course> classes){
        return  new HashSet<>();
    }

    //returns null for any student who attempts to get this info
    public Set<Student> getStudents(School school, Set<Major> m, Set<Professor> p){
        return  new HashSet<>();
    }

    public Set<Course> getPrereqs(Major m){
        return new HashSet<>();
    }


}
