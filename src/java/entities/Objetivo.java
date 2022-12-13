/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
public class Objetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idObjetivo;
    private String descripcion;
    private String valorParam;
    private String descriParam;

    /**
     * @associates <{uml.ObjetivoUser}>
     */
    private List<Cliente> listaClientes;

    /**
     * @associates <{uml.Entrenamiento}>
     */
    private List<Entrenamiento> listaEntrenamiento;

    public void setListaEntrenamiento(List<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    public List<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }
    
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }
    private Admin admin;




    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setValorParam(String valorParam) {
        this.valorParam = valorParam;
    }

    public String getValorParam() {
        return valorParam;
    }

    public void setDescriParam(String descriParam) {
        this.descriParam = descriParam;
    }

    public String getDescriParam() {
        return descriParam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivo != null ? idObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivo)) {
            return false;
        }
        Objetivo other = (Objetivo) object;
        if ((this.idObjetivo == null && other.idObjetivo != null) || (this.idObjetivo != null && !this.idObjetivo.equals(other.idObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Objetivo[ id=" + idObjetivo + " ]";
    }
    
}
