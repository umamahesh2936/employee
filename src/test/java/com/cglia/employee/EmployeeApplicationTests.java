package com.cglia.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cglia.employee.dao.EmployeeDao;
import com.cglia.employee.dto.Employee;
import com.cglia.employee.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
class EmployeeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMVC;
	Employee employee;
	Employee employeeThree;
	ObjectMapper mapper = new ObjectMapper();
	List<Employee> list = new ArrayList<>();

	@Autowired
	EmployeeRepository employeeRepository;
	EmployeeDao dao;
	List<Employee> employees;

	@BeforeEach
	private void setup() throws Exception {
		this.mockMVC = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

		employee = new Employee(1, "Raju", 98473535, 1);
		employeeThree = new Employee(2, "Jeevan", 7855435, 1);
		list.add(employee);
		list.add(employeeThree);
		dao = new EmployeeDao(employeeRepository);
		dao.saveEmployee(employee);
		dao.saveEmployee(employeeThree);
		employees = dao.getByName(employee.getEmployeeName());

	}

	@Test
	void createEmployeeTest() throws Exception {

		String payload = mapper.writeValueAsString(employee);

		MvcResult result = mockMVC.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(payload))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		Employee employee2 = mapper.readValue(response, Employee.class);
		assertThat(employee2.getEmployeeName()).isEqualTo(employee.getEmployeeName());
		assertThat(employee2.getEmployeeId()).isEqualTo(employee.getEmployeeId());
		assertThat(employee2.getEmployeeSalary()).isEqualTo(employee.getEmployeeSalary());
		assertThat(employee2.getStatus()).isEqualTo(employee.getStatus());
	}

	@Test
	void getEmployeeTest() throws Exception {

		MvcResult result = mockMVC.perform(get("/employee/get/{id}", 1)).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		Employee employee2 = mapper.readValue(response, Employee.class);
		assertThat(employee2.getEmployeeId()).isEqualTo(employee.getEmployeeId());
		assertThat(employee2.getEmployeeName()).isEqualTo(employee.getEmployeeName());
		assertThat(employee2.getEmployeeSalary()).isEqualTo(employee.getEmployeeSalary());
		assertThat(employee2.getStatus()).isEqualTo(employee.getStatus());
	}

//
	@Test
	void getEmployeeAllTest() throws Exception {

		MvcResult result = mockMVC.perform(get("/employee")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		List<Employee> employeeList = mapper.readValue(response, new TypeReference<List<Employee>>() {
		});
		assertThat(list.size()).isEqualTo(employeeList.size());

	}

	@Test
	void deleteEmployeeTest() throws Exception {

		MvcResult mvcResult = mockMVC.perform(delete("/employee/{id}", 2)).andExpect(status().isOk()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertThat(result).isEqualTo("Deleted");
	}

	@Test
	void getEmployeeByNameTest() throws Exception {
		MvcResult result = mockMVC.perform(get("/employee/{name}", "Raju")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		List<Employee> employeeList = mapper.readValue(response, new TypeReference<List<Employee>>() {
		});
		assertThat(employees.size()).isEqualTo(1);
	}
//	@Test
//	public void updateEmployeeTest() throws Exception {
//
//		Employee updatedEmployee = new Employee(1, "Updated John", 50000, 1);
//		String updatedPayload = mapper.writeValueAsString(updatedEmployee);
//
//		MvcResult mvcResult = mockMVC
//				.perform(put("/employee/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(updatedPayload))
//				.andExpect(status().isOk()).andReturn();
//		String response = mvcResult.getResponse().getContentAsString();
//		if (response != null) {
//			Employee employeeUpdated = mapper.readValue(response, Employee.class);
//			System.out.println("successfully updated");
//			assertThat(updatedEmployee.getEmployeeId()).isEqualTo(employeeUpdated.getEmployeeId());
//			assertThat(updatedEmployee.getEmployeeName()).isEqualTo(employeeUpdated.getEmployeeName());
//			assertThat(updatedEmployee.getEmployeeSalary()).isEqualTo(employeeUpdated.getEmployeeSalary());
//			assertThat(updatedEmployee.getStatus()).isEqualTo(employeeUpdated.getStatus());
//		}
//
//	}
}