package com.cactusmanager;


import java.io.Serializable;

public class Cacti extends Thing {

    private int plantingYear;

    public Cacti(int catalogID, Genus genus, String species, int plantingYear) {
        super(catalogID, genus, species);
        this.plantingYear = plantingYear;
    }

    public Cacti() {
    }

    public int getPlantingYear() {
        return plantingYear;
    }

    public void setPlantingYear() {

    }

    @Override
    public String toString() {
        return catalogID + ". " + genus.name() + " " + species + ", planted in " + plantingYear;
    }
}
