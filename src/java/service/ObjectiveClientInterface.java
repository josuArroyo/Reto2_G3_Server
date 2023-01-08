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

/**
 *
 * @author Diego
 */
public interface ObjectiveClientInterface {
    
    public List<ObjetivoCliente> viewObjectiveClient() throws ReadException;
    
    public ObjetivoCliente filterObjectiveByDate(Date fechaCon) throws ReadException;
}
