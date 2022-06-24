package com.rdacompany.RDAsportsAPI.repository;

import com.rdacompany.RDAsportsAPI.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Integer> {

    Session findBySessionId(int sessionId);

    List<Session> findAll();
}



