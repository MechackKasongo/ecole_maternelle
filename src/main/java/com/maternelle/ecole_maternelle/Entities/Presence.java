
package com.maternelle.ecole_maternelle.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
//import java.sql.Timestamp;

/**
 *
 * @author mechack-kasongo
 */
@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ENFANT_ID", nullable = false)
    private Enfant enfant;
    

    @Column(name = "DATE_PRESENCE")
    private Date datePresence;
    
    @Column(name = "HEURE_ENTREE")
    private Time heureEntree;
    
    @Column(name = "HEURE_SORTIE")
    private Time heureSortie;
    
    @Column(name = "DATE_CREATION")
    private Date dateCreation;
    
    
    @Column(name = "PRESENT")
    private Boolean present;
    

    
    // Constructeur par défaut obligatoire pour JPA
    public Presence() {
    }
    
        // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    public Date getDatePresence() {
        return datePresence;
    }

    public void setDatePresence(Date datePresence) {
        this.datePresence = datePresence;
    }

    public Time getHeureEntree() {
        return heureEntree;
    }

    public void setHeureEntree(Time heureEntree) {
        this.heureEntree = heureEntree;
    }

    public Time getHeureSortie() {
        return heureSortie;
    }

    public void setHeureSortie(Time heureSortie) {
        this.heureSortie = heureSortie;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }


//    // Getter et Setter pour 'dateCreation'
//    public Timestamp getDateCreation() {
//        return dateCreation;
//    }
//
//    public void setDateCreation(Timestamp dateCreation) {
//        this.dateCreation = dateCreation;
//    }

//    // Getter et Setter pour 'enfant'
//    public Enfant getEnfant() {
//        return enfant;
//    }
//
//    public void setEnfant(Enfant enfant) {
//        this.enfant = enfant;
//    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
