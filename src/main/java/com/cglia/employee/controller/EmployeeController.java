package com.cglia.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cglia.employee.dto.Employee;
import com.cglia.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	public Employee save(@RequestBody Employee employee) {
		return employeeService.saveEmp(employee);
	}

	@GetMapping("/employee/get/{id}")
	public Employee getById(@PathVariable Integer id) {
		return employeeService.getById(id);
	}

	@GetMapping("/employee")
	public List<Employee> getAllEmp() {
		return employeeService.getaAll();
	}

	@DeleteMapping("/employee/{id}")
	public String deleteById(@PathVariable Integer id) {
		return employeeService.deleteById(id);
	}

	@GetMapping("/employee/{name}")
	public List<Employee> findByName(@PathVariable String name) {
		return employeeService.getByName(name);
	}

	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
		return employeeService.updateById(employee, id);
	}

}
