package com.tondeuse.api.service;

import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Position;
import com.tondeuse.api.model.Tondeuse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PelouseService {

    private Pelouse pelouse;
    public PelouseService() {
        // Constructeur par défaut sans initialisation de la pelouse
    }

    // Cette méthode permet de définir ou de mettre à jour la pelouse
    public void setPelouse(Pelouse nouvellePelouse) {
        this.pelouse = nouvellePelouse;
    }
    public PelouseService(int largeur, int hauteur) {
        this.pelouse = new Pelouse(largeur, hauteur);
    }

    public void ajouterTondeuse(Position positionInitiale, String instructions) {
        Tondeuse nouvelleTondeuse = new Tondeuse(positionInitiale, instructions);
        if (estPositionValide(positionInitiale)) {
            pelouse.addTondeuse(nouvelleTondeuse);
        } else {
            System.out.println("Position initiale invalide pour la tondeuse : " + positionInitiale.getX() + ", " + positionInitiale.getY());
        }
    }

    public boolean estPositionValide(Position position) {
        return position.getX() >= 0 && position.getX() <= pelouse.getLargeur() &&
               position.getY() >= 0 && position.getY() <= pelouse.getHauteur();
    }

    public Pelouse getPelouse() {
        return this.pelouse;
    }

    public void executerInstructionsPourToutesLesTondeuses(TondeuseService tondeuseService) {
        for (Tondeuse tondeuse : pelouse.getTondeuses()) {
            tondeuseService.executeInstructions(tondeuse, pelouse);
            // Après exécution, afficher la position et orientation finale de chaque tondeuse
            System.out.println("Tondeuse en position finale : " + tondeuse.getPosition().getX() + " " +
                    tondeuse.getPosition().getY() + " " + tondeuse.getPosition().getOrientation());
        }
    }
}
