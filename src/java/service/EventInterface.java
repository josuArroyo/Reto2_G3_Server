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
import java.util.Set;

/**
 *
 * @author Ale
 */
public interface EventInterface {
    public void deleteEvent(Evento event) throws DeleteException;
    public Evento viewEvents(Evento event) throws ReadException;
    public void createEvent(Evento event) throws CreateException;
    public void modifyEvent(Evento event) throws UpdateException;
    public Set<Evento> findEventByParticipants(Evento event, Integer numPart) throws ReadException;
    public Set<Evento> findEventByDate(Evento event, Date fecha) throws ReadException;
    public Set<Evento> findEventByType(Evento event, String tipoEvento) throws ReadException;
    public void subscribeToEvent(Evento event) throws UpdateException;
}