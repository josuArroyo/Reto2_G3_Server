/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Evento;
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
 *
 * @author 2dam
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
/*
    //mal???
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void subscribeToEvent(Integer numPart) {
        try {
            inter.subscribeToEvent(numPart);
        } catch (UpdateException e) {
            System.out.println(e);
            throw new InternalServerErrorException(e.getMessage());
        }
    }
*/
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
    @Path("/GET/Integer{numPart}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Evento findEventByParticipants(@PathParam("numPart") Integer numPart) {
        try {
          return inter.findEventByParticipants(numPart);
        }catch(ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        } 
    }
    
    @GET
    @Path("/GET/Date{fecha}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Evento findEventByDate(@PathParam("fecha") Date fecha) {
        try {
          return inter.findEventByDate(fecha);
        }catch(ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        } 
    }
    
    @GET
    @Path("/GET/String{tipoEvento}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Evento findEventByType(@PathParam("tipoEvento") String tipoEvento) {
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