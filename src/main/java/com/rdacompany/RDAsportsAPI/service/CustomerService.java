package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    void addBooking(Customer customer, Session session);

    Customer removeCustomer(int customerId) throws CustomerNotFoundException;

    List<Customer> findAll();

    Customer findByCustomerId(int customerId);

    Customer modifyCustomer(int customerId, Customer customer) throws CustomerNotFoundException;
}
