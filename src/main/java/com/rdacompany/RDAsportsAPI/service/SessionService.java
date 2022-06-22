package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;
import com.rdacompany.RDAsportsAPI.exception.SessionNotFoundException;

import java.util.List;

public interface SessionService {
    Session addSession(SessionDto sessionDto) throws AreaNotFoundException;

    List<Session> findAll();

    Session findBySessionId(int sessionId);

    Session deleteSession(int sessionId) throws SessionNotFoundException;

    /*Session modifySession(int sessionId, Session session) throws SessionNotFoundException;*/
}
