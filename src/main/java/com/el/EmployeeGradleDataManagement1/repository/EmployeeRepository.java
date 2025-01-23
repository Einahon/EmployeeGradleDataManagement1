package com.el.EmployeeGradleDataManagement1.repository;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByNameIgnoreCase(String name);
}
