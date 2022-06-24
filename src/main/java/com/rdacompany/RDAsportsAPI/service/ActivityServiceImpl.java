package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Activity;
import com.rdacompany.RDAsportsAPI.exception.ActivityNotFoundExcepction;
import com.rdacompany.RDAsportsAPI.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity deleteActivity(int activityId) throws ActivityNotFoundExcepction {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(ActivityNotFoundExcepction::new);
        activityRepository.delete(activity);

        return activity;
    }

    @Override
    public Activity modifyActivity(int activityId, Activity newActivity) throws ActivityNotFoundExcepction {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(ActivityNotFoundExcepction::new);
        ModelMapper mapper = new ModelMapper();
        Activity modActivity = mapper.map(newActivity, Activity.class);
        modActivity.setActivityId(activity.getActivityId());
        return activityRepository.save(activity);

    }

}
