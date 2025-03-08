package com.maternelle.ecole_maternelle.Services;

import com.maternelle.ecole_maternelle.Entities.Presence;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Service pour gérer les présences des enfants dans l'école.
 */
@Stateless
public class PresenceService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Ajouter une nouvelle présence.
     * @param presence La présence à ajouter.
     */
    public void addPresence(Presence presence) {
        em.persist(presence);
    }

    /**
     * Récupérer toutes les présences d'un enfant par son ID.
     * @param enfantId L'ID de l'enfant.
     * @return Liste des présences pour cet enfant.
     */
    public List<Presence> getPresencesByEnfant(int enfantId) {
        return em.createQuery("SELECT p FROM Presence p WHERE p.enfant.id = :enfantId", Presence.class)
                 .setParameter("enfantId", enfantId)
                 .getResultList();
    }

    /**
     * Récupérer une présence par son ID.
     * @param id L'ID de la présence.
     * @return La présence correspondant à l'ID.
     */
    public Presence getPresence(int id) {
        return em.find(Presence.class, id);
    }

    /**
     * Mettre à jour une présence existante.
     * @param presence La présence à mettre à jour.
     */
    public void updatePresence(Presence presence) {
        Presence existingPresence = em.find(Presence.class, presence.getId());
        if (existingPresence != null) {
            // Mise à jour des champs de la présence existante
            existingPresence.setHeureEntree(presence.getHeureEntree());
            existingPresence.setHeureSortie(presence.getHeureSortie());
            existingPresence.setDatePresence(presence.getDatePresence());
            // Si d'autres champs existent, tu peux les mettre à jour ici
            em.merge(existingPresence);
        }
    }

    /**
     * Supprimer une présence par son ID.
     * @param id L'ID de la présence à supprimer.
     */
    public void deletePresence(int id) {
        Presence presence = em.find(Presence.class, id);
        if (presence != null) {
            em.remove(presence);
        }
    }
}
