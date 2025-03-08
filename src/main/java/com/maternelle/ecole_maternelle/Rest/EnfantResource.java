/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.maternelle.ecole_maternelle.Rest;

import com.maternelle.ecole_maternelle.Entities.Enfant;
import com.maternelle.ecole_maternelle.Services.EnfantService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mechack-kasongo
 */

@RequestScoped
@Path("/enfants")
public class EnfantResource {

    @Inject
    private EnfantService enfantService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ajouterEnfant(Enfant enfant) {
        enfantService.ajouterEnfant(enfant);
        return Response.status(Response.Status.CREATED).entity(enfant).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnfant(@PathParam("id") int id) {
        Enfant enfant = (Enfant) enfantService.getEnfantById(id);
        if (enfant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(enfant).build();
    }
}
