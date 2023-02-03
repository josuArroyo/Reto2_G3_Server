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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Este es el facadeREST de Evento en el cual les ponemos las path a las consultas 
 * @author Ale.
 */
@Path("entities.evento")
public class EventoFacadeREST {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    @EJB
    private EventInterface inter;
    
    public EventoFacadeREST() {

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createEvent(@PathParam("idEvento") Integer idEvento, Evento event) {        
        try {
            inter.createEvent(event);
        } catch (CreateException e) {
             throw new InternalServerErrorException(e.getMessage());
        }       
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifyEvent(@PathParam("idEvento") Integer idEvento, Evento event) {
        try {
            inter.modifyEvent(event);
        } catch (UpdateException e) {
            System.out.println(e);
             throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("DELETE-Evento/{idEvento}")
    public void deleteEvent(@PathParam("idEvento") Integer idEvento) {
        try {
            inter.deleteEvent(inter.filterEventById(idEvento));
        }catch(DeleteException | ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> viewEvents() {
        try {
          return inter.viewEvents();
        }catch(ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        } 
    }
    
    @GET
    @Path("FindEventBy/{idEvento}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Evento> findEventByEventId(@PathParam("idEvento") Integer idEvento) {
        try {
          return inter.findEventByEventId(idEvento);
        }catch(ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        } 
    }
    
    @GET
    @Path("FindEventBy/String/{tipoEvento}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Evento> findEventByType(@PathParam("tipoEvento") String tipoEvento) {
        try {
          return inter.findEventByType(tipoEvento);
        }catch(ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        } 
    }


    protected EntityManager getEntityManager() {
        return em;
    }
    
}

