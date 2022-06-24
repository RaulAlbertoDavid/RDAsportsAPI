package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.exception.CustomerNotFoundException;
import com.rdacompany.RDAsportsAPI.repository.CustomerRepository;
import com.rdacompany.RDAsportsAPI.repository.SessionRepository;
import org.modelmapper.ModelMapper;
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
    public Customer findByCustomerId(int customerId) throws CustomerNotFoundException{
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer modifyCustomer(int customerId, Customer newCustomer) throws CustomerNotFoundException{
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Customer modCustomer = mapper.map(newCustomer, Customer.class);
        modCustomer.setCustomerId(customer.getCustomerId());
        return customerRepository.save(customer);
    }


    @Override
    public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(CustomerNotFoundException::new);
        customer.getSessions().forEach(session -> {
            session.getCustomers().remove(customer);
            sessionRepository.save(session);
        });
        customerRepository.delete(customer);
        return customer;
    }

}
