/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2dam
 */

@NamedQueries({
    @NamedQuery(name = "viewUsersByLogin&asswd", query = "SELECT u FROM User u WHERE u.login = :login AND u.passwd = :passwd")
    ,
    @NamedQuery(name = "filterUserByPrivilege",
            query = "SELECT u FROM User u WHERE u.userPrivilege = :userPrivilege")
})
@Entity
@DiscriminatorColumn(name = "privilege", discriminatorType = DiscriminatorType.STRING)
@Table(name="User", schema="Fuerza_G3")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    @Column(unique = true)
    private String login;
    private String nombre;
    private String email;
    private String passwd;
    private String confPasswd;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "privilege", insertable= false, updatable=false)
    private UserPrivilege userPrivilege;
    
    
    public Integer getId() {
        return idUser;
    }
    
    public void setId(Integer id) {
        this.idUser = id;
    }
    
        public User() {
        super();
    }
    
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserPrivilege getPrivilege() {
        return userPrivilege;
    }

    public void setPrivilege(UserPrivilege privilege) {
        this.userPrivilege = privilege;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setConfPasswd(String confPasswd) {
        this.confPasswd = confPasswd;
    }

    public String getConfPasswd() {
        return confPasswd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + idUser + " ]";
    }
    
}
