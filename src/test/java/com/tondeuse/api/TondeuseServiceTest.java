package com.tondeuse.api;

import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Position;
import com.tondeuse.api.model.Tondeuse;
import com.tondeuse.api.service.TondeuseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TondeuseServiceTest {

    private TondeuseService tondeuseService;
    private Tondeuse tondeuse;
    private Pelouse pelouse;

    @BeforeEach
    void setUp() {
        tondeuseService = new TondeuseService();
        // Initialiser une pelouse de taille 5x5 pour le test
        pelouse = new Pelouse(5, 5);
        // Position initiale de la tondeuse
        tondeuse = new Tondeuse(new Position(1, 2, 'N'), "DAGADAGA");
    }

  //  @Test
    void executeInstructions() {
        tondeuseService.executeInstructions(tondeuse, pelouse);
        // Vérifiez la position finale et l'orientation de la tondeuse après l'exécution des instructions
        assertEquals(1, tondeuse.getPosition().getX(), "X final incorrect");
        assertEquals(3, tondeuse.getPosition().getY(), "Y final incorrect");
        assertEquals('N', tondeuse.getPosition().getOrientation(), "Orientation finale incorrecte");
    }

    @Test
    void avancer() {
        // Test avancer vers le nord
        tondeuse.getPosition().setOrientation('N');
        tondeuseService.avancer(tondeuse, pelouse);
        assertEquals(3, tondeuse.getPosition().getY(), "Avancer vers le nord échoue");

        // Ajoutez des tests similaires pour les autres directions (Est, Sud, Ouest)
    }

    @Test
    void tournerDroiteEtGauche() {
        // Test tourner à droite de Nord à Est
        tondeuse.getPosition().setOrientation('N');
        tondeuseService.tournerDroite(tondeuse);
        assertEquals('E', tondeuse.getPosition().getOrientation(), "Tourner à droite échoue");

        // Test tourner à gauche de Est à Nord
        tondeuseService.tournerGauche(tondeuse);
        assertEquals('N', tondeuse.getPosition().getOrientation(), "Tourner à gauche échoue");

      
    }

}