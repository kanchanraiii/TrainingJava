package com.student.controller;

import com.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
	private StudentService studentService;

    // Add one student
    @PostMapping("/add")
    public String addOne(@RequestBody String json) {
        return studentService.addOne(json);
    }


    // Get all students
    @GetMapping("/getAll")
    public List<Document> getAll() {
        return studentService.getAll();
    }

    // Upload a JSON file with an array of objects
    @PostMapping("/upload")
    public String uploadJson(@RequestParam("file") MultipartFile file) {
        int count = studentService.uploadJsonFile(file);
        return count + " documents inserted successfully!";
    }
}
