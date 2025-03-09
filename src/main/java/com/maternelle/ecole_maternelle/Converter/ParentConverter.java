package com.maternelle.ecole_maternelle.Converter;

import com.maternelle.ecole_maternelle.Entities.Parent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@FacesConverter("parentConverter")
public class ParentConverter implements Converter {

    @PersistenceContext
    private EntityManager em;  // L'EntityManager pour accéder à la base de données

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            // Convertir la chaîne de caractères en ID (par exemple, entier) et rechercher l'objet Parent dans la base de données
            return em.find(Parent.class, Integer.parseInt(value));  // Assurez-vous que 'value' est l'ID du Parent
        } catch (NumberFormatException e) {
            // Gérer l'exception si l'ID est invalide
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        // Si l'objet Parent est valide, retourner son ID (ou un autre attribut si nécessaire)
        Parent parent = (Parent) value;
        return String.valueOf(parent.getId());  // Retourner l'ID du Parent sous forme de chaîne
    }
}
