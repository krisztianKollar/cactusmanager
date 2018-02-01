package com.cactusmanager;

import java.io.Serializable;

public class Thing implements Serializable {

    protected int catalogID;
    protected Genus genus;
    protected String species;
    public static int actualYear = 2018;
    private static final long serialVersionUID = 12345L;

    protected Thing(int catalogID, Genus genus, String species) {
        this.catalogID = catalogID;
        this.genus = genus;
        this.species = species;
    }

    protected Thing() {

    }

    public int getCatalogID() {
        return catalogID;
    }

    public Genus getGenus() {
        return genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setGenus(Genus genus) {
        this.genus = genus;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
