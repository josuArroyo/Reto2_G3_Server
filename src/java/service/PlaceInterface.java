/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Lugar;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Josu
 */
public interface PlaceInterface {

    public void createPlace(Lugar lugar) throws CreateException;

    public void deletePlace(Lugar lugar) throws DeleteException;

    public void modifyPlace(Lugar lugar) throws UpdateException;

    public List<Lugar> viewPlaces() throws ReadException;

    public Lugar viewPlacesById(Integer id) throws ReadException;

    public List<Lugar> viewPlacesByType(String tipoLugar) throws ReadException;
}
