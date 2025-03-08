/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.maternelle.ecole_maternelle.Rest;

import com.maternelle.ecole_maternelle.Entities.Parent;
import com.maternelle.ecole_maternelle.Services.ParentService;
import jakarta.ws.rs.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/parents")
public class ParentResource {

    @Inject
    private ParentService parentService;

    // Ajouter un parent
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addParent(Parent parent) {
        Parent createdParent = parentService.addParent(parent);
        return Response.status(Response.Status.CREATED).entity(createdParent).build();
    }

    // Récupérer un parent par son ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParent(@PathParam("id") int id) {
        Parent parent = parentService.getParent(id);
        if (parent != null) {
            return Response.ok(parent).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Récupérer tous les parents
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllParents() {
        return Response.ok(parentService.getAllParents()).build();
    }

    // Mettre à jour un parent
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParent(@PathParam("id") int id, Parent parent) {
        parent.setId(id);
        Parent updatedParent = parentService.updateParent(parent);
        return Response.ok(updatedParent).build();
    }

    // Supprimer un parent
    @DELETE
    @Path("/{id}")
    public Response deleteParent(@PathParam("id") int id) {
        parentService.deleteParent(id);
        return Response.noContent().build();
    }
}
