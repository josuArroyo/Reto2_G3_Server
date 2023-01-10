/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Lugar;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
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
@Path("entities.lugar")
public class LugarFacadeREST {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    @EJB
    private PlaceInterface pli;

    Lugar lugar = new Lugar();

    public LugarFacadeREST() {

    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Lugar lugar) throws CreateException {
        try {
            pli.createPlace(lugar);
        } catch (CreateException ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id,Lugar lugar) {
        try {
            pli.modifyPlace(lugar);
        } catch (UpdateException ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @DELETE
    @Path("deletePlace/{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            pli.deletePlace(pli.viewPlacesById(id));
        } catch (ReadException | DeleteException ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Lugar find(@PathParam("id") Integer id) {
        try {
            return (Lugar) pli.viewPlacesById(id);
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Lugar> findAll() {
        try {
            return pli.viewPlaces();
        } catch (ReadException ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }
    
    @GET
    @Path("findByType/{tipoLugar}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Lugar> findByType(@PathParam("tipoLugar") String tipoLugar) {
        try {
            return pli.viewPlacesByType(tipoLugar);
        } catch (ReadException ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }
    

    /*
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Lugar> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
     */
    protected EntityManager getEntityManager() {
        return em;
    }

}