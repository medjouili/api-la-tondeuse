package com.tondeuse.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Pelouse {
    private int largeur;
    private int hauteur;
    private List<Tondeuse> tondeuses;

    public Pelouse(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.tondeuses = new ArrayList<>();
    }

    // Méthodes pour ajouter une tondeuse
    public void addTondeuse(Tondeuse tondeuse) {
        tondeuses.add(tondeuse);
    }

    // Getters et Setters
    public int getLargeur() { return largeur; }
    public void setLargeur(int largeur) { this.largeur = largeur; }

    public int getHauteur() { return hauteur; }
    public void setHauteur(int hauteur) { this.hauteur = hauteur; }

    public List<Tondeuse> getTondeuses() { return tondeuses; }
}
