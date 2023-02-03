/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Entrenamiento;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
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
 * Este es el facadeREST de Entrenamiento en el cual les ponemos las path a las consultas 
 * @author Jessica..
 */
@Path("entities.entrenamiento")
public class EntrenamientoFacadeREST {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;
    
    @EJB
    private EntrenamientoInterface einterface;
    

    Entrenamiento entrenamiento = new Entrenamiento();
    
    public EntrenamientoFacadeREST() {
       
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Entrenamiento entrenamiento) {
        try {
            einterface.createEntrenamiento(entrenamiento);
        } catch (CreateException ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Entrenamiento entrenamiento) {
         try {
            einterface.modifyEntrenamiento(entrenamiento);
        } catch (UpdateException ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("deleteTraining/{id}")
    public void remove(@PathParam("id") Integer id) {
            try {
            einterface.deleteEntrenamiento(einterface.viewById(id));
        } catch (DeleteException ex) {
            throw new InternalServerErrorException(ex.getMessage());
        } catch (ReadException ex) {
            Logger.getLogger(EntrenamientoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    @GET
    @Path("getEntrenamientoId/{idEntrenamiento}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Entrenamiento findById(@PathParam("idEntrenamiento") Integer id) {
        Entrenamiento lista;
          try {
              lista = einterface.viewById(id);
            return  lista;
        } catch (ReadException ex) {
              System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    @GET
    @Path("getEntrenamientoObjetivo/{idObjetivo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Entrenamiento> findObjetivo(@PathParam("idObjetivo") Integer id) {
        List<Entrenamiento> lista;
          try {
              lista = einterface.viewByObjetivo(id);
            return  lista;
        } catch (ReadException ex) {
              System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    @GET
    @Path("getEntrenamientoInsensidad/{intensidad}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Entrenamiento> findIntensidad(@PathParam("intensidad") Integer id) {
        List<Entrenamiento> lista;
          try {
              lista = einterface.viewByIntensity(id);
            return  lista;
        } catch (ReadException ex) {
              System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("getEntrenamientoDuracion/{duracion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public List<Entrenamiento> findDuracion(@PathParam("duracion") Integer id) {
        List<Entrenamiento> lista;
          try {
              lista = einterface.viewByDuration(id);
            return  lista;
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Entrenamiento> findAll() {
        try {
            return einterface.viewAllEntrenamientos();
        } catch (ReadException ex) {
           System.out.println(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    
    
    
    
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Entrenamiento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    }


    protected EntityManager getEntityManager() {
        return em;
    }
    
}
