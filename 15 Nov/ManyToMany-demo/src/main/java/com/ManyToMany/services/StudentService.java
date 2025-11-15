package com.ManyToMany.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManyToMany.repository.StudentRepository;
import com.ManyToMany.requests.Student;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public Student save(Student student) {
        return repo.save(student);
    }

    public Student get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
