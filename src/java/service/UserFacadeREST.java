/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cipher.Cifrado;
import cipher.Hash;
import cipher.Mail;
import entities.User;
import entities.UserPrivilege;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.AbstractFacade;

/**
 *
 * @author 2dam
 */
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User user) {
        Cifrado cf = new Cifrado();
        Hash hash = new Hash();
        String text = Cifrado.desencriptar(user.getPasswd());
        text = hash.cifrarTexto(text);
        user.setPasswd(text);
        super.create(user);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, User user) {
        Cifrado cf = new Cifrado();
        Hash hash = new Hash();
        String text = Cifrado.desencriptar(user.getPasswd());
        text = hash.cifrarTexto(text);
        user.setPasswd(text);
        super.edit(user);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findUserbyLogin&Passwrd/{login}/{passwd}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUsersByLogin(@PathParam("login") String login, @PathParam("passwd") String passwd){
        try{
            
        List<User> users = null;
        Hash hash = new Hash();
        passwd = Cifrado.desencriptar(passwd);
        passwd = hash.cifrarTexto(passwd);
            return super.findUserbyLogin(login, passwd);   
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    @GET
    @Path("reset/{email}")
    @Produces({MediaType.APPLICATION_XML})
    public void resetContra(@PathParam("email") String email) throws Exception {

        List<User> users = usuarioPorEmail(email);
        if (!users.isEmpty()) {
            Mail ml = new Mail();
            Cifrado cf = new Cifrado();
            String contra = cf.generatePassword();
            String hash = cf.cipherPassword(email);
            users.get(0).setPasswd(hash);
            String mail = users.get(0).getEmail();
            Mail.sendMail(mail);
            em.merge(users.get(0));
        }
        em.flush();
    }
    
    @GET
    @Path("usuarioPorEmail/{email}")
    @Produces({MediaType.APPLICATION_XML})
    public List<User> usuarioPorEmail(@PathParam("email") String email) {
        List<User> users = null;
        try {
            users = em.createNamedQuery("usuarioPorEmail").setParameter("email", email).getResultList();
        } catch (Exception e) {

        }
        return users;
    }
    
    @GET
    @Path("findUserbyPrivilege/{userPrivilege}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUsersByPrivilege(@PathParam("userPrivilege") UserPrivilege userPrivilege){
        try{
        return super.findUserbyPrivilege(userPrivilege);
        }catch(Exception e){
            throw new InternalServerErrorException(e.getMessage());
            
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
