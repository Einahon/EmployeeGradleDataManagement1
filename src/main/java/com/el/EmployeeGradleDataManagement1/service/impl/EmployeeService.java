package com.el.EmployeeGradleDataManagement1.service.impl;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.repository.EmployeeRepository;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException {
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found by id "+ id));
        validateEmployee(employee);
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setDepartment(employee.getDepartment());
        updatedEmployee.setHireDate(employee.getHireDate());
        updatedEmployee.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) throws EmployeeNotFoundException {
       Optional<Employee> employee = employeeRepository.findById(id);
       if (!employee.isPresent()) {
           throw new EmployeeNotFoundException("Employee not found by id "+ id);
       }
           employeeRepository.deleteById(id);
       }


    @Override
    public Employee fetchEmployeeById(Long id) throws EmployeeNotFoundException {
        employeeRepository.findById(id);
        if(!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee Not Available");
        }
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee fetchEmployeeByName(String name) throws EmployeeNotFoundException {
        if(employeeRepository.findByNameIgnoreCase(name) == null) {
            throw new EmployeeNotFoundException("Employee Not Found By Name "+ name);
        }
        return employeeRepository.findByNameIgnoreCase(name);
    }

    private void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name must not be empty or null");
        }
        if (employee.getName().length() < 5 || employee.getName().length() > 30) {
            throw new IllegalArgumentException("Employee name must be between 5 and 30 characters");
        }

        if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException("Department must not be empty or null");
        }
        if (employee.getDepartment().length() < 2 || employee.getDepartment().length() > 20) {
            throw new IllegalArgumentException("Department must be between 2 and 20 characters");
        }

        if (employee.getJobTitle() == null || employee.getJobTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Job title must not be empty or null");
        }
        if (employee.getJobTitle().length() < 2 || employee.getJobTitle().length() > 20) {
            throw new IllegalArgumentException("Job title must be between 2 and 20 characters");
        }

        if (employee.getSalary() == null) {
            throw new IllegalArgumentException("Salary must not be null");
        }
        if (employee.getSalary().compareTo(new BigDecimal("60000.00")) < 0) {
            throw new IllegalArgumentException("Salary must be greater or equal to $60000.00");
        }
        if (employee.getSalary().compareTo(new BigDecimal("1000000.00")) > 0) {
            throw new IllegalArgumentException("Salary must be less or equal to $1000000.00");
        }

        if (employee.getHireDate() == null) {
            throw new IllegalArgumentException("Hire date must not be null");
        }
        if (employee.getHireDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hire date cannot be in the future");
        }

    }
}
