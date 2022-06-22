package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Activity;
import com.rdacompany.RDAsportsAPI.exception.ActivityNotFoundExcepction;

import java.util.List;

public interface ActivityService {
    Activity addActivity(Activity activity);

    List<Activity> findAll();

    Activity deleteActivity(int activityId) throws ActivityNotFoundExcepction;

    Activity modifyActivity(int activityId, Activity newActivity) throws ActivityNotFoundExcepction;
}
