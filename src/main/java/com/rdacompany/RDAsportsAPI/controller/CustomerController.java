package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.BookingDto;
import com.rdacompany.RDAsportsAPI.exception.CustomerNotFoundException;
import com.rdacompany.RDAsportsAPI.exception.Response;
import com.rdacompany.RDAsportsAPI.exception.SessionNotFoundException;
import com.rdacompany.RDAsportsAPI.service.CustomerService;
import com.rdacompany.RDAsportsAPI.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        logger.info("Inicio addCustomer");
        Customer newCustomer = customerService.addCustomer(customer);
        logger.info("Fin addCustomer");
        return newCustomer;
    }

    /*
     * Relacionar un customer con una session
     * */
    @PostMapping("/bookings")
    public ResponseEntity<Response> relacion(@RequestBody BookingDto bookingDto)
            throws CustomerNotFoundException, SessionNotFoundException {
        logger.info("Inicio booking");
        Customer customer = customerService.findByCustomerId(bookingDto.getCustomerId());
        logger.info("Customer encontrado " + customer.getCustomerId());
        Session session = sessionService.findBySessionId(bookingDto.getSessionId());
        logger.info("Session encontrado " + session.getSessionId());
        customerService.addBooking(customer, session);


        Response response = new Response("1", "Sesion añadida al Customer " +
                bookingDto.getCustomerId());
        logger.info("Fin booking");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        List<Customer> customers;
        customers = customerService.findAll();

        return customers;
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {
        Customer customer = customerService.findByCustomerId(customerId);
        return customer;
    }


    @DeleteMapping("/customer/{customerId}")
    public Customer removeCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        logger.info("Inicio removeCustomer");
        Customer customer = customerService.removeCustomer(customerId);
        logger.info("Fin removeCustomer");
        return customer;
    }


    @PutMapping("/customer/{customerId}")
    public Customer modifyCustomer(@RequestBody Customer customer, @PathVariable int customerId) throws CustomerNotFoundException {
        logger.info("Inicio modifyCustomer");
        Customer newCustomer = customerService.modifyCustomer(customerId, customer);
        logger.info("Fin modifyCustomer");
        return newCustomer;
    }

}
