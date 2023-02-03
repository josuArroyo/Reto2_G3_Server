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
import static java.lang.reflect.Array.set;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Este es ek EJB de Lugar
 * @author Josu
 */
@Stateless
public class LugarEJB implements PlaceInterface {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    @Override
    public void createPlace(Lugar lugar) throws CreateException {

        try {
            em.persist(lugar);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void deletePlace(Lugar lugar) throws DeleteException {

        try {
            em.remove(em.merge(lugar));

        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public void modifyPlace(Lugar lugar) throws UpdateException {

        try {
            if (!em.contains(lugar)) 
                em.merge(lugar);   
            em.flush();
            
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public List<Lugar> viewPlaces() throws ReadException {
        List<Lugar> lugar;
        try {
            lugar =em.createNamedQuery("findAllLocations").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return lugar;
    }

    
    @Override
    public Lugar viewPlacesById(Integer id) throws ReadException {
        Lugar lugar;
        try {
            lugar=em.find(Lugar.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return lugar;

    }

    @Override
    public List<Lugar> viewPlacesByType(String tipoLugar) throws ReadException {
        List<Lugar> lugar;
        try {
            lugar =em.createNamedQuery("findAllLocationsByType").setParameter("tipoLugar", tipoLugar).getResultList();
            //lugar=em.find(Lugar.class, tipoLugar);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        
        return lugar;
    }


    
}

