package com.el.EmployeeGradleDataManagement1.service;

import com.el.EmployeeGradleDataManagement1.model.Employee;

import java.util.List;

public interface IEmployeeService {
public String saveEmployee(Employee employee);
public List<String> fetchEmployeeList();
public String updateEmployeeById(Long id, Employee employee);
public void deleteEmployeeById(Long id);
public String fetchEmployeeById(String id);
public String fetchEmployeeByName(String name);
}
