/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Evento;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 * Este es ek EJB de Evento 
 * @author Ale..
 */
@Stateless
public class EventEJB implements EventInterface{

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;
    
    @Override
    public void deleteEvent(Evento event) throws DeleteException {
        try {
            em.remove(em.merge(event));
        }catch(Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public List<Evento> viewEvents() throws ReadException {
        List<Evento> events = null;
        try {
            events = em.createNamedQuery("viewAllEvents").getResultList();
        }catch(Exception e) {
            throw new ReadException(e.getMessage());
        }
        return events;
    }

    @Override
    public void createEvent(Evento event) throws CreateException {
        try {
            em.persist(event);
        }catch(Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void modifyEvent(Evento event) throws UpdateException {
        try {
            if(!em.contains(event))
                em.merge(event);
            em.flush();
        }catch(Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public List<Evento> findEventByEventId(Integer idEvento) throws ReadException {
        List<Evento> events;
        try {
            events = em.createNamedQuery("findEventByEventId").setParameter("idEvento", idEvento).getResultList();
        }catch(Exception e) {
            throw new ReadException(e.getMessage());
        }
        return events;
    }

    @Override
    public List<Evento> findEventByType(String tipoEvento) throws ReadException {
        List<Evento> events;
        try {
            events = em.createNamedQuery("findEventByType").setParameter("tipoEvento", tipoEvento).getResultList();
        }catch(Exception e) {
            throw new ReadException(e.getMessage());
        }
        return events; 
    }

    @Override
    public Evento filterEventById(Integer idEvento) throws ReadException {
      Evento event;
      try {
          event = em.find(Evento.class, idEvento);
      }catch(Exception e) {
          throw new ReadException(e.getMessage());
      }
        return event;
    }
}