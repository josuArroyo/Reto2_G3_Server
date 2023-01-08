/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.ObjetivoCliente;
import exceptions.ReadException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Stateless
public class ObjetivoClienteEJB implements ObjectiveClientInterface{

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;
    
    public ObjetivoCliente filterObjectiveByDate(Date fechaCon) throws ReadException {
        //This method shows info from ObjetivoCLiente by it´s fechaCon Date parameter.
        ObjetivoCliente objetivoCliente;
        try{
            objetivoCliente =
                    em.find(ObjetivoCliente.class, fechaCon);
        }catch(Exception e){
            System.out.println(e);
            throw new ReadException(e.getMessage());
        }
        return objetivoCliente;
    }

    @Override
    public List<ObjetivoCliente> viewObjectiveClient() throws ReadException {
        List<ObjetivoCliente> objetivoClie = null;
        try{
            objetivoClie = 
                     em.createNamedQuery("viewObjective").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return objetivoClie;
    }
    
}
