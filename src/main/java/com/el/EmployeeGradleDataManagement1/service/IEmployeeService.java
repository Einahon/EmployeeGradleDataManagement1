package com.el.EmployeeGradleDataManagement1.service;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;

import java.util.List;

public interface IEmployeeService {

 Employee saveEmployee(Employee employee) throws IllegalAccessException;
 List<Employee> fetchEmployeeList();
 Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException, IllegalAccessException;
 void deleteEmployeeById(Long id) throws EmployeeNotFoundException;
 Employee fetchEmployeeById(Long id) throws EmployeeNotFoundException;
 Employee fetchEmployeeByName(String name) throws EmployeeNotFoundException;
 Employee fetchEmployeeByEmail(String email) throws EmployeeNotFoundException;
}
