package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Activity;
import com.rdacompany.RDAsportsAPI.exception.*;
import com.rdacompany.RDAsportsAPI.service.ActivityService;
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
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @PostMapping("/activities")
    public ResponseEntity<Activity> addActivity (@RequestBody Activity activity) {
        logger.info("Inicio addActivity");
        Activity newActivity = activityService.addActivity(activity);
        logger.info("Fin addActivity");
        return ResponseEntity.ok(newActivity);

    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivities() {
        List<Activity> activities;
        activities = activityService.findAll();
        logger.info("Fin getActivity");
        return ResponseEntity.ok(activities);
    }

    @DeleteMapping("/activity/{activityId}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable int activityId) throws ActivityNotFoundExcepction {
        logger.info("Inicio deleteActivity");
        Activity activity = activityService.deleteActivity(activityId);
        logger.info("Fin deleteActivity");
        return ResponseEntity.ok(activity);
    }

    @PutMapping("activity/{activityId}")
    public ResponseEntity<Activity> modifyActivity(@RequestBody Activity newActivity, @PathVariable int activityId) throws ActivityNotFoundExcepction {
        logger.info("Inicio modifyActivity");
        Activity activity = activityService.modifyActivity(activityId, newActivity);
        logger.info("Fin modifyActivity");
        return ResponseEntity.ok(newActivity);
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

    @ExceptionHandler(ActivityNotFoundExcepction.class)
    public ResponseEntity<ErrorResponse> handleActivityNotFoundException(ActivityNotFoundExcepction anfe) {
        logger.info("404: Activity not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(anfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }
}
