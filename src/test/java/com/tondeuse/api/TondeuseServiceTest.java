package com.tondeuse.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Position;
import com.tondeuse.api.model.Tondeuse;
import com.tondeuse.api.service.TondeuseService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TondeuseServiceTest {

    private TondeuseService tondeuseService;
    private Pelouse pelouse;

    @BeforeEach
    void setUp() {
        tondeuseService = new TondeuseService();
        // Initialiser une pelouse de 5x5 pour les tests
        pelouse = new Pelouse(5, 5);
    }

    @Test
    void testDeplacementTondeuse() {
        Position positionInitiale = new Position(1, 2, 'N');
        Tondeuse tondeuse = new Tondeuse(positionInitiale, "GAGAGAGAA");
        tondeuseService.executeInstructions(tondeuse, pelouse);

        assertEquals(1, tondeuse.getPosition().getX());
        assertEquals(3, tondeuse.getPosition().getY());
        assertEquals('N', tondeuse.getPosition().getOrientation());
    }
}