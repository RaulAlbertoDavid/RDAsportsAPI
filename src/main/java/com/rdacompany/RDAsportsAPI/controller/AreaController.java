package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Area;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;
import com.rdacompany.RDAsportsAPI.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreaController {

    private final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @PostMapping("/areas")
    public Area addArea(@RequestBody Area area) throws AreaNotFoundException {
        logger.info("Inicio addArea");
        Area newArea = areaService.addArea(area);
        logger.info("Fin addArea");
        return newArea;
    }

    @GetMapping("/areas")
    public List<Area> getAreas() {
        List<Area> areas;
        areas = areaService.findAll();

        return areas;
    }

}
