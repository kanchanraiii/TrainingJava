package com.ManyToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ManyToMany.requests.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
