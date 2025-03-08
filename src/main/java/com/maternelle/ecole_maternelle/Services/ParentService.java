/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.maternelle.ecole_maternelle.Services;

import com.maternelle.ecole_maternelle.Entities.Parent;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ParentService {

    @PersistenceContext
    private EntityManager em;

    public Parent addParent(Parent parent) {
        em.persist(parent);
        return parent;
    }

    public Parent getParent(int id) {
        return em.find(Parent.class, id);
    }

    public List<Parent> getAllParents() {
        return em.createQuery("SELECT p FROM Parent p", Parent.class).getResultList();
    }

    public Parent updateParent(Parent parent) {
        return em.merge(parent);
    }

    public void deleteParent(int id) {
        Parent parent = em.find(Parent.class, id);
        if (parent != null) {
            em.remove(parent);
        }
    }
}
