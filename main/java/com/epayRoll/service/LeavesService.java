package com.epayRoll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayRoll.entity.Employee;
import com.epayRoll.entity.Leaves;
import com.epayRoll.repository.LeavesRespository;

@Service
public class LeavesService {
	
	@Autowired
	LeavesRespository leaveRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	
	public List<Leaves> getAllLeaves(){
		return leaveRepository.findAll();
	}
	
	
	public Leaves getByIdLeaves(int id) {
		return leaveRepository.findById(id).get();
	}
	
	public void insertLeaves(Leaves leave) {
		Employee employee = employeeService.getEmployeeId(leave.getEmployee().getId());
		if(employee != null) {
			leave.setEmployee(employee);
			leaveRepository.save(leave);
		}else {
			System.out.println("Leaves are not inserted");
		}
	}
	
	public void updateLeaves(int id,Leaves leave) {
		Optional<Leaves> l = leaveRepository.findById(id);
		if(l.isPresent()) {
			Leaves l1 = l.get();
			l1.setStartDate(leave.getStartDate());
			l1.setEndDate(leave.getEndDate());
			l1.setReason(leave.getReason());
			l1.setEmployee(leave.getEmployee());
			leaveRepository.save(l1);
		}
	}
	
	
	public void deleteLeaveById(int id) {
		leaveRepository.deleteById(id);
	}
	
	
	
	
}
