package com.el.EmployeeGradleDataManagement1.controller;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/employees")
    public String saveEmployee(@Valid @RequestBody Employee employee) {
        LOGGER.info("Logger for saveEmployee of EmployeeController");
        return "My controller works";
    }
    @GetMapping("/employees")
    public <List> String fetchEmployeeList() {
        LOGGER.info("Logger for fetchEmployees of EmployeeController");
        return "My controller works";
    }
    @GetMapping("/employees/{id}")
    public String fetchEmployeeById(@PathVariable("id") int id) {
        LOGGER.info("Logger for fetchEmployeeById of EmployeeController");
        return "My controller works";
    }
    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable("id") int id, @Valid @RequestBody Employee employee) {
        LOGGER.info("Logger for updateEmployee of EmployeeController");
        return "My controller works";
}
@DeleteMapping("/employees/{id}")
public void deleteEmployee(@PathVariable("id") int id) {
        LOGGER.info("Logger for deleteEmployee of EmployeeController");

}
@GetMapping("/employees/name/{name}")
public String fetchEmployeeByName(@PathVariable("name") String name) {
        LOGGER.info("Logger for fetchEmployeeByName of EmployeeController");
        return "My controller works";
}

}