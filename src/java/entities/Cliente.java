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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author 2dam
 */
@Entity
@DiscriminatorValue("CL")
@Table(name="cliente",schema="Fuerza_g3")
public class Cliente extends User {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)

    private int edad;
   

    /**
     * @associates <{uml.ObjetivoUser}>
     */
    private Set<Objetivo> listaObjetivo;

    /**
     * @associates <{uml.Evento}>
     */
    @ManyToMany(mappedBy="listaCliente")
    private Set<Evento> listaEvento;

    public void setListaObjetivo(Set<Objetivo> listaObjetivo) {
        this.listaObjetivo = listaObjetivo;
    }

    
    public Set<Objetivo> getListaObjetivo() {
        return listaObjetivo;
    }

    public void setListaEvento(Set<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    public Set<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }


    @Override
    public String toString() {
        return "entities.Cliente[ id=" + edad + " ]";
    }

}
