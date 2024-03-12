package com.tondeuse.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tondeuse.api.model.Position;
import com.tondeuse.api.model.Tondeuse;
import com.tondeuse.api.service.PelouseService;
import com.tondeuse.api.service.TondeuseService;

//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PelouseServiceTest {

    private PelouseService pelouseService;
    private TondeuseService tondeuseService;

    @BeforeEach
    void setUp() {
        pelouseService = new PelouseService(5, 5);
        tondeuseService = new TondeuseService();
    }

    @Test
    void testAjouterTondeuseALaPelouse() {
        Position positionInitiale = new Position(1, 2, 'N');
        String instructions = "GAGAGAGAA";
        pelouseService.ajouterTondeuse(positionInitiale, instructions);

        assertEquals(1, pelouseService.getPelouse().getTondeuses().size());
        Tondeuse tondeuse = pelouseService.getPelouse().getTondeuses().get(0);
        assertEquals(1, tondeuse.getPosition().getX());
        assertEquals(2, tondeuse.getPosition().getY());
        assertEquals('N', tondeuse.getPosition().getOrientation());
        assertEquals(instructions, tondeuse.getInstructions());
    }
}
