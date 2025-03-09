package com.maternelle.ecole_maternelle.Bean;

import com.maternelle.ecole_maternelle.Entities.Classe;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Named
@RequestScoped
public class ClasseBean {

    @PersistenceContext(unitName = "ecolePU")
    private EntityManager em;
    
    private Classe classe = new Classe();
    private List<Classe> listeClasses; // ✅ Ajout de la liste des classes
    
    @PostConstruct
    public void init() {
        listeClasses = getAllClasses(); // ✅ Initialisation de la liste des classes
    }

    // Getter et Setter pour la classe
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    // Getter pour listeClasses
    public List<Classe> getListeClasses() {
        return listeClasses;
    }

    // Méthode pour récupérer toutes les classes
    public List<Classe> getAllClasses() {
        return em.createQuery("SELECT c FROM Classe c", Classe.class).getResultList();
    }

    public String addClasse() {
        em.persist(classe);
        return "classeList?faces-redirect=true"; 
    }

    public String removeClasse(Classe classe) {
        em.remove(em.merge(classe));
        return "classeList?faces-redirect=true"; 
    }

    public Classe getClasseById(int id) {
        return em.find(Classe.class, id);  
    }
}
