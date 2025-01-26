package com.el.EmployeeGradleDataManagement1.service.impl;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.repository.EmployeeRepository;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee updatedEmployee = employeeRepository.findById(id).get();
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setDepartment(employee.getDepartment());
        updatedEmployee.setHireDate(employee.getHireDate());
        updatedEmployee.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
            employeeRepository.delete(employee);


    }

    @Override
    public Employee fetchEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee fetchEmployeeByName(String name) {
        return employeeRepository.findByNameIgnoreCase(name);
    }
}
