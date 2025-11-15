package com.ManyToMany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ManyToMany.requests.Student;
import com.ManyToMany.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping
    public Student createStudent(@RequestBody Student s) {
        return service.save(s);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return service.get(id);
    }
}
