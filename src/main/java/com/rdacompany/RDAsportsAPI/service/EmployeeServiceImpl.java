package com.rdacompany.RDAsportsAPI.service;


import com.rdacompany.RDAsportsAPI.domain.Employee;
import com.rdacompany.RDAsportsAPI.exception.EmployeeNotFoundException;
import com.rdacompany.RDAsportsAPI.repository.EmployeeRepository;
import com.rdacompany.RDAsportsAPI.repository.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee deleteEmployee(int employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(EmployeeNotFoundException::new);
        employee.getSessions().forEach(session -> sessionRepository.delete(session));
        employeeRepository.delete(employee);

        return employee;
    }

    @Override
    public Employee modifyEmployee(int employeeId, Employee newEmployee) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Employee modEmployee = mapper.map(newEmployee, Employee.class);
        modEmployee.setEmployeeId(employee.getEmployeeId());
        return employeeRepository.save(employee);
    }
}
