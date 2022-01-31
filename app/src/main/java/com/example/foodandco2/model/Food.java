package com.example.foodandco2.model;

public class Food {
    private int id;
    private int rank;
    private String name;
    private double c02KilosEquivalent;
    private double carMilesEquivalent;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Food(int id, int rank, String name, double c02KilosEquivalent, double carMilesEquivalent) {
        this.id = id;
        this.rank = rank;
        this.name = name;
        this.c02KilosEquivalent = c02KilosEquivalent;
        this.carMilesEquivalent = carMilesEquivalent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getC02KilosEquivalent() {
        return c02KilosEquivalent;
    }

    public void setC02KilosEquivalent(double c02KilosEquivalent) {
        this.c02KilosEquivalent = c02KilosEquivalent;
    }

    public double getCarMilesEquivalent() {
        return carMilesEquivalent;
    }

    public void setCarMilesEquivalent(double carMilesEquivalent) {
        this.carMilesEquivalent = carMilesEquivalent;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", rank=" + rank +
                ", name='" + name + '\'' +
                ", c02KilosEquivalent=" + c02KilosEquivalent +
                ", carMilesEquivalent=" + carMilesEquivalent +
                '}';
    }
}
