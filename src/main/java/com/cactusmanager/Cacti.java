package com.cactusmanager;


import java.io.Serializable;

public class Cacti extends Thing {

    private int plantingYear;
    private static final long serialVersionUID = 123456789L;


    public Cacti(int catalogID, Genus genus, String species, int plantingYear) {
        super(catalogID, genus, species);
        this.plantingYear = plantingYear;
    }

    public Cacti() {
    }

    public int getPlantingYear() {
        return plantingYear;
    }

    public void setPlantingYear(int plantingYear) {
        this.plantingYear = plantingYear;
    }

    @Override
    public String toString() {
        return catalogID + ". " + genus.name() + " " + species + ", planted in " + plantingYear;
    }


}
