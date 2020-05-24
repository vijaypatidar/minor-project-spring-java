package com.svceindore.minor.rest;

import com.svceindore.minor.model.Student;
import com.svceindore.minor.repositories.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email) {
        return studentRepository.findByEmail(email);
    }
}
