package com.el.EmployeeGradleDataManagement1.service;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;

import java.util.List;

public interface IEmployeeService {

public Employee saveEmployee(Employee employee);
public List<Employee> fetchEmployeeList();
public Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException;
public void deleteEmployeeById(Long id) throws EmployeeNotFoundException;
public Employee fetchEmployeeById(Long id) throws EmployeeNotFoundException;
public Employee fetchEmployeeByName(String name) throws EmployeeNotFoundException;
}
