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


/**
 *
 * @author Diego
 */
public interface ObjectiveInterface {
    
    public void createObjective(Objetivo objetivo) throws CreateException ;
    
    public void modifyObjectiveAdmin(Objetivo objetivo) throws UpdateException;
    
    public void modifyObjectiveClient(Objetivo objetivo) throws UpdateException;
    
    public void deleteObjectiveAdmin(Objetivo objetivo) throws DeleteException;
    
    public void deleteObjectiveClient(Objetivo objetivo) throws DeleteException;
    
    public List<Objetivo> viewObjective() throws ReadException;
    
    public Objetivo filterObjectiveById(Integer id) throws ReadException;
    
    public List<Objetivo> filterObjectiveByValue(String valorParam) throws ReadException;
    
    
    
}
