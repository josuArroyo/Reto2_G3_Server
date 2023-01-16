/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Objetivo;
import entities.ObjetivoCliente;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
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
 * @author Ale
 */
@Path("entities.objetivo")
public class ObjetivoFacadeREST {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;
    
    @EJB
    private ObjectiveInterface inter;
    
    public ObjetivoFacadeREST() {
        //super(Objetivo.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Objetivo objetivo) {
        //super.create(entity);
        try{
            inter.createObjective(objetivo);
        }catch(CreateException e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Objetivo objetivo) {
        //User user = null;
        try{
            inter.modifyObjectiveAdmin(objetivo);
        }catch(UpdateException e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("deleteObjectiveAdmin/{id}")
    public void remove(@PathParam("id") Integer id) {
        try{
            inter.deleteObjectiveAdmin(inter.filterObjectiveById(id));
        }catch(DeleteException | ReadException e){
            System.out.println(e);
            throw new InternalServerErrorException(e);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Objetivo find(@PathParam("id") Integer id) {
        try{
            return inter.filterObjectiveById(id);
        }catch(ReadException e){
           
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    
    @GET
    @Path("FindByValue/{valorParam}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<Objetivo> findByValue(@PathParam("valorParam") String valorParam){
         try{
             return inter.filterObjectiveByValue(valorParam);
         }catch(ReadException e){
             System.out.println(e.getMessage());
             throw new InternalServerErrorException(e.getMessage());
         }
     }
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Objetivo> findAll() {
        //return super.findAll();  
        try{
            return inter.viewObjective();
        }catch(ReadException e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    

    /*@GET
    @Path("{fechaCon}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ObjetivoCliente> filterObjectiveByDate(@PathParam("Date") Date fechaCon){
        try{
            return inter.filterObjectiveByDate(fechaCon);
        }catch(ReadException e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    /*@GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Set<Objetivo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }*/

    
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
