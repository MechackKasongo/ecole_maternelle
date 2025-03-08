package com.maternelle.ecole_maternelle.Bean;

import com.maternelle.ecole_maternelle.Entities.Enfant;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
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
    private Enfant nouvelEnfant = new Enfant();  // Objet enfant pour l'ajout

    @PostConstruct
    public void init() {
        loadEnfants();
    }

    private void loadEnfants() {
        try {
            listeEnfants = em.createQuery("SELECT e FROM Enfant e", Enfant.class).getResultList();
            System.out.println("Connexion réussie : " + listeEnfants.size() + " enfants trouvés !");
        } catch (Exception e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }

    // Getter pour la liste des enfants
    public List<Enfant> getListeEnfants() {
        return listeEnfants;
    }

    // Getter et setter pour le nouvel enfant
    public Enfant getNouvelEnfant() {
        return nouvelEnfant;
    }

    public void setNouvelEnfant(Enfant nouvelEnfant) {
        this.nouvelEnfant = nouvelEnfant;
    }

    // Méthode pour ajouter un enfant dans la base de données
    public String ajouterEnfant() {
        try {
            em.persist(nouvelEnfant);  // Enregistre le nouvel enfant dans la base de données
            loadEnfants();  // Recharge la liste après l'ajout
            nouvelEnfant = new Enfant();  // Réinitialise l'objet pour préparer l'ajout d'un nouvel enfant
            return "enfants.xhtml?faces-redirect=true";  // Redirige vers la page de la liste des enfants
        } catch (Exception e) {
            e.printStackTrace();
            // Ajoutez éventuellement un message d'erreur à l'utilisateur ici
            return null;  // Reste sur la même page en cas d'erreur
        }
    }
}