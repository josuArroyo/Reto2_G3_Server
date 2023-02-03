/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import entities.UserPrivilege;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;

/**
 * esta es la interfaz abstracta que es la encargada de controlar al admin y al
 * cliente
 *
 * @author grupo3c.
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    @PersistenceContext(unitName = "Reto2_G3_ServerPU")
    private EntityManager em;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

 
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<User> findUserbyLogin(String login, String passwd) {

        List<User> users = null;
        try {
            users = em.createNamedQuery("viewUsersByLogin&asswd", User.class).setParameter("login", login).setParameter("passwd", passwd).getResultList();
            if (users.isEmpty()) {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return users;
    }

    public List<User> findUserbyPrivilege(UserPrivilege userPrivilege) {
        return em.createNamedQuery("filterUserByPrivilege", User.class).setParameter("userPrivilege", userPrivilege).getResultList();
    }
}