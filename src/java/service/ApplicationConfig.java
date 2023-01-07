/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author 2dam
 */

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author 2dam
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AdminFacadeREST.class);
        resources.add(service.ClienteFacadeREST.class);
        resources.add(service.EntrenamientoFacadeREST.class);
        resources.add(service.EventoFacadeREST.class);
        resources.add(service.LugarFacadeREST.class);
        resources.add(service.ObjetivoClienteFacadeREST.class);
        resources.add(service.ObjetivoFacadeREST.class);
        resources.add(service.SignInFacadeREST.class);
        resources.add(service.UserFacadeREST.class);
    }
    


}