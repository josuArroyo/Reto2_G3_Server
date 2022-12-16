/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author DiegoF
 */
@Embeddable
public class ObjetivoClienteId implements Serializable{
    
    private Integer idCliente;
    private Integer idObjetivo;

    public Integer getEdad() {
        return idCliente;
    }

    public void setEdad(Integer edad) {
        this.idCliente = edad;
    }

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idCliente);
        hash = 89 * hash + Objects.hashCode(this.idObjetivo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjetivoClienteId other = (ObjetivoClienteId) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        if (!Objects.equals(this.idObjetivo, other.idObjetivo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObjetivoUserId{" + "edad=" + idCliente + ", idObjetivo=" + idObjetivo + '}';
    }
    
    
}

