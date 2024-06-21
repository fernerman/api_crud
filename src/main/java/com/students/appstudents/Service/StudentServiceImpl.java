package com.students.appstudents.Service;

import com.students.appstudents.Exceptions.NoSuchStudentExistsException;

import com.students.appstudents.Model.Student;
import com.students.appstudents.Repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create operation
    @Override
    public void addStudent(Student student) {

            studentRepository.save(student);
    }


    @Override
    public Student saveStudent(Student student) {

        return studentRepository.save(student);

    }

    // Read operation
    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    // Method to get student by Id.Throws
    // NoSuchElementException for invalid Id
    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(
                ()
                        -> new NoSuchElementException(
                        "NO STUDENT PRESENT WITH ID = " + id));
    }

    // Delete operation
    @Override
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    // Update operation
    @Override
    public String updateStudent(Student student) {

        Student existingStudent = studentRepository.findById(student.getId()).orElse(null);

        if (existingStudent == null) {
            throw new NoSuchStudentExistsException("No Such Student exists!");
        } else {
            existingStudent.setName(student.getName());
            existingStudent.setAddress(student.getAddress());
            studentRepository.save(existingStudent);
            return "Record updated Successfully";

        }
    }
}



