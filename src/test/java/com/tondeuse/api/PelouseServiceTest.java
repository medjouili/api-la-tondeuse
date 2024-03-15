package com.tondeuse.api;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Position;
import com.tondeuse.api.model.Tondeuse;
import com.tondeuse.api.service.PelouseService;

public class PelouseServiceTest {

    private PelouseService pelouseService;

    @Before
    public void setUp() {
        pelouseService = new PelouseService(5, 5); // Initialisation avec une pelouse de taille 5x5
    }

    @Test
    public void testAjouterTondeuseAvecPositionValide() {
        Position positionValide = new Position(1, 1, 'N');
        pelouseService.ajouterTondeuse(positionValide, "AAA");
        Pelouse pelouse = pelouseService.getPelouse();
        
        assertNotNull("La pelouse ne doit pas être nulle", pelouse);
        assertEquals("La pelouse doit contenir une tondeuse", 1, pelouse.getTondeuses().size());
        Tondeuse tondeuse = pelouse.getTondeuses().get(0);
        assertEquals("La position de la tondeuse doit correspondre à la position initiale", positionValide, tondeuse.getPosition());
    }

    @Test
    public void testAjouterTondeuseAvecPositionInvalide() {
        Position positionInvalide = new Position(6, 6, 'N');
        pelouseService.ajouterTondeuse(positionInvalide, "AAA");
        Pelouse pelouse = pelouseService.getPelouse();
        
        assertNotNull("La pelouse ne doit pas être nulle", pelouse);
        assertTrue("La pelouse ne doit contenir aucune tondeuse", pelouse.getTondeuses().isEmpty());
    }

    // Test supplémentaire pour setPelouse et le constructeur
    @Test
    public void testSetPelouse() {
        Pelouse nouvellePelouse = new Pelouse(10, 10);
        pelouseService.setPelouse(nouvellePelouse);
        assertEquals("La pelouse doit être mise à jour avec les nouvelles dimensions", nouvellePelouse, pelouseService.getPelouse());
    }

    @Test
    public void testConstructeurAvecParametres() {
        PelouseService nouveauService = new PelouseService(8, 8);
        assertNotNull("Un nouveau service de pelouse doit être créé avec une pelouse", nouveauService.getPelouse());
        assertEquals("La largeur de la pelouse doit être 8", 8, nouveauService.getPelouse().getLargeur());
        assertEquals("La hauteur de la pelouse doit être 8", 8, nouveauService.getPelouse().getHauteur());
    }

}