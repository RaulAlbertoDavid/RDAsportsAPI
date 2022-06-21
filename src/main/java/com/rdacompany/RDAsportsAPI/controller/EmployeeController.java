package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Employee;
import com.rdacompany.RDAsportsAPI.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        logger.info("Inicio addEmployee");
        Employee newEmployee = employeeService.addEmployee(employee);
        logger.info("Fin addEmployee");
        return newEmployee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()  {
        List<Employee> employees;

        employees = employeeService.findAll();

        return employees;
    }
}
