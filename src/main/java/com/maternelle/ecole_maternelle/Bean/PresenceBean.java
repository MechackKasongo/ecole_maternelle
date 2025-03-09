package com.maternelle.ecole_maternelle.Bean;

import com.maternelle.ecole_maternelle.Entities.Classe;
import com.maternelle.ecole_maternelle.Entities.Enfant;
import com.maternelle.ecole_maternelle.Entities.Presence;
import com.maternelle.ecole_maternelle.Services.ClasseService;
import com.maternelle.ecole_maternelle.Services.EnfantService;
import com.maternelle.ecole_maternelle.Services.PresenceService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class PresenceBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PresenceBean.class.getName());

    @EJB//@Inject
    private PresenceService presenceService;

    @Inject
    private ClasseService classeService;

    @Inject
    private EnfantService enfantService;

    private List<Presence> listePresences;
    private List<Classe> listeClasses;
    private List<Enfant> listeEnfants;

    private Classe selectedClasse;
    private final Map<Integer, Boolean> presenceMap = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            // Charger les classes et présences uniquement si elles existent
            listeClasses = presenceService.getAllClasses();
            if (listeClasses == null || listeClasses.isEmpty()) {
                LOGGER.warning("Aucune classe disponible.");
            } else {
                LOGGER.info("Classes chargées avec succès.");
            }

            listePresences = presenceService.getAllPresences();
            if (listePresences == null || listePresences.isEmpty()) {
                LOGGER.warning("Aucune présence enregistrée.");
            } else {
                LOGGER.info("Présences chargées avec succès.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'initialisation de PresenceBean : {0}", e.getMessage());
        }
    }


    /**
     * Marquer la présence des enfants sélectionnés.
     */
    public void marquerPresences() {
        try {
            for (Map.Entry<Integer, Boolean> entry : presenceMap.entrySet()) {
                Integer enfantId = entry.getKey();
                Boolean isPresent = entry.getValue();

                if (enfantId != null && isPresent != null) {
                    Enfant enfant = enfantService.getEnfantById(enfantId);
                    if (enfant != null) {
                        Presence presence = new Presence();
                        presence.setEnfant(enfant);
                        presence.setDatePresence(new Date(System.currentTimeMillis()));
                        presence.setHeureEntree(new Time(System.currentTimeMillis()));
                        presence.setPresent(isPresent);

                        presenceService.addPresence(presence);
                    } else {
                        LOGGER.warning("Impossible d'ajouter la présence : Enfant introuvable (ID=" + enfantId + ")");
                    }
                } else {
                    LOGGER.warning("ID enfant ou statut présence null.");
                }
            }
            LOGGER.info("Présences enregistrées avec succès.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'enregistrement des présences : {0}", e.getMessage());
        }
    }

    /**
     * Charger la liste des enfants pour la classe sélectionnée.
     */
    public void loadEnfants() {
        try {
            if (selectedClasse != null) {
                listeEnfants = enfantService.getEnfantsByClasse(selectedClasse.getId());
                presenceMap.clear(); // Réinitialise la carte des présences pour la nouvelle classe
                LOGGER.info("Enfants chargés pour la classe : " + selectedClasse.getNom());
            } else {
                LOGGER.warning("Aucune classe sélectionnée.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors du chargement des enfants : {0}", e.getMessage());
        }
    }

    /**
     * Charger les présences des enfants pour la classe sélectionnée.
     */
    public void loadPresences() {
        try {
            if (selectedClasse != null) {
                listePresences = presenceService.getPresencesByClasse(selectedClasse.getId());
                LOGGER.info("Présences chargées pour la classe : " + selectedClasse.getNom());
            } else {
                LOGGER.warning("Aucune classe sélectionnée.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors du chargement des présences : {0}", e.getMessage());
        }
    }

    // Getters et Setters
    public List<Presence> getListePresences() {
        return listePresences;
    }

    public List<Classe> getListeClasses() {
        return listeClasses;
    }

    public Classe getSelectedClasse() {
        return selectedClasse;
    }
    
    public void setSelectedClasse(Classe selectedClasse) {
        this.selectedClasse = selectedClasse;
        if (selectedClasse != null) {
            listePresences = presenceService.getPresencesByClasse(selectedClasse.getId());
        } else {
            listePresences = presenceService.getAllPresences();  // Affiche toutes les présences si aucune classe n'est sélectionnée
        }
    }


//    public void setSelectedClasse(Classe selectedClasse) {
//        this.selectedClasse = selectedClasse;
//        if (selectedClasse != null) {
//            loadPresences();
//        } else {
//            listePresences = presenceService.getAllPresences();
//        }
//    }

    public List<Enfant> getListeEnfants() {
        return listeEnfants;
    }

    public Map<Integer, Boolean> getPresenceMap() {
        return presenceMap;
    }
}



















