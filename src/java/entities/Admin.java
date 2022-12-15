/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ale, 
 */
@Entity
@DiscriminatorValue("AD")
public class Admin extends User {

    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    
    private String licencia;
    private User user;

    /**
     * @associates <{uml.Evento}>
     */
    @OneToMany(mappedBy="admin")
    private Set<Evento> listaEvento;

    /**
     * @associates <{uml.Lugar}>
     */
    private Set<Lugar> listaLugar;

    /**
     * @associates <{uml.Entrenamiento}>
     */
    private Set<Entrenamiento> listaEntrenamiento;

    /**
     * @associates <{uml.Objetivo}>
     */
    private Set<Objetivo> listaObjetivo;


    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setListaEvento(Set<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    public Set<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setListaLugar(Set<Lugar> listaLugar) {
        this.listaLugar = listaLugar;
    }

    public Set<Lugar> getListaLugar() {
        return listaLugar;
    }

    public void setListaEntrenamiento(Set<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    public Set<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }

    public void setListaObjetivo(Set<Objetivo> listaObjetivo) {
        this.listaObjetivo = listaObjetivo;
    }

    public Set<Objetivo> getListaObjetivo() {
        return listaObjetivo;
    }

   
    @Override
    public String toString() {
        return "entities.Admin[ id=" + licencia + " ]";
    }
    
}