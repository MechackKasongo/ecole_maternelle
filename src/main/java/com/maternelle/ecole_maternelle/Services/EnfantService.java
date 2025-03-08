/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.maternelle.ecole_maternelle.Services;

import com.maternelle.ecole_maternelle.Entities.Enfant;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author mechack-kasongo
 */
@Stateless
@LocalBean
public class EnfantService {

    @PersistenceContext(unitName = "ecolePU")
    private EntityManager em;
    
    public List<Enfant> getAllEnfants() {
        return em.createQuery("SELECT e FROM Enfant e", Enfant.class).getResultList();
    }

    public void ajouterEnfant(Enfant enfant) {
        em.persist(enfant);
    }

    public Enfant modifierEnfant(Enfant enfant) {
        return em.merge(enfant);
    }

    public void supprimerEnfant(int id) {
        Enfant enfant = em.find(Enfant.class, id);
        if (enfant != null) {
            em.remove(enfant);
        }
    }

    public Enfant getEnfantById(int id) {
        return em.find(Enfant.class, id);
    }
}
