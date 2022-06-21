package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Activity;

import java.util.List;

public interface ActivityService {
    Activity addActivity(Activity activity);

    List<Activity> findAll();
}
