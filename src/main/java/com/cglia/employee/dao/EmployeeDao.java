package com.cglia.employee.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cglia.employee.dto.Employee;
import com.cglia.employee.repository.EmployeeRepository;

@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeDao(EmployeeRepository employeeRepository2) {
		this.employeeRepository = employeeRepository2;
	}

	public Employee saveEmployee(Employee employee) {
		if (employee.getStatus() == 1)
			return employeeRepository.save(employee);
		return null;
	}

	public Employee getById(Integer id) {
		Employee employee = null;
		Optional<Employee> optional = employeeRepository.findById(id);
		if (!optional.isEmpty())
			employee = optional.get();
		if (employee != null && employee.getStatus() == 1) {
			return employee;
		}
		return null;
	}

	public List<Employee> getAllEmp() {
		List<Employee> list = employeeRepository.findAll();
		List<Employee> resultEmployees = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Employee employee : list) {
				if (employee.getStatus() == 1) {
					resultEmployees.add(employee);
				}
			}
			return resultEmployees;
		}
		return Collections.emptyList();
	}

	public String deleteByid(Integer id) {
		Employee employee = null;
		Optional<Employee> optional = employeeRepository.findById(id);
		if (!optional.isEmpty())
			employee = optional.get();
		if (employee != null && employee.getStatus() == 1) {
			employee.setStatus(0);
			employeeRepository.save(employee);
			return "Deleted";
		}
		return "No DataFound";

	}

	public Employee updateById(Employee employee, Integer id) {
		Employee employee2 = null;
		Optional<Employee> optional = employeeRepository.findById(id);
		if (!optional.isEmpty())
			employee2 = optional.get();
		if (employee2 != null && employee2.getStatus() == 1) {
			employeeRepository.save(employee);
		}
		return null;
	}

	public List<Employee> getByName(String name) {
		List<Employee> list = employeeRepository.findByEmployeeName(name);
		List<Employee> resultEmployees = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Employee employee : list) {
				if (employee.getStatus() == 1) {
					resultEmployees.add(employee);
				}
			}
			return resultEmployees;
		}
		return Collections.emptyList();
	}

}
