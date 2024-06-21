package com.students.appstudents.Controller;

import com.students.appstudents.Exceptions.ErrorResponse;
import com.students.appstudents.Exceptions.NoSuchStudentExistsException;
import com.students.appstudents.Model.Student;
import com.students.appstudents.Service.StudentService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get Student by I'd
    @GetMapping("/get/{id}")
    public Student getCustomer(@PathVariable("id") Long id)
    {
        return studentService.getStudentById(id);
    }

    // Add new Student
    @PostMapping("/add")
    public ResponseEntity<String> saveStudent(@RequestBody @Valid Student student) {
        try {
            studentService.addStudent(student);
            return ResponseEntity.ok("Student added successfully");
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Get all Students
    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentService.getStudentList();
    }
    // Delete  Student by Id
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id){
        studentService.deleteStudentById(id);
        return "Student is deleted";
    }

    // Update Student`s details
    @PutMapping("/update")
    public String updateStudent(@RequestBody @Valid  Student student){

            return studentService.updateStudent(student);
    }
    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerAlreadyExistsException(NoSuchElementException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = "Validation failed for object='" + ex.getBindingResult().getObjectName() + "'. Error count: " + ex.getBindingResult().getErrorCount();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(value = NoSuchStudentExistsException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public @ResponseBody ErrorResponse handleException(NoSuchStudentExistsException ex) {
            return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        }
    }


}
