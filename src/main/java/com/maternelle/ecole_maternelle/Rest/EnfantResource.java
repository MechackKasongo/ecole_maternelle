package com.maternelle.ecole_maternelle.Rest;

import com.maternelle.ecole_maternelle.Entities.Enfant;
import com.maternelle.ecole_maternelle.Services.EnfantService;
import jakarta.ws.rs.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service pour la gestion des enfants
 */
@RequestScoped
@Path("/enfants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnfantResource {

    @Inject
    private EnfantService enfantService;

    // Ajouter un enfant
    @POST
    public Response ajouterEnfant(Enfant enfant) {
        enfantService.ajouterEnfant(enfant);
        return Response.status(Response.Status.CREATED).entity(enfant).build();
    }

    // Récupérer un enfant par ID
    @GET
    @Path("/{id}")
    public Response getEnfant(@PathParam("id") int id) {
        Enfant enfant = enfantService.getEnfantById(id);
        if (enfant == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("Enfant avec ID " + id + " non trouvé.")
                          .build();
        }
        return Response.ok(enfant).build();
    }

    // Récupérer tous les enfants
    @GET
    public Response getAllEnfants() {
        List<Enfant> enfants = enfantService.getAllEnfants();
        return Response.ok(enfants).build();
    }

    // Modifier un enfant
    @PUT
    @Path("/{id}")
    public Response modifierEnfant(@PathParam("id") int id, Enfant enfantModifie) {
        Enfant enfant = enfantService.getEnfantById(id);
        if (enfant == null) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("Impossible de modifier : Enfant non trouvé.")
                          .build();
        }
        enfant.setNom(enfantModifie.getNom());
        enfant.setPrenom(enfantModifie.getPrenom());
        enfant.setDateNaissance(enfantModifie.getDateNaissance());
        enfant.setParent(enfantModifie.getParent());
        enfant.setClasse(enfantModifie.getClasse());

        enfantService.modifierEnfant(enfant);
        return Response.ok(enfant).build();
    }

    // Supprimer un enfant
    @DELETE
    @Path("/{id}")
    public Response supprimerEnfant(@PathParam("id") int id) {
        boolean supprimé = enfantService.supprimerEnfant(id);
        if (!supprimé) {
            return Response.status(Response.Status.NOT_FOUND)
                          .entity("Impossible de supprimer : Enfant non trouvé.")
                          .build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
