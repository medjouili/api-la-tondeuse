package com.tondeuse.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Position;
import com.tondeuse.api.service.PelouseService;

public class FichierUtil {

    public static Pelouse lireFichier(String cheminFichier) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(cheminFichier));
        // Lecture des dimensions de la pelouse
        int largeur = scanner.nextInt();
        int hauteur = scanner.nextInt();

        PelouseService pelouseService = new PelouseService(largeur, hauteur);

        while (scanner.hasNext()) {
            // Lecture de la position initiale et de l'orientation de la tondeuse
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            char orientation = scanner.next().charAt(0);
            Position positionInitiale = new Position(x, y, orientation);

            // Lecture des instructions de la tondeuse
            if (scanner.hasNext()) {
                String instructions = scanner.next();
                pelouseService.ajouterTondeuse(positionInitiale, instructions);
            }
        }

        scanner.close();

        return pelouseService.getPelouse();
    }
}