/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Entrenamiento;
import entities.Objetivo;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class EntrenamientoEJB implements EntrenamientoInterface {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    @Override
    public void createEntrenamiento(Entrenamiento entrenamiento) throws CreateException {
        //Metodo para  crear entrenamientos.
        try {
            if (entrenamiento.getIdEntrenamiento() == null){
                em.merge(entrenamiento);
            }
            if (!em.contains(entrenamiento.getObjetivo())) {
                em.merge(entrenamiento.getObjetivo());
            }
                
            em.persist(entrenamiento);
        } catch (Exception e) {

            throw new CreateException(e.getMessage());
        }

    }

    @Override
    public List<Entrenamiento> viewAllEntrenamientos() throws ReadException {
       //Metodo para mostrar todos los entrenamientos
        List<Entrenamiento> entrenamiento = null;
        try {
            entrenamiento = em.createNamedQuery("viewAllTraining").getResultList();;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return entrenamiento;
    }

    @Override
    public List <Entrenamiento> viewByDuration(Integer duracion) throws ReadException {
        //Metodo para mostrar los entrenamientos por la duracion
        List <Entrenamiento> list;
        try {
            list = em.createNamedQuery("viewByDuration").setParameter("duracion", duracion).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return list;

    }

    @Override
    public List <Entrenamiento> viewByIntensity(Integer intensidad) throws ReadException {
      //Metodo para mostrar los entrenamientos por la intensidad
        List <Entrenamiento> list;
        try {
            list = em.createNamedQuery("viewByIntensity").setParameter("intensidad", intensidad).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return list;
    }

    @Override
    public List <Entrenamiento> viewByObjetivo(Integer idObjetivo) throws ReadException {
        //Metodo para mostrar los entrenamientos pot el id de objetivo 
        List <Entrenamiento> list;
        try{
            list = em.createNamedQuery("viewByObjective").setParameter("objetivo", em.find(Objetivo.class, idObjetivo)).getResultList();
        }catch (Exception e){
            throw new ReadException(e.getMessage());
        }
        return list;
    }

    @Override
    public void modifyEntrenamiento(Entrenamiento entrenamiento) throws UpdateException {
        //Metodo para modificar los entrenamientos 
         try{
            if(!em.contains(entrenamiento))
                em.merge(entrenamiento);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deleteEntrenamiento(Entrenamiento entrenamiento) throws DeleteException {
        //Metodo para borrar los entrenamientos
        try{
            em.remove(em.merge(entrenamiento));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public Entrenamiento viewById(Integer idEntrenamiento) throws ReadException {
        Entrenamiento entrenamiento = null;
        try {
            entrenamiento = em.find(Entrenamiento.class, idEntrenamiento);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return entrenamiento;    }

}
