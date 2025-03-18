package com.el.EmployeeGradleDataManagement1.controller;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import com.el.EmployeeGradleDataManagement1.service.impl.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
         employee = new Employee().builder()
                .id(1L)
                .name("Elie Inahon")
                .email("elie@gmail.com")
                .department("IT")
                 .jobTitle("Lead Dev")
                .salary(BigDecimal.valueOf(500000))
                .hireDate(LocalDate.parse("2025-02-20"))
                 .build();

    }

    @Test
    void saveEmployee() throws Exception {
        Employee inputEmployee = Employee.builder()
                .name("Elie Inahon")
                .email("Elie@gmail.com")
                .salary(BigDecimal.valueOf(500000.00))
                .hireDate(LocalDate.parse("2025-02-20"))
                .jobTitle("Lead Dev")
                .build();

        Mockito.when(employeeService.saveEmployee(inputEmployee))
                .thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{  \"name\": \"Elie Inahon\",\n" +
                                "\t\t\"department\": \"IT\",\n" +
                                "\t\t\"jobTitle\": \"Lead Dev\",\n" +
                                "\t\t\"salary\": 500000.00,\n" +
                                "\t\t\"hireDate\": \"2025-02-20\"\n" +
                                "\n" +
                                "\t}\n"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getEmployeeById() throws Exception {
        employee = Employee.builder()
                .name("Elie Inahon")
                .hireDate(LocalDate.parse("2025-02-20"))
                .salary(BigDecimal.valueOf(500000.00))
                .department("IT")
                .jobTitle("Lead Dev")
                .email("Elie@gmail.com")
                .build();

        Mockito.when(employeeService.fetchEmployeeById(1L))
                .thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
