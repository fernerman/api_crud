package com.students.appstudents.Service;

import com.students.appstudents.Model.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public void  addStudent(Student student);
    public List<Student> getStudentList();
    public Student getStudentById(long id);
    public void deleteStudentById(long id);
    public String updateStudent(Student student);


}
