package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Employee;
import com.rdacompany.RDAsportsAPI.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> findAll();

    Employee deleteEmployee(int employeeId) throws EmployeeNotFoundException;

    Employee modifyEmployee(int employeeId, Employee newEmployee) throws EmployeeNotFoundException;
}
