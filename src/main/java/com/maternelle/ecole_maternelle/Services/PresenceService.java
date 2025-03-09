package com.maternelle.ecole_maternelle.Services;

import com.maternelle.ecole_maternelle.Entities.Classe;
import com.maternelle.ecole_maternelle.Entities.Enfant;
import com.maternelle.ecole_maternelle.Entities.Presence;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PresenceService {

    private static final Logger LOGGER = Logger.getLogger(PresenceService.class.getName());

    @PersistenceContext(unitName = "ecolePU")
    private EntityManager em;

    // Méthode pour récupérer une présence
    public Presence getPresence(int id) {
        return em.find(Presence.class, id);
    }

    // Méthode pour récupérer les présences par enfant
    public List<Presence> getPresencesByEnfant(int enfantId) {
        return em.createQuery("SELECT p FROM Presence p WHERE p.enfant.id = :enfantId", Presence.class)
                 .setParameter("enfantId", enfantId)
                 .getResultList();
    }

    // Méthode pour récupérer les présences par classe
    public List<Presence> getPresencesByClasse(int classeId) {
        return em.createQuery("SELECT p FROM Presence p WHERE p.enfant.classe.id = :classeId", Presence.class)
                 .setParameter("classeId", classeId)
                 .getResultList();
    }

    // Méthode pour ajouter une présence
    public void addPresence(Presence presence) {
        em.persist(presence);
    }

    // Méthode pour mettre à jour une présence
    public void updatePresence(Presence presence) {
        Presence existingPresence = em.find(Presence.class, presence.getId());
        if (existingPresence != null) {
            existingPresence.setHeureEntree(presence.getHeureEntree());
            existingPresence.setHeureSortie(presence.getHeureSortie());
            existingPresence.setDatePresence(presence.getDatePresence());
            em.merge(existingPresence);
        }
    }

    // Méthode pour supprimer une présence
    public void deletePresence(int id) {
        Presence presence = em.find(Presence.class, id);
        if (presence != null) {
            em.remove(presence);
        }
    }

    // Méthode pour récupérer toutes les classes
    public List<Classe> getAllClasses() {
        List<Classe> classes = em.createQuery("SELECT c FROM Classe c", Classe.class).getResultList();
        if (classes.isEmpty()) {
            LOGGER.warning("Aucune classe trouvée.");
        } else {
            LOGGER.info("Classes récupérées avec succès.");
        }
        return classes;
    }

    // Méthode pour récupérer toutes les présences
    public List<Presence> getAllPresences() {
        List<Presence> presences = em.createQuery("SELECT p FROM Presence p", Presence.class).getResultList();
        if (presences.isEmpty()) {
            LOGGER.warning("Aucune présence trouvée.");
        } else {
            LOGGER.info("Présences récupérées avec succès.");
        }
        return presences;
    }
}
