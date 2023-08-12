package com.epayRoll.controller;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epayRoll.dto.LeavesDTO;
import com.epayRoll.entity.Employee;
import com.epayRoll.entity.Leaves;
import com.epayRoll.repository.EmployeeRepository;
import com.epayRoll.repository.LeavesRespository;

@RestController
public class leavesController {

    @Autowired
    private LeavesRespository leavesRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/leave")
    public ResponseEntity<String> leaveWork(@RequestBody Leaves leave) {
        Optional<Employee> employee = employeeRepository.findById(leave.getEmployee().getId());
        if (employee.isPresent()) {
            leave.setEmployee(employee.get());
            leavesRepository.save(leave);
            return ResponseEntity.status(HttpStatus.CREATED).body("Leave scheduled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee not found, leave not scheduled");
        }
    }

    @DeleteMapping("leave/{id}")
    public void deleteLeave(@PathVariable int id) {
    	leavesRepository.deleteById(id);
    }

    @PutMapping("leave/{id}")
    public ResponseEntity<String> updateLeave(@PathVariable  int id, @RequestBody Leaves leave) {
        Optional<Leaves> optionalLeave = leavesRepository.findById(id);
        if (optionalLeave.isPresent()) {
            Leaves l = optionalLeave.get();
            l.setStartDate(leave.getStartDate());
            l.setEndDate(leave.getEndDate());
            l.setReason(leave.getReason());
            leavesRepository.save(l);
            return ResponseEntity.ok("Leave schedule updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave schedule not found");
        }
    }

    @GetMapping("/leave")
    public List<LeavesDTO> getLeaves() {
        List<Leaves> leaveWorks = leavesRepository.findAll();
        List<LeavesDTO> leaveDTOs = new ArrayList<>();
        for (Leaves leave : leaveWorks) {
            LeavesDTO dto = new LeavesDTO();
            dto.setId(leave.getId());
            dto.setStartDate(leave.getStartDate());
            dto.setEndDate(leave.getEndDate());
            dto.setReason(leave.getReason());
            dto.setEmployee_id(leave.getEmployee().getId());
            leaveDTOs.add(dto);
        }
        return leaveDTOs;
    }
    
    @GetMapping("/leave/{id}")
    public LeavesDTO getLeavesById(@PathVariable int id) {
        List<Leaves> leaveWorks = leavesRepository.findAll();
        for (Leaves leave : leaveWorks) {
            if (leave.getId() == id) {
                LeavesDTO dto = new LeavesDTO();
                dto.setId(leave.getId());
                dto.setStartDate(leave.getStartDate());
                dto.setEndDate(leave.getEndDate());
                dto.setReason(leave.getReason());
                dto.setEmployee_id(leave.getEmployee().getId());
                return dto;
            }
        }
        return null;
    }

    
    
    

    @GetMapping("getLeavesForEmployee/{employeeId}")
    public List<LeavesDTO> getLeavesForEmployee(@PathVariable int employeeId) {
        List<Leaves> leaves = leavesRepository.findAllByEmployeeId(employeeId);
        List<LeavesDTO> leaveDTOs = new ArrayList<>();
        for (Leaves leave : leaves) {
            LeavesDTO dto = new LeavesDTO();
            dto.setId(leave.getId());
            dto.setStartDate(leave.getStartDate());
            dto.setEndDate(leave.getEndDate());
            dto.setReason(leave.getReason());
            dto.setEmployee_id(leave.getEmployee().getId());
            leaveDTOs.add(dto);
        }
        return leaveDTOs;
    }
}