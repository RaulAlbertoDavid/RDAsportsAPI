package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Activity;
import com.rdacompany.RDAsportsAPI.domain.Area;
import com.rdacompany.RDAsportsAPI.exception.ActivityNotFoundExcepction;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;
import com.rdacompany.RDAsportsAPI.repository.AreaRepository;
import org.modelmapper.ModelMapper;
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

    @Override
    public Area deleteArea(int areaId) throws AreaNotFoundException {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(AreaNotFoundException::new);
        areaRepository.delete(area);

        return area;
    }

    @Override
    public Area modifyArea(int areaId, Area newArea) throws AreaNotFoundException {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(AreaNotFoundException::new);
        ModelMapper mapper = new ModelMapper();
        Area modArea = mapper.map(newArea, Area.class);
        modArea.setAreaId(area.getAreaId());
        return areaRepository.save(area);
    }

}
