package com.rdacompany.RDAsportsAPI.controller;

import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.ActivityNotFoundExcepction;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;
import com.rdacompany.RDAsportsAPI.exception.EmployeeNotFoundException;
import com.rdacompany.RDAsportsAPI.exception.SessionNotFoundException;
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

    @PostMapping("/sessions")
    public Session addSession (@RequestBody SessionDto sessionDto) throws SessionNotFoundException, AreaNotFoundException {
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

    @GetMapping("/session/{sessionId}")
    public Session getSession (@PathVariable int sessionId) throws SessionNotFoundException{
        Session session = sessionService.findBySessionId(sessionId);
        return session;
    }

    @DeleteMapping("/session/{sessionId}")
    public Session deletSession (@PathVariable int sessionId) throws SessionNotFoundException{
        logger.info("Inicio deleteSession");
        Session session = sessionService.deleteSession(sessionId);
        logger.info("Fin deleteSession");
        return session;
    }

    @PutMapping("/session/{sessionId}")
    public Session modifySession(@RequestBody SessionDto newSessionDto, @PathVariable int sessionId) throws SessionNotFoundException, AreaNotFoundException, ActivityNotFoundExcepction, EmployeeNotFoundException {
        logger.info("Inicio modifySession");
        Session session = null;
        try {
            session = sessionService.modifySession(sessionId, newSessionDto);
        } catch (com.rdacompany.RDAsportsAPI.exception.EmployeeNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Inicio modifySession");
        return  session;
    }


}
