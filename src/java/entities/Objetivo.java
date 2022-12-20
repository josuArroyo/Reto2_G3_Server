/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego Y Jessica
 */

@Entity
@Table(name="objetivo", schema="Fuerza_G3")
@XmlRootElement
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
    //Relaciones
    @OneToMany(mappedBy="ObjetivoCliente")
    private Set<Cliente> listaClientes;

    
    /**
     * @associates <{uml.Entrenamiento}>
     */
    @OneToMany(mappedBy="Entrenamiento")
    private Set<Entrenamiento> listaEntrenamiento;
    
    @ManyToOne
    private Admin admin;

    public void setListaEntrenamiento(Set<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    @XmlTransient
    public Set<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }
    
    public void setListaClientes(Set<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @XmlTransient
    public Set<Cliente> getListaClientes() {
        return listaClientes;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }
    


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
