package com.epayRoll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epayRoll.entity.Salary;


@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer>{
   List<Salary> findByEmployee_Id(int employeeId);
}
