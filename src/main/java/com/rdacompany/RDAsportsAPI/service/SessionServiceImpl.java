package com.rdacompany.RDAsportsAPI.service;

import com.rdacompany.RDAsportsAPI.domain.*;
import com.rdacompany.RDAsportsAPI.domain.dto.SessionDto;
import com.rdacompany.RDAsportsAPI.exception.*;
import com.rdacompany.RDAsportsAPI.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Session addSession(SessionDto sessionDto) throws AreaNotFoundException {
        //tengo que recuperar los objetos enteros para pasarlos a la base de datos no solo el id
        Area area = areaRepository.findById(sessionDto.getArea())
                .orElseThrow(AreaNotFoundException::new);
        Activity activity = activityRepository.findById(sessionDto.getActivity())
                .orElseThrow(AreaNotFoundException::new);
        Employee employee = employeeRepository.findById(sessionDto.getEmployee())
                .orElseThrow(AreaNotFoundException::new);
        /*
        vamos a utilizar un mapeador(introducir dependencia modelmapper) para no tener que ir indicandolo al
        nuevo objeto todos los atributos
        Le indico mapeame el objeto que te indico en la nueva session con lo que debe tener cualquier sesion y ya cojes la
        información y yo le indico a mayores los objetos completos que no estan en el DTO, no solo los id.
        Con el map me ahorro los get set para poder crear un objeto. Solo mapea lo que es común a ambos objetos y
        lo demas lo ignora.
         */
        ModelMapper mapper = new ModelMapper();
        Session session = mapper.map(sessionDto, Session.class);
        session.setArea(area);
        session.setActivity(activity);
        session.setEmployee(employee);

        return sessionRepository.save(session);
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findBySessionId(int sessionId) throws SessionNotFoundException {
        return sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public Session deleteSession(int sessionId) throws SessionNotFoundException {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(SessionNotFoundException::new);
        session.getCustomers().clear();
        sessionRepository.delete(session);
        return session;
    }

   @Override
   public Session modifySession(int sessionId, SessionDto newSessionDto) throws SessionNotFoundException, EmployeeNotFoundException, ActivityNotFoundExcepction, AreaNotFoundException {
       Session session = sessionRepository.findById(sessionId)
                .orElseThrow(SessionNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Session newSession = mapper.map(newSessionDto, Session.class);
        newSession.setSessionId(session.getSessionId());
        Employee employee = employeeRepository.findById(newSessionDto.getEmployee())
                .orElseThrow(EmployeeNotFoundException::new);
        Activity activity = activityRepository.findById(newSessionDto.getActivity())
                .orElseThrow(ActivityNotFoundExcepction::new);
        Area area = areaRepository.findById(newSessionDto.getArea())
                .orElseThrow(AreaNotFoundException::new);
        newSession.setEmployee(employee);
        newSession.setActivity(activity);
        newSession.setArea(area);
        return sessionRepository.save(newSession);
    }

    @Override
    public void dropOut(int customerId, int sessionId) throws CustomerNotFoundException, SessionNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Session session = sessionRepository.findById(sessionId).orElseThrow(SessionNotFoundException::new);

        session.getCustomers().remove(customer);
        sessionRepository.save(session);
    }

}
