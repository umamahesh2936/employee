package com.cglia.employee.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cglia.employee.dto.Employee;
import com.cglia.employee.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
 class EmployeeDaoTest {

	@Autowired
	EmployeeRepository employeeRepository;
	private EmployeeDao employeeDao;
	Employee employee;
	Employee employee2;
	List<Employee> list = new ArrayList<>();

	@BeforeEach
	void setUp() {
		employeeDao = new EmployeeDao(employeeRepository);
		mock(EmployeeRepository.class);
		employee = new Employee(1, "Jane Smith", 2500.00, 1);

		employee2 = new Employee(2, "Jane Smith", 2500.00, 1);
		list.add(employee);
		list.add(employee2);
		employeeDao.saveEmployee(employee);
		 employeeDao.saveEmployee(employee2);
	}

//working
	@Test
	 void testSaveEmployee() {
		Employee employee3 = new Employee();
		employee3.setEmployeeId(3);
		employee3.setEmployeeName("John Doe");
		employee3.setEmployeeSalary(2000.00);
		employee3.setStatus(1);

		Employee savedEmployee = employeeDao.saveEmployee(employee3);

		assertNotNull(savedEmployee.getEmployeeId());
		assertEquals("John Doe", savedEmployee.getEmployeeName());
		assertEquals(2000.00, savedEmployee.getEmployeeSalary());
		assertEquals(1, savedEmployee.getStatus());
	}

//working
	@Test
	 void testGetById() {
		Employee retrievedEmployee = employeeDao.getById(1);

		assertEquals(1, retrievedEmployee.getEmployeeId());
		assertEquals("Jane Smith", retrievedEmployee.getEmployeeName());
		assertEquals(2500.00, retrievedEmployee.getEmployeeSalary());
		assertEquals(1, retrievedEmployee.getStatus());
	}

//working
	@Test
	 void testGetAllEmp() {

		List<Employee> employees = employeeDao.getAllEmp();
		assertEquals(2, employees.size());
	}

	@Test
	 void testDeleteById() {
		employeeDao.saveEmployee(employee);
		String deletedEmployee = employeeDao.deleteByid(1);

		assertNotNull(deletedEmployee);
		if (deletedEmployee != "No DataFound")
			assertEquals("Deleted", deletedEmployee);
		else {
			System.out.println("employee not found");
		}
	}
}
