
package com.maternelle.ecole_maternelle.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author mechack-kasongo
 */
@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date datePresence;
    private Time heureEntree;
    private Time heureSortie;
    private Timestamp dateCreation;

    @ManyToOne
    @JoinColumn(name = "enfant_id", nullable = false)
    private Enfant enfant;
    
    // Getter et Setter pour 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter et Setter pour 'datePresence'
    public Date getDatePresence() {
        return datePresence;
    }

    public void setDatePresence(Date datePresence) {
        this.datePresence = datePresence;
    }

    // Getter et Setter pour 'heureEntree'
    public Time getHeureEntree() {
        return heureEntree;
    }

    public void setHeureEntree(Time heureEntree) {
        this.heureEntree = heureEntree;
    }

    // Getter et Setter pour 'heureSortie'
    public Time getHeureSortie() {
        return heureSortie;
    }

    public void setHeureSortie(Time heureSortie) {
        this.heureSortie = heureSortie;
    }

    // Getter et Setter pour 'dateCreation'
    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    // Getter et Setter pour 'enfant'
    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }
}
