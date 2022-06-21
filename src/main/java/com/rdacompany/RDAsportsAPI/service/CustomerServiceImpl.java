package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.exception.CustomerNotFoundException;
import com.rdacompany.RDAsportsAPI.repository.CustomerRepository;
import com.rdacompany.RDAsportsAPI.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    @Override
    public void addBooking(Customer customer, Session session) {
        logger.info("Inicio addBooking");

        customer.getSessions().add(session);
        session.getCustomers().add(customer);
        customerRepository.save(customer);
        sessionRepository.save(session);

        logger.info("Fin addBooking");
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByCustomerId(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }


    @Override
    public Customer removeCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
        return customer;
    }

}
