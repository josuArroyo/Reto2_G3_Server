/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Jessica
 */

@Entity
@Table(name="entrenamiento", schema="Fuerza_g3")
public class Entrenamiento implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEntrenamiento;
    
    @NotNull
    private String descripcion;
    
    @NotNull
    private Integer duracion;
    
    @NotNull
    @Pattern(regexp = "^\\d(4)-\\d(2)-\\d(2)$")
    @Temporal (TemporalType.DATE)
    private Date fechaPeriod;
    
    @NotNull
    private Integer intensidad;
    
    @NotNull
    private Integer repeticion;
    
    @NotNull
    @ManyToOne
    private Objetivo objetivo;
    
    @NotNull
    @ManyToMany()
    @JoinTable(name="admin_entrenamieto", schema="Fuerza_g3")
    private Admin admin;


    public void setIdEntrenamiento(Integer idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }
    
    public Integer getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setFechaPeriod(Date fechaPeriod) {
        this.fechaPeriod = fechaPeriod;
    }

    public Date getFechaPeriod() {
        return fechaPeriod;
    }

    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    public Integer getIntensidad() {
        return intensidad;
    }

    public void setRepeticion(Integer repeticion) {
        this.repeticion = repeticion;
    }

    public Integer getRepeticion() {
        return repeticion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrenamiento != null ? idEntrenamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrenamiento)) {
            return false;
        }
        Entrenamiento other = (Entrenamiento) object;
        if ((this.idEntrenamiento == null && other.idEntrenamiento != null) || (this.idEntrenamiento != null && !this.idEntrenamiento.equals(other.idEntrenamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Entrenamiento[ id=" + idEntrenamiento + " ]";
    }
    
}
