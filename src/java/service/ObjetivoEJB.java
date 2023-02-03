/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Objetivo;
import entities.ObjetivoCliente;
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
 * Este es el EJB de lugar 
 * @author Diego
 */
@Stateless
public class ObjetivoEJB implements ObjectiveInterface {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;
    
    @Override
    //This method is used to make sure that the objectives are created
    public void createObjective(Objetivo objetivo) throws CreateException {
        try{
            em.persist(objetivo);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    //This metod makes sure that an objective already exists before updating it, if so, it changes the data into our database
    public void modifyObjectiveAdmin(Objetivo objetivo) throws UpdateException {
       try{
           if(!em.contains(objetivo))
               em.merge(objetivo);
           em.flush();
       }catch(Exception e){
           throw new UpdateException(e.getMessage());
       }
    }

    @Override
    //This metod makes sure that an objective already exists before updating it, if so, it changes the data into our database
    public void modifyObjectiveClient(Objetivo objetivo) throws UpdateException{
         try{
           if(!em.contains(objetivo))
               em.merge(objetivo);
           em.flush();
       }catch(Exception e){
           throw new UpdateException(e.getMessage());
       }
    }

    @Override
    //This method deletes an entity´s content table 
    public void deleteObjectiveAdmin(Objetivo objetivo) throws DeleteException {
        try{
            em.remove(em.merge(objetivo));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public void deleteObjectiveClient(Objetivo objetivo) throws DeleteException {
        try{
            em.remove(em.merge(objetivo));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
    
    @Override
    //This method shows all objetives
    public List<Objetivo> viewObjective() throws ReadException {
        List<Objetivo> objetivo = null;
        try{
            objetivo = 
                     em.createNamedQuery("viewObjective").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return objetivo;
    }

    @Override
    //This method is used to view the objectives by it´s ids
    public Objetivo filterObjectiveById(Integer id) throws ReadException {
        Objetivo objetivo;
        try{
             objetivo = em.find(Objetivo.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
            
        }
        return objetivo;
    }

    @Override
    public List<Objetivo> filterObjectiveByValue(String valorParam) throws ReadException {
       List<Objetivo> objetivo;
       try{
           objetivo = 
                   em.createNamedQuery("filterObjectiveByValue").setParameter("valorParam", valorParam).getResultList();
       }catch(Exception e){
           throw new ReadException(e.getMessage());
       }
       return objetivo;
    }

    
     
}