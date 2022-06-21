package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Area;

import java.util.List;

public interface AreaService {
    Area addArea(Area area);

    List<Area> findAll();
}
