package com.maternelle.ecole_maternelle.Rest;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.maternelle.ecole_maternelle.Entities.Presence;
import com.maternelle.ecole_maternelle.Services.PresenceService;
import jakarta.ws.rs.core.Response;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import java.sql.Time;

public class PresenceResourceTest {

    @InjectMocks
    private PresenceResource presenceResource; // Classe à tester

    @Mock
    private PresenceService presenceService; // Service simulé

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        presenceResource.setPresenceService(presenceService); // Injection du mock
    }

    @Test
    public void testAddPresence() {
        // Création de l'objet Presence simulé
        Presence presence = new Presence();
        presence.setHeureEntree(Time.valueOf("08:00:00")); // Convertir String en Time
        presence.setHeureSortie(Time.valueOf("12:00:00")); // Convertir String en Time
        presence.setDatePresence(Date.valueOf("2025-03-06")); // Convertir String en Date

        // Simuler l'appel à la méthode addPresence du service
        doNothing().when(presenceService).addPresence(presence);

        // Appel de l'API
        Response response = presenceResource.addPresence(presence);

        // Vérification du statut de la réponse
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        // Vérifier que la méthode addPresence a bien été appelée une fois
        verify(presenceService, times(1)).addPresence(presence);
    }

    @Test
    public void testGetPresencesByEnfant() {
        int enfantId = 1;

        // Création de la liste de présences simulées
        Presence presence1 = new Presence();
        Presence presence2 = new Presence();
        List<Presence> presences = List.of(presence1, presence2);

        // Simuler l'appel à la méthode getPresencesByEnfant du service
        when(presenceService.getPresencesByEnfant(enfantId)).thenReturn(presences);

        // Appel de l'API REST
        Response response = presenceResource.getPresencesByEnfant(enfantId);

        // Vérifier que le statut HTTP est bien 200 OK
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // Extraire la liste des présences de la réponse
        List<Presence> result = response.readEntity(List.class); // Correction ici

        // Vérifier que la liste retournée contient bien 2 éléments
        assertNotNull(result);
        assertEquals(2, result.size());

        // Vérifier que la méthode du service a bien été appelée avec l'enfantId 1
        verify(presenceService, times(1)).getPresencesByEnfant(enfantId);
    }

}
