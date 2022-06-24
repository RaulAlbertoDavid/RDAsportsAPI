package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Area;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;

import java.util.List;

public interface AreaService {
    Area addArea(Area area);

    List<Area> findAll();

    Area deleteArea(int areaId) throws AreaNotFoundException;

    Area modifyArea(int areaId, Area newArea) throws AreaNotFoundException;
}
