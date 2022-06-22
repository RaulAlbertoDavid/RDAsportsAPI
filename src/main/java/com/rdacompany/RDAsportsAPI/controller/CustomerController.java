package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.exception.*;
import com.rdacompany.RDAsportsAPI.service.CustomerService;
import com.rdacompany.RDAsportsAPI.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        logger.info("Inicio addCustomer");
        Customer newCustomer = customerService.addCustomer(customer);
        logger.info("Fin addCustomer");
        return ResponseEntity.ok(newCustomer);
    }

    /*
     * Relacionar un customer con una session
     * */
    @PostMapping("/customer/{customerId}/session/{sessionId}")
    public ResponseEntity<Response> register(@PathVariable int customerId, @PathVariable int sessionId)
            throws CustomerNotFoundException, SessionNotFoundException {
        logger.info("Inicio booking");
        Customer customer = customerService.findByCustomerId(customerId);
        logger.info("Customer encontrado " + customer.getCustomerId());
        Session session = sessionService.findBySessionId(sessionId);
        logger.info("Session encontrado " + session.getSessionId());
        customerService.addBooking(customer, session);


        Response response = new Response("1", "Sesion añadida al Customer " + customerId);
        logger.info("Fin booking");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers;
        customers = customerService.findAll();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) throws CustomerNotFoundException{
        Customer customer = customerService.findByCustomerId(customerId);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        logger.info("Inicio removeCustomer");
        Customer customer = customerService.deleteCustomer(customerId);
        logger.info("Fin removeCustomer");
        return ResponseEntity.ok(customer);
    }


    @PutMapping("/customer/{customerId}")
    public ResponseEntity<Customer> modifyCustomer(@RequestBody Customer newCustomer, @PathVariable int customerId) throws CustomerNotFoundException {
        logger.info("Inicio modifyCustomer");
        Customer Customer = customerService.modifyCustomer(customerId, newCustomer);
        logger.info("Fin modifyCustomer");
        return ResponseEntity.ok(newCustomer);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        logger.info("400: Argument not valid");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre) {
        logger.info("400: Bad request");
        return ResponseEntity.badRequest().body(ErrorResponse.badRequest(bre.getMessage()));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(CustomerNotFoundException customerNotFoundException) {
        logger.info("404: Customer not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(customerNotFoundException.getMessage()));
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(SessionNotFoundException snfe) {
        logger.info("404: Session not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(snfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }
}
