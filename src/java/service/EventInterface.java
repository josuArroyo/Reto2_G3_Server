/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Evento;
import exceptions.DeleteException;
import exceptions.CreateException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ale
 */
public interface EventInterface {
    public void deleteEvent(Evento event) throws DeleteException;
    public List<Evento> viewEvents() throws ReadException;
    public void createEvent(Evento event) throws CreateException;
    public void modifyEvent(Evento event) throws UpdateException;
    public Evento findEventByParticipants(Integer numPart) throws ReadException;
    public Evento findEventByDate(Date fecha) throws ReadException;
    public Evento findEventByType(String tipoEvento) throws ReadException;
    public void subscribeToEvent(Evento event) throws UpdateException;
}