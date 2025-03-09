package com.maternelle.ecole_maternelle.Converter;

import com.maternelle.ecole_maternelle.Bean.ClasseBean;
import com.maternelle.ecole_maternelle.Bean.EntityManagerBean;
import com.maternelle.ecole_maternelle.Entities.Classe;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;



@FacesConverter("classeConverter")
public class ClasseConverter implements Converter {

    @Inject
    private EntityManagerBean entityManagerBean;
    
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        em = entityManagerBean.getEntityManager();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return em.find(Classe.class, Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        Classe classe = (Classe) value;
        return String.valueOf(classe.getId());
    }
}