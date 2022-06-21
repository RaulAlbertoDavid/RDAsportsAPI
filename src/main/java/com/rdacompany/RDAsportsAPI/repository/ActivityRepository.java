package com.rdacompany.RDAsportsAPI.repository;

import com.rdacompany.RDAsportsAPI.domain.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

    List<Activity> findAll();

}
