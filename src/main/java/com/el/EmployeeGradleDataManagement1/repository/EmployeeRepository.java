package com.el.EmployeeGradleDataManagement1.repository;

import com.el.EmployeeGradleDataManagement1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     Employee findByNameIgnoreCase(String name);
     Employee findByEmailIgnoreCase(String email);
}
