package com.cactusmanager;

public class Cacti extends Thing {

    private static int cactiCounter = 0;
    private int plantingYear; 

    public Cacti(int catalogID, Genus genus, String species, int plantingYear) {
        super(catalogID, genus, species);
        this.plantingYear = plantingYear;
    }

    public int getPlantingYear() {
        return plantingYear;
    }

    public void setPlantingYear() {
        
    }
}