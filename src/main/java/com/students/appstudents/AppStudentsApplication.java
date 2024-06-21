package com.students.appstudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
        (
                scanBasePackages = "com.students.appstudents",
                scanBasePackageClasses = {AppStudentsApplication.class}

        )
public class AppStudentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppStudentsApplication.class, args);
    }

}
