package com.el.EmployeeGradleDataManagement1.service.impl;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.repository.EmployeeRepository;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException {
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found by id "+ id));
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
}
