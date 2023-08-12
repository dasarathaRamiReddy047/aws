package com.epayRoll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epayRoll.entity.Employee;
import com.epayRoll.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	
	@GetMapping("employee")
	public List<Employee> getAllEmployee(){
		return empService.getAllEmployees();
	}
	
	@GetMapping("employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return empService.getEmployeeId(id);
	}
	
	@PutMapping("employee/{id}")
	public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		empService.updateEmployee(id, employee);
	}
	
	@PostMapping("employee")
	public void insertEmployee(@RequestBody  Employee employee) {
		empService.insertEmployee(employee);
	}
	
	@DeleteMapping("employee/{id}")
	public void deleteEmployee(@PathVariable int id) {
		empService.deleteEmployee(id);
	}

	
}
