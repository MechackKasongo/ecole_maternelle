package com.maternelle.ecole_maternelle.Bean;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
@RolesAllowed("admin")  // Restrict access to users with the 'admin' role
public class AdminBean {

    // Propriétés et méthodes spécifiques pour l'administrateur

    private String message = "Bienvenue, Administrateur !";

    // Méthode pour obtenir le message
    public String getMessage() {
        return message;
    }

    // Autres méthodes spécifiques à l'administrateur
    public void performAdminTask() {
        // Code pour une tâche spécifique à l'admin
    }
}
