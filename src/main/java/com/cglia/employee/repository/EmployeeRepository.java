package com.cglia.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cglia.employee.dto.Employee;

@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByEmployeeName(String name);
}
