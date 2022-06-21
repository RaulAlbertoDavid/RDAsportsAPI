package com.rdacompany.RDAsportsAPI.repository;

import com.rdacompany.RDAsportsAPI.domain.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends CrudRepository<Area, Integer> {

    List<Area> findAll();


}
