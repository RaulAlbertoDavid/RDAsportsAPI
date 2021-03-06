package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.*;

import java.util.List;

public interface SessionService {
    Session addSession(SessionDto sessionDto) throws AreaNotFoundException, ActivityNotFoundExcepction, EmployeeNotFoundException;

    List<Session> findAll();

    Session findBySessionId(int sessionId) throws SessionNotFoundException;

    Session deleteSession(int sessionId) throws SessionNotFoundException;

    Session modifySession(int sessionId, SessionDto newSessionDto) throws SessionNotFoundException, ActivityNotFoundExcepction, AreaNotFoundException, EmployeeNotFoundException;

    void dropOut(int customerId, int sessionId) throws CustomerNotFoundException, SessionNotFoundException;
}
