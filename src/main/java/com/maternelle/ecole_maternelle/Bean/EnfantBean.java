package com.maternelle.ecole_maternelle.Bean;

import com.maternelle.ecole_maternelle.Entities.Classe;
import com.maternelle.ecole_maternelle.Entities.Enfant;
import com.maternelle.ecole_maternelle.Entities.Parent;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Named
@RequestScoped
public class EnfantBean {

    @PersistenceContext(unitName = "ecolePU")
    private EntityManager em;

    private List<Enfant> listeEnfants;
    private Enfant nouvelEnfant = new Enfant();
    private List<Parent> listeParents;
    private List<Classe> listeClasses;

    @PostConstruct
    public void init() {
        loadEnfants();
        loadParents();
        loadClasses();
    }

    private void loadEnfants() {
        try {
            listeEnfants = em.createQuery("SELECT e FROM Enfant e", Enfant.class).getResultList();
        } catch (Exception e) {
            addErrorMessage("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }

    private void loadParents() {
        try {
            listeParents = em.createQuery("SELECT p FROM Parent p", Parent.class).getResultList();
        } catch (Exception e) {
            addErrorMessage("Erreur de récupération des parents : " + e.getMessage());
        }
    }

    private void loadClasses() {
        try {
            listeClasses = em.createQuery("SELECT c FROM Classe c", Classe.class).getResultList();
        } catch (Exception e) {
            addErrorMessage("Erreur de récupération des classes : " + e.getMessage());
        }
    }

    // Méthode pour ajouter un enfant dans la base de données
    public String ajouterEnfant() {
        try {
            if (nouvelEnfant.getNom() == null || nouvelEnfant.getNom().isEmpty()) {
                addErrorMessage("Le nom de l'enfant est obligatoire.");
                return null;
            }

            em.persist(nouvelEnfant);
            loadEnfants();  // Recharger la liste des enfants après ajout
            nouvelEnfant = new Enfant();  // Réinitialiser le formulaire
            addSuccessMessage("L'enfant a été ajouté avec succès.");
            return "enfants.xhtml?faces-redirect=true";  // Rediriger vers la page de la liste des enfants
        } catch (Exception e) {
            addErrorMessage("Erreur lors de l'ajout de l'enfant : " + e.getMessage());
            return null;
        }
    }
    
    
    public void supprimerEnfant(Enfant enfant) {
        try {
            em.remove(em.contains(enfant) ? enfant : em.merge(enfant));
            loadEnfants(); // Recharge la liste des enfants
        } catch (Exception e) {
            System.err.println("Erreur de suppression : " + e.getMessage());
        }
    }


    // Méthodes pour afficher les messages dans JSF
    private void addErrorMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    private void addSuccessMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    // Getters et setters
    public List<Parent> getListeParents() {
        return listeParents;
    }

    public List<Classe> getListeClasses() {
        return listeClasses;
    }

    public List<Enfant> getListeEnfants() {
        return listeEnfants;
    }

    public Enfant getNouvelEnfant() {
        return nouvelEnfant;
    }

    public void setNouvelEnfant(Enfant nouvelEnfant) {
        this.nouvelEnfant = nouvelEnfant;
    }
}
