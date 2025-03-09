package com.maternelle.ecole_maternelle.Services;

import com.maternelle.ecole_maternelle.Entities.Classe;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClasseService {
    
    @PersistenceContext(unitName = "ecolePU")
    private EntityManager em;

    public List<Classe> getAllClasses() {
        return em.createQuery("SELECT c FROM Classe c", Classe.class).getResultList();
    }

    public Classe getClasseById(int id) {
        return em.find(Classe.class, id);
    }
}
