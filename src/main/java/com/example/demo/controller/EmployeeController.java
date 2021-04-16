package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;

	@PostMapping("/save")
	public ResponseEntity saveEmployee(@RequestBody Employee emp) {

		repo.save(emp);

		return new ResponseEntity("Employee saved successfully", HttpStatus.OK);
	}

	@GetMapping("/show/{dept}")
	public List<Employee> getEmployeeByDept(@PathVariable String dept) {

		return repo.findByDept(dept);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody Employee emp) {

		Employee empObj = repo.findById(id);
		empObj.setDept(emp.getDept());
		empObj.setId(id);
		empObj.setName(emp.getName());
		empObj.setSalary(emp.getSalary());

		repo.save(empObj);

		return new ResponseEntity("Employee updated successfully", HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteEmployee(@PathVariable int id) {
		Employee empObj=repo.findById(id);
		repo.delete(empObj);
		return new ResponseEntity("Employee deleted successfully", HttpStatus.OK);
	}
	

}
