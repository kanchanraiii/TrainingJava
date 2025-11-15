package com.ManyToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ManyToMany.requests.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
