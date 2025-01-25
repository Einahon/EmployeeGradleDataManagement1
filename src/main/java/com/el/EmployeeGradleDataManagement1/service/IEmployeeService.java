package com.el.EmployeeGradleDataManagement1.service;

import com.el.EmployeeGradleDataManagement1.model.Employee;

import java.util.List;

public interface IEmployeeService {

public Employee saveEmployee(Employee employee);
public List<Employee> fetchEmployeeList();
public Employee updateEmployeeById(Long id, Employee employee);
public void deleteEmployeeById(Long id);
public Employee fetchEmployeeById(Long id);
public Employee fetchEmployeeByName(String name);
}
