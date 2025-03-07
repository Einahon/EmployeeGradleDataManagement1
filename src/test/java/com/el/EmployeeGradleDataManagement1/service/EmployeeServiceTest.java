package com.el.EmployeeGradleDataManagement1.service;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.repository.EmployeeRepository;
import com.el.EmployeeGradleDataManagement1.service.impl.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee mockEmployee;

    @BeforeEach
    void setUp() {
        mockEmployee = new Employee();
        mockEmployee.setId(1L);
        mockEmployee.setName("Elie Inahon");
        mockEmployee.setSalary(BigDecimal.valueOf(160000.00));
        mockEmployee.setDepartment("IT");
        mockEmployee.setHireDate(LocalDate.parse("2025-01-15"));
        mockEmployee.setJobTitle("Developer");
    }

    @Test
    void testSaveEmployee() throws IllegalAccessException {
        when(employeeRepository.save(mockEmployee)).thenReturn(mockEmployee);

        Employee savedEmployee = employeeService.saveEmployee(mockEmployee);

        assertNotNull(savedEmployee);
        assertEquals("Elie Inahon", savedEmployee.getName());
        verify(employeeRepository, times(1)).save(mockEmployee);
    }

    @Test
    void testFetchEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(mockEmployee);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> fetchedEmployees = employeeService.fetchEmployeeList();

        assertEquals(1, fetchedEmployees.size());
        assertEquals("Elie Inahon", fetchedEmployees.get(0).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployeeById() throws EmployeeNotFoundException, IllegalAccessException {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
        when(employeeRepository.save(mockEmployee)).thenReturn(mockEmployee);

        mockEmployee.setJobTitle("Senior Developer");
        Employee updatedEmployee = employeeService.updateEmployeeById(1L, mockEmployee);

        assertEquals("Senior Developer", updatedEmployee.getJobTitle());
        verify(employeeRepository, times(1)).save(mockEmployee);
    }

    @Test
    void testUpdateEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployeeById(1L, mockEmployee));
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployeeById() throws EmployeeNotFoundException {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployeeById(1L));
        verify(employeeRepository, never()).deleteById(anyLong());
    }



    @Test
    void testFetchEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.fetchEmployeeById(1L));
    }

    @Test
    void testFetchEmployeeByName_NotFound() {
        when(employeeRepository.findByNameIgnoreCase("John Doe")).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.fetchEmployeeByName("John Doe"));
    }
}
