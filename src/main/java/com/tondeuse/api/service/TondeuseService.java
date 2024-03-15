package com.tondeuse.api.service;

import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Tondeuse;

public class TondeuseService {
	

    public void executeInstructions(Tondeuse tondeuse, Pelouse pelouse) {
        String instructions = tondeuse.getInstructions();
        for (int i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);
            switch (instruction) {
                case 'D':
                    tournerDroite(tondeuse);
                    break;
                case 'G':
                    tournerGauche(tondeuse);
                    break;
                case 'A':
                    avancer(tondeuse, pelouse);
                    break;
                default:
                    // Instruction inconnue, ignorer
                    break;
            }
        }
    }

    public void tournerDroite(Tondeuse tondeuse) {
        switch (tondeuse.getPosition().getOrientation()) {
            case 'N':
                tondeuse.getPosition().setOrientation('E');
                break;
            case 'E':
                tondeuse.getPosition().setOrientation('S');
                break;
            case 'S':
                tondeuse.getPosition().setOrientation('W');
                break;
            case 'W':
                tondeuse.getPosition().setOrientation('N');
                break;
        }
    }

    public void tournerGauche(Tondeuse tondeuse) {
        switch (tondeuse.getPosition().getOrientation()) {
            case 'N':
                tondeuse.getPosition().setOrientation('W');
                break;
            case 'W':
                tondeuse.getPosition().setOrientation('S');
                break;
            case 'S':
                tondeuse.getPosition().setOrientation('E');
                break;
            case 'E':
                tondeuse.getPosition().setOrientation('N');
                break;
        }
    }

    public void avancer(Tondeuse tondeuse, Pelouse pelouse) {
        int x = tondeuse.getPosition().getX();
        int y = tondeuse.getPosition().getY();
        switch (tondeuse.getPosition().getOrientation()) {
            case 'N':
                if (y < pelouse.getHauteur()) tondeuse.getPosition().setY(y + 1);
                break;
            case 'E':
                if (x < pelouse.getLargeur()) tondeuse.getPosition().setX(x + 1);
                break;
            case 'S':
                if (y > 0) tondeuse.getPosition().setY(y - 1);
                break;
            case 'W':
                if (x > 0) tondeuse.getPosition().setX(x - 1);
                break;
        }
    }

}
