package com.el.EmployeeGradleDataManagement1.controller;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;
import com.el.EmployeeGradleDataManagement1.service.impl.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
@Autowired
   private IEmployeeService employeeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee ) {
        LOGGER.info("Logger for saveEmployee of EmployeeController");
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/employees")
    public List<Employee> fetchEmployeeList() {
        LOGGER.info("Logger for fetchEmployees of EmployeeController");

        return employeeService.fetchEmployeeList();
    }
    @GetMapping("/employees/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        LOGGER.info("Logger for fetchEmployeeById of EmployeeController");
        return employeeService.fetchEmployeeById(id);
    }
    @PutMapping("/employees/{id}")
    public Employee updateEmployeeById(@PathVariable("id") Long id, @Valid @RequestBody Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Logger for updateEmployee of EmployeeController");
        return employeeService.updateEmployeeById(id, employee);
}
@DeleteMapping("/employees/{id}")
public String deleteEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        LOGGER.info("Logger for deleteEmployee of EmployeeController");
        employeeService.deleteEmployeeById(id);
        return "Employee deleted Successfully";

}
@GetMapping("/employees/name/{name}")
public Employee fetchEmployeeByName(@PathVariable("name") String name) throws EmployeeNotFoundException {
        LOGGER.info("Logger for fetchEmployeeByName of EmployeeController");
        return employeeService.fetchEmployeeByName(name);
}

}