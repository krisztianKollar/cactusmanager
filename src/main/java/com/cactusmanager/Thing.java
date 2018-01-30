package com.cactusmanager;

public class Thing {

    protected int catalogID;
    protected Genus genus;
    protected String species;

    protected Thing(int catalogID, Genus genus, String species) {
        this.catalogID = catalogID;
        this.genus = genus;
        this.species = species;
    }

    public int getCatalogID() {
        return catalogID;
    }

    public Genus getgenus() {
        return genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setCatalogID() {
        ;
    }
}