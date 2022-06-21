package com.rdacompany.RDAsportsAPI.repository;

import com.rdacompany.RDAsportsAPI.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();




}
