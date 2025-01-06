package com.el.EmployeeGradleDataManagement1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please Add Employee Name.")
    @Size(min = 5, max = 30, message = "Employee name must be btwn 5 - 30 characters")
    private String name;
    @NotBlank(message = "Please add department")
    @Size(min = 2, max = 20, message = "Department must be btwn 2 - 20 characters")
    private String department;
    @NotBlank(message = "Please add Job Title")
    @Size(min = 2, max = 20, message = "Job title must be btwn 2 - 20 characters")
    private String jobTitle;
    @NotNull(message = "Salary must not be null")
    @DecimalMin(value = "60000.00", message = "Salary must be greater or equal $60000.00")
    @DecimalMax(value = "1000000.00", message = "Salary must be less or equal to $1000000.00")
    @Digits(integer = 10, fraction = 2, message = "Salary must be 2 digits after the decimal point")
    private BigDecimal salary;
    @NotNull(message = "Hired date must not be null")
    @PastOrPresent(message = "Hired date cannot be in the future")
    private LocalDate hireDate;
}
