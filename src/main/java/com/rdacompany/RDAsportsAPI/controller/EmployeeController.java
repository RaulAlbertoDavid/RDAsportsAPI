package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Employee;
import com.rdacompany.RDAsportsAPI.exception.*;
import com.rdacompany.RDAsportsAPI.service.EmployeeService;
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
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        logger.info("Inicio addEmployees");
        Employee newEmployee = employeeService.addEmployee(employee);
        logger.info("Fin addEmployees");
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees()  {
        List<Employee> employees;
        employees = employeeService.findAll();
        logger.info("Fin getEmployees");
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee (@PathVariable int employeeId) throws EmployeeNotFoundException {
        logger.info("Inicio deleteEmployee");
        Employee employee = employeeService.deleteEmployee(employeeId);
        logger.info("Fin deleteEmployee");
        return ResponseEntity.ok(employee);
    }


    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> modifyEmployee (@RequestBody Employee newEmployee, @PathVariable int employeeId) throws EmployeeNotFoundException {
        logger.info("Inicio modifyEmployee");
        Employee employee = employeeService.modifyEmployee(employeeId, newEmployee);
        logger.info("Fin modifyEmployee");
        return ResponseEntity.ok(newEmployee);
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

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException enfe) {
        logger.info("404: Employee not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(enfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }
}
