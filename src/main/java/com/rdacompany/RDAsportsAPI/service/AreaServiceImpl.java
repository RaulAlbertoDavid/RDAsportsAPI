package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Area;
import com.rdacompany.RDAsportsAPI.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaRepository areaRepository;
    @Override
    public Area addArea(Area area) {
        return areaRepository.save(area);
    }

    @Override
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

}
