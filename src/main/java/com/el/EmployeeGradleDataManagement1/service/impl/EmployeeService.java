package com.el.EmployeeGradleDataManagement1.service.impl;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService implements IEmployeeService {

    @Override
    public Employee saveEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return List.of();
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {

    }

    @Override
    public Employee fetchEmployeeById(Long id) {
        return null;
    }

    @Override
    public Employee fetchEmployeeByName(String name) {
        return null;
    }
}
