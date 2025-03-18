package com.el.EmployeeGradleDataManagement1.repository;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .email("Rob@yahoo.com")
                .name("Robert Bob")
                .jobTitle("QA")
                .department("Tester")
                .hireDate(LocalDate.of(2010, 04, 10))
                .build();
        entityManager.persist(employee);
    }

    @Test
    void whenFindById_thenReturnEmployee() {
        employee = employeeRepository.findById(1L).get();
        Assertions.assertEquals("Robert Bob", employee.getName());
    }
}
