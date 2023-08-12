package com.epayRoll.service;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.epayRoll.entity.Employee;
import com.epayRoll.entity.Salary;
import com.epayRoll.repository.SalaryRepository;

@Service
public class SalaryService {
	
	@Autowired
	SalaryRepository salaryRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	
	public List<Salary> getAllSalary(){
		return salaryRepository.findAll();
	}
	
	
	public Salary getByIdSalary(int salaryId) {
		return salaryRepository.findById(salaryId).get();
	}
	
	
	public void insertSalary(Salary salary) {
	    Employee employee = employeeService.getEmployeeId(salary.getEmployee().getId());

	    if (employee != null) {
	        salary.setEmployee(employee);  
	        salaryRepository.save(salary);
	    } else {
	        System.out.println("insert not Occured");
	    }
	}
	
	
	public void updatedSalary(int id,Salary salary) {
	Optional<Salary> current_salary = salaryRepository.findById(id);
	    if(current_salary.isPresent()) {
	    	Salary newSalary =  current_salary.get();
	    	newSalary.setAmount(salary.getAmount());
	    	newSalary.setSalary_credited_date(salary.getSalary_credited_date());
	    	newSalary.setEmployee(salary.getEmployee());
	    	salaryRepository.save(newSalary);
	    }
	}
	

	

	
	public void deleteSalary(int salaryId) {
		salaryRepository.deleteById(salaryId);
	}
	

    public List<Salary> getSalaryByEmployeeId(int employeeId) {
        return salaryRepository.findByEmployee_Id(employeeId);
    }
	
}
