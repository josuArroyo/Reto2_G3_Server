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

/**
 * Esta es la interfaz encargada del entrenamiento 
 * @author Jessica.
 */
public interface EntrenamientoInterface {
    
    public void createEntrenamiento(Entrenamiento entrenamiento) throws CreateException;
    
    public List <Entrenamiento> viewAllEntrenamientos() throws ReadException;
    
    public Entrenamiento viewById(Integer idEntrenamiento) throws ReadException;
    
    public List <Entrenamiento> viewByDuration (Integer duracion) throws ReadException;
    
    public List <Entrenamiento> viewByIntensity (Integer intensidad)throws ReadException;
            
    public List <Entrenamiento> viewByObjetivo (Integer idObjetivo) throws ReadException;
    
    public void modifyEntrenamiento (Entrenamiento entrenamiento) throws UpdateException;
    
    public void deleteEntrenamiento (Entrenamiento entrenamiento) throws DeleteException;
    
    
    
    
     
}