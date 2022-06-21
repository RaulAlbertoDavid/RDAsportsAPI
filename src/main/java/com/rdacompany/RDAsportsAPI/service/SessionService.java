package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.Session;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.AreaNotFoundException;

import java.util.List;

public interface SessionService {
    Session addSession(SessionDto sessionDto) throws AreaNotFoundException;

    List<Session> findAll();

    Session findBySessionId(int sessionId);

}
