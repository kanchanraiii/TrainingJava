package com.MongoSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.MongoSpring.Model.Student;
import com.MongoSpring.Repository.StudentRepo;

@RestController
public class MainController {
	
	@Autowired
	StudentRepo repo;
	
	// add a student
	@PostMapping("/addStudent")
	public void addStudent(@RequestBody Student stud) {
		repo.save(stud);
		
	}
	
	// fetch all student details as a list
	@GetMapping("/fetchStudents")
	public List<Student> fetchStudents() {
		return repo.findAll();
		
	}
	
	//fetch a student's detail by id
	@GetMapping("/fetchStudent/{id}")
	public Student fetchStudentById(@PathVariable Integer id){
		return repo.findById(id).orElse(null);
	}

	//update student
	 @PutMapping("/updateStudent")
	 public void updateStudent(@RequestBody Student stud) {
		 Student data=repo.findById(stud.getRno()).orElse(null);
		 if(data!=null) {
			 data.setName(stud.getName());
			 data.setAddress(stud.getAddress());
			 repo.save(data);
		 }
	 }
	 
	 //delete student
	 @DeleteMapping("/deleteStudent/{id}")
	 public void deleteStudent(@PathVariable Integer id) {
		 repo.deleteById(id);
	 }
		
}
