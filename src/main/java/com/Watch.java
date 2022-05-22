package com;

public class Watch {
    private String manufacturer = "Rolex";
    private String model = "Daytona";
    private double diameter = 2.3;
    private Double weight = 33.3;
    private char symbol = 'W';

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public char getSymbol() { return symbol; }

    public void setSymbol(char symbol) { this.symbol = symbol; }
}
