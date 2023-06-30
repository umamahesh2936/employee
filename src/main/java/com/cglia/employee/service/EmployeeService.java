package com.cglia.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cglia.employee.dao.EmployeeDao;
import com.cglia.employee.dto.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao dao;

	public Employee saveEmp(Employee employee) {
		return dao.saveEmployee(employee);
	}

	public Employee getById(Integer id) {
		return dao.getById(id);
	}

	public List<Employee> getaAll() {
		return dao.getAllEmp();
	}

	public String deleteById(Integer id) {
		return dao.deleteByid(id);
	}

	public Employee updateById(Employee employee, Integer id) {
		return dao.updateById(employee, id);
	}

	public List<Employee> getByName(String name) {
		return dao.getByName(name);
	}

}
