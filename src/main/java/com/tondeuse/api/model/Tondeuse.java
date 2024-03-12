package com.tondeuse.api.model;

public class Tondeuse {
    private Position position;
    private String instructions;

    public Tondeuse(Position position, String instructions) {
        this.position = position;
        this.instructions = instructions;
    }

    // Getters et Setters
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}