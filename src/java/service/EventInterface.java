/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package service;

import entities.Evento;
import entities.User;
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
public Evento filterEventById(Integer idEvento) throws ReadException;
public void deleteEvent(Evento event) throws DeleteException;
public List<Evento> viewEvents() throws ReadException;
public void createEvent(Evento event) throws CreateException;
public void modifyEvent(Evento event) throws UpdateException;
public List<Evento> findEventByEventId(Integer idEvento) throws ReadException;
public List<Evento> findEventByType(String tipoEvento) throws ReadException;
}