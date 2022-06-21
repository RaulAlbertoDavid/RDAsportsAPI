package com.rdacompany.RDAsportsAPI.repository;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findAll();

    Customer findByCustomerId(int customerId);

}

