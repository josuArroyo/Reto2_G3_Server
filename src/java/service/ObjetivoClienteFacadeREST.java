/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.ObjetivoCliente;
import entities.ObjetivoClienteId;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 * Este es el facadeREST de ObjetivoCliente  en el cual les ponemos las path a las consultas 
 * @author Diego.
 */
@Stateless
@Path("entities.objetivocliente")
public class ObjetivoClienteFacadeREST extends AbstractFacade<ObjetivoCliente> {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    private ObjetivoClienteId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idUser=idUserValue;idObjetivo=idObjetivoValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.ObjetivoClienteId key = new entities.ObjetivoClienteId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idUser = map.get("idUser");
        if (idUser != null && !idUser.isEmpty()) {
            key.setIdUser(new java.lang.Integer(idUser.get(0)));
        }
        java.util.List<String> idObjetivo = map.get("idObjetivo");
        if (idObjetivo != null && !idObjetivo.isEmpty()) {
            key.setIdObjetivo(new java.lang.Integer(idObjetivo.get(0)));
        }
        return key;
    }

    public ObjetivoClienteFacadeREST() {
        super(ObjetivoCliente.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ObjetivoCliente entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, ObjetivoCliente entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entities.ObjetivoClienteId key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ObjetivoCliente find(@PathParam("id") PathSegment id) {
        entities.ObjetivoClienteId key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ObjetivoCliente> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ObjetivoCliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}