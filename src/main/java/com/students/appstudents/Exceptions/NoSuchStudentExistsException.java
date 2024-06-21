package com.students.appstudents.Exceptions;

public class NoSuchStudentExistsException extends RuntimeException {
    public NoSuchStudentExistsException() {}

    public NoSuchStudentExistsException(String message) {
        super(message);
    }
}
