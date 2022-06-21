package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Activity;
import com.rdacompany.RDAsportsAPI.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @PostMapping("/activities")
    public Activity addActivity (@RequestBody Activity activity) {
        logger.info("Inicio addActivity");
        Activity newActivity = activityService.addActivity(activity);
        logger.info("Fin addActivity");
        return newActivity;

    }

    @GetMapping("/activities")
    public List<Activity> getActivities() {
        List<Activity> activities;

            activities = activityService.findAll();

        return activities;
    }
}
