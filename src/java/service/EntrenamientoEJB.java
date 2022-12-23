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
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
public class EntrenamientoEJB implements EntrenamientoInterface {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    @Override
    public void createEntrenamiento(Entrenamiento entrenamiento) throws CreateException {
        //Metodo para  crear entrenamientos.
        try {
            em.persist(entrenamiento);
        } catch (Exception e) {

            throw new CreateException(e.getMessage());
        }

    }

    @Override
    public Set<Entrenamiento> viewAllEntrenamientos() throws ReadException {
       //Metodo para mostrar todos los entrenamientos
        Set<Entrenamiento> entrenamiento = null;
        try {
            entrenamiento = (Set<Entrenamiento>) em.createNamedQuery("viewAllEntrenamientos").getResultList();;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return (Set<Entrenamiento>) entrenamiento;
    }

    @Override
    public Entrenamiento viewByDuration(Integer duracion) throws ReadException {
        //Metodo para mostrar los entrenamientos por la duracion
        Entrenamiento entrenamiento = null;
        try {
            entrenamiento = em.find(Entrenamiento.class, duracion);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return entrenamiento;

    }

    @Override
    public Entrenamiento viewByIntensity(Integer intensdidad) throws ReadException {
      //Metodo para mostrar los entrenamientos por la intensidad
        Entrenamiento entrenamiento = null;
        try {
            entrenamiento = em.find(Entrenamiento.class, intensdidad);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return entrenamiento;
    }

    @Override
    public Entrenamiento viewByObjetivo(Objetivo objetivo) throws ReadException {
        //Metodo para mostrar los entrenamientos pot el id de objetivo 
        Entrenamiento entrenamiento = null;
        try{
            entrenamiento = em.find(Entrenamiento.class, objetivo);
        }catch (Exception e){
            throw new ReadException(e.getMessage());
        }
        return entrenamiento;
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

}
