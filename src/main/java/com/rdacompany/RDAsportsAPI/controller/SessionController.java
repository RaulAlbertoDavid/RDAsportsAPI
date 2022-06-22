package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Customer;
import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.*;
import com.rdacompany.RDAsportsAPI.service.CustomerService;
import com.rdacompany.RDAsportsAPI.service.SessionService;
import lombok.extern.java.Log;
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
public class SessionController {

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/sessions")
    public ResponseEntity<Session> addSession (@RequestBody SessionDto sessionDto) throws SessionNotFoundException, AreaNotFoundException {
        logger.info("Inicio addSession");
        Session newSession = sessionService.addSession(sessionDto);
        logger.info("Fin addSession");
        return ResponseEntity.ok(newSession);
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getSessions() {
        List<Session> sessions;

        sessions = sessionService.findAll();
        logger.info("Fin consulta de todas las session");
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<Session> getSession (@PathVariable int sessionId) throws SessionNotFoundException{
        logger.info ("Inicio busqueda sessionById");
        Session session = sessionService.findBySessionId(sessionId);
        logger.info("Fin consultar sessionById");
        return ResponseEntity.ok(session);
    }

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Session> deleteSession(@PathVariable int sessionId) throws SessionNotFoundException{
        logger.info("Inicio deleteSession");
        Session session = sessionService.deleteSession(sessionId);
        logger.info("Fin deleteSession");
        return ResponseEntity.ok(session);
    }

    //como no devuelvo nada pongo un void y creo una nueva respuesta para devolver el codigo de estado de no contenido
    @DeleteMapping("/session/{sessionId}/customer/{customerId}")
    public ResponseEntity<Void> dropOut(@PathVariable int customerId, @PathVariable int sessionId) throws CustomerNotFoundException, SessionNotFoundException {
        logger.info("Inicio dropOut");
        sessionService.dropOut(customerId, sessionId);
        logger.info("Fin dropOut");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/session/{sessionId}")
    public ResponseEntity<Session> modifySession(@RequestBody SessionDto newSessionDto, @PathVariable int sessionId) throws SessionNotFoundException, AreaNotFoundException, ActivityNotFoundExcepction, EmployeeNotFoundException {
        logger.info("Inicio modifySession");
        Session session = null;
        try {
            session = sessionService.modifySession(sessionId, newSessionDto);
        } catch (com.rdacompany.RDAsportsAPI.exception.EmployeeNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Fin modifySession");
        return  ResponseEntity.ok(session);
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

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(SessionNotFoundException snfe) {
        logger.info("404: Session not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(snfe.getMessage()));
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(AreaNotFoundException anfe) {
        logger.info("404: Area not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(anfe.getMessage()));
    }
    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(ActivityNotFoundExcepction anfe) {
        logger.info("404: Activity not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(anfe.getMessage()));
    }
    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(EmployeeNotFoundException enfe) {
        logger.info("404: Employee not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(enfe.getMessage()));
    }
    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(CustomerNotFoundException cnfe) {
        logger.info("404: Customer not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(cnfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }


}
