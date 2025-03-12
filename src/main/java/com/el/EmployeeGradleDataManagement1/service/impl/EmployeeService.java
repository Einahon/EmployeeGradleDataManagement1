package com.el.EmployeeGradleDataManagement1.service.impl;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.repository.EmployeeRepository;
import com.el.EmployeeGradleDataManagement1.service.IEmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) throws IllegalAccessException {
        if (employee == null) {
            throw new IllegalArgumentException("Employee object must not be null");
        }
        Employee getEmployee = employeeRepository.findByEmailIgnoreCase(employee.getEmail());
        if(getEmployee != null) {
            throw new IllegalArgumentException("Employee already exists by email " + employee.getEmail());
        }
        List<String> validatedErrorList = validateEmployee(employee);

        if (!CollectionUtils.isEmpty(validatedErrorList)) {
            throw new IllegalArgumentException("Employee data  has incorrect format. Review these - " + validatedErrorList);
        }
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException, IllegalAccessException {
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found by id " + id));
        validateEmployee(employee);
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setEmail(employee.getEmail());
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
            throw new EmployeeNotFoundException("Employee not found by id " + id);
        }
        employeeRepository.deleteById(id);
    }


    @Override
    public Employee fetchEmployeeById(Long id) throws EmployeeNotFoundException {
        employeeRepository.findById(id);
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee Not Available");
        }
        return employeeRepository.findById(id).get();
    }


    @Override
    public Employee fetchEmployeeByName(String name) throws EmployeeNotFoundException {
        if (employeeRepository.findByNameIgnoreCase(name) == null) {
            throw new EmployeeNotFoundException("Employee Not Found By Name " + name);
        }
        return employeeRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Employee fetchEmployeeByEmail(String email) throws EmployeeNotFoundException {
        if(employeeRepository.findByEmailIgnoreCase(email) == null) {
            throw new EmployeeNotFoundException("Employee Not Found By Email " + email);
        }
        return employeeRepository.findByEmailIgnoreCase(email);
    }


    public List<String> validateEmployee(Employee employee) throws IllegalAccessException {

        List<String> emptyFields = new ArrayList<>();

        Field[] pojoFields = Employee.class.getDeclaredFields();

        List<String> missingFields = new ArrayList<>();

        if (employee.getSalary() != null) {
            BigDecimal minSalary = new BigDecimal("60000.00");
            BigDecimal maxSalary = new BigDecimal("1000000.00");
            if (employee.getSalary().intValue() < minSalary.intValue() || employee.getSalary().intValue() > maxSalary.intValue()) {
                emptyFields.add("salary");
            }
        }
        if (employee.getHireDate() != null && employee.getHireDate().isAfter(LocalDate.now())) {
            emptyFields.add("hireDate");
        }

        for (Field pojoField : pojoFields) {
            pojoField.setAccessible(true);
            if(pojoField.isAnnotationPresent(jakarta.persistence.Id.class)) {
                continue;
            }
            try {
                Field employeeField = employee.getClass().getDeclaredField(pojoField.getName());
                System.out.println(employeeField.getName());
                employeeField.setAccessible(true);

                Object value = employeeField.get(employee);
                System.out.println(value);
                if (value == null || StringUtils.isBlank(value.toString())) {
                    emptyFields.add(pojoField.getName());
                }
            } catch (NoSuchFieldException e) {
                missingFields.add(pojoField.getName());
            } catch (IllegalAccessException e) {
                System.out.println( e.getClass()+ " " + e.getMessage());
            }
        }

        if (!missingFields.isEmpty()) {
            emptyFields.addAll(missingFields);
        }
        return emptyFields;
    }
}






