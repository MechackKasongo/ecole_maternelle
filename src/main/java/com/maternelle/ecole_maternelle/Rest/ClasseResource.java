/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.maternelle.ecole_maternelle.Rest;

import com.maternelle.ecole_maternelle.Bean.ClasseBean;
import com.maternelle.ecole_maternelle.Entities.Classe;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST Web Service
 *
 * @author mechack-kasongo
 */
@Path("/classes")
@Produces(MediaType.APPLICATION_JSON)
public class ClasseResource {
    @Inject
    private ClasseBean classeBean;

    @GET
    @Path("/{id}")
    public Classe getClasse(@PathParam("id") int id) {
        return classeBean.getClasseById(id);
    }

    @GET
    public List<Classe> getAllClasses() {
        return classeBean.getAllClasses();
    }
}
