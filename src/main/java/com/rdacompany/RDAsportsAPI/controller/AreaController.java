package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Area;
import com.rdacompany.RDAsportsAPI.exception.*;
import com.rdacompany.RDAsportsAPI.service.AreaService;
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
public class AreaController {

    private final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @PostMapping("/areas")
    public ResponseEntity<Area> addArea(@RequestBody Area area) throws AreaNotFoundException {
        logger.info("Inicio addArea");
        Area newArea = areaService.addArea(area);
        logger.info("Fin addArea");
        return ResponseEntity.ok(newArea);
    }

    @GetMapping("/areas")
    public ResponseEntity<List<Area>> getAreas() {
        List<Area> areas;
        areas = areaService.findAll();
        logger.info("Fin gerAreas");
        return ResponseEntity.ok(areas);
    }

    @DeleteMapping("/area/{areaId}")
    public ResponseEntity<Area> deleteArea(@PathVariable int areaId) throws AreaNotFoundException {
        logger.info("Inicio deleteArea");
        Area area = areaService.deleteArea(areaId);
        logger.info("Fin deleteArea");
        return ResponseEntity.ok(area);
    }

    @PutMapping("area/{areaId}")
    public ResponseEntity<Area> modifyArea(@RequestBody Area newArea, @PathVariable int areaId) throws AreaNotFoundException {
        logger.info("Inicio modifyArea");
        Area area = areaService.modifyArea(areaId, newArea);
        logger.info("Fin modifyArea");
        return ResponseEntity.ok(newArea);
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

    @ExceptionHandler(AreaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAreaNotFoundException(AreaNotFoundException anfe) {
        logger.info("404: Area not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(anfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }

}
