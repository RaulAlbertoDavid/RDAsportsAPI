package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;
import com.rdacompany.RDAsportsAPI.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sesions")
    public Session addSession (@RequestBody SessionDto sessionDto) throws AreaNotFoundException {
        logger.info("Inicio addSession");
        Session newSession = sessionService.addSession(sessionDto);
        logger.info("Fin addSession");
        return newSession;
    }

    @GetMapping("/sessions")
    public List<Session> getSessions() {
        List<Session> sessions;

        sessions = sessionService.findAll();

        return sessions;
    }

}
