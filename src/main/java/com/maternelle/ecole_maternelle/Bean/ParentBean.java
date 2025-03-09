/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.maternelle.ecole_maternelle.Bean;

import com.maternelle.ecole_maternelle.Entities.Parent;
import jakarta.annotation.ManagedBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author mechack-kasongo
 */
@ManagedBean
@RequestScoped
public class ParentBean {
    @PersistenceContext
    private EntityManager em;

    private Parent parent = new Parent();

    // Getter et Setter pour le parent
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    // Méthodes CRUD pour le parent
    public List<Parent> getAllParents() {
        return em.createQuery("SELECT p FROM Parent p", Parent.class).getResultList();
    }

    public String addParent() {
        em.persist(parent);
        return "parentList?faces-redirect=true"; // Rediriger vers la page de liste des parents
    }

    public String removeParent(Parent parent) {
        em.remove(em.merge(parent));
        return "parentList?faces-redirect=true"; // Rediriger vers la page de liste des parents
    }

    public Parent getParentById(int id) {
        return em.find(Parent.class, id);
    }
}

