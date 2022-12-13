/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 2dam
 */
@Entity
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLugar;
    private String nombre;
    private String descripcion;
    private String tipoLugar;
    private Date tiempo;

    /**
     * @associates <{uml.Evento}>
     */
    private List<Evento> listaEvento;
    private Admin admin;


 

    public void setListaEvento(List<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    public List<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }
    
    
    public Integer getIdLugar() {
        return idLugar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public Date getTiempo() {
        return tiempo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLugar != null ? idLugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.idLugar == null && other.idLugar != null) || (this.idLugar != null && !this.idLugar.equals(other.idLugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lugar[ id=" + idLugar + " ]";
    }
    
}
