package com.epayRoll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epayRoll.entity.Leaves;

@Repository
public interface LeavesRespository extends JpaRepository<Leaves, Integer>{
	List<Leaves> findAllByEmployeeId(int employeeId);
}
