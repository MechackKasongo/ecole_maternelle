/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.maternelle.ecole_maternelle.Rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import com.maternelle.ecole_maternelle.Entities.Presence;
import com.maternelle.ecole_maternelle.Services.PresenceService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/presences")
public class PresenceResource {

    @EJB
    private PresenceService presenceService;

    public void setPresenceService(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    // Ajouter une présence
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPresence(Presence presence) {
        presenceService.addPresence(presence);
        return Response.status(Response.Status.CREATED).build();
    }

    // Récupérer les présences d'un enfant
    @GET
    @Path("/enfant/{enfantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPresencesByEnfant(@PathParam("enfantId") int enfantId) {
        List<Presence> presences = presenceService.getPresencesByEnfant(enfantId);

        if (presences.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucune présence trouvée pour cet enfant.").build();
        }

        return Response.ok(presences).build(); // Retourne la liste dans un Response
    }


    // Mettre à jour la présence
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePresence(@PathParam("id") int id, Presence presence) {
        presence.setId(id);
        presenceService.updatePresence(presence);
        return Response.ok().build();
    }

    // Supprimer une présence
    @DELETE
    @Path("/{id}")
    public Response deletePresence(@PathParam("id") int id) {
        presenceService.deletePresence(id);
        return Response.noContent().build();
    }
}
