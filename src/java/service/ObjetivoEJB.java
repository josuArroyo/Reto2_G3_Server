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
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego
 */
/*public class ObjetivoEJB implements ObjectiveInterface {

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

    @Override
    public Objetivo viewObjective(Objetivo objetivo) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<ObjetivoCliente> filterObjectiveByDate(Date fechaCon) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjetivoCliente selectObjectiveClient(Objetivo objetivo, ObjetivoCliente objetivoCliente) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //This method shows info from ObjetivoCLiente by it´s fechaCon Date parameter, returns ObjetivoCliente
    public Set<ObjetivoCliente> filterObjectiveByDate(Date fechaCon) throws ReadException {
        Set<ObjetivoCliente> objetivoClientes;
        try{
           //objetivoClientes = em.createQuery("filterObjectivesByDate").setParameter("fechaCon", em.find(objetivoClientes, fechaCon));
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        
        return objetivoClientes;
    }

    
    public Objetivo viewObjective(Objetivo objetivo) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjetivoCliente selectObjectiveClient(Objetivo objetivo, ObjetivoCliente objetivoCliente) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}*/
