package com.el.EmployeeGradleDataManagement1.service.impl;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    @Override
    public String saveEmployee(Employee employee) {
        return "";
    }

    @Override
    public List<String> fetchEmployeeList() {
        return List.of();
    }

    @Override
    public String updateEmployeeById(Long id, Employee employee) {
        return "";
    }

    @Override
    public void deleteEmployeeById(Long id) {

    }

    @Override
    public String fetchEmployeeById(String id) {
        return "";
    }

    @Override
    public String fetchEmployeeByName(String name) {
        return "";
    }
}
