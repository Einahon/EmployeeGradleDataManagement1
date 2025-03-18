package com.el.EmployeeGradleDataManagement1.service;

import com.el.EmployeeGradleDataManagement1.error.EmployeeNotFoundException;
import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private IEmployeeService employeeService;

    @MockitoBean
    private EmployeeRepository employeeRepository;



    @BeforeEach
    void setUp() {
       Employee employee = Employee.builder()
                .name("Elie Inahon")
                .email("Elie@gmail.com")
                .hireDate(LocalDate.parse("2025-02-20"))
                .department("IT")
                .jobTitle("Lead Dev")
                .salary(BigDecimal.valueOf(500000.00))
                .build();
        Mockito.when(employeeRepository.findByNameIgnoreCase("Elie Inahon"))
                .thenReturn(employee);

    }

    @Test
    public void whenValidEmployeeName_thenReturnEmployeeFound() throws EmployeeNotFoundException {
        String name = "Elie Inahon";
        Employee found =
                employeeService.fetchEmployeeByName(name);
        assertEquals(name, found.getName());
    }


}





