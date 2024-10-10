package service;

import model.Student;

public interface StudentService {

    public void create( );
    public void update( );
    public void delete( );
    public void printAllStudents();
    public Student readFile();
}
