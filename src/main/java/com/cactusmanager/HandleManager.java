package com.cactusmanager;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HandleManager implements Serializable {

    private Cacti[] cactiList;
    private static final long serialVersionUID = 1234567L;


    public HandleManager() {
        cactiList = new Cacti[0];
    }

    public void addToCatalog(Cacti cactus) {
        Cacti[] tempArray = new Cacti[cactiList.length + 1];
        for (int i = 0; i < cactiList.length; i++) {
            tempArray[i] = cactiList[i];
        }
        tempArray[tempArray.length - 1] = cactus;
        cactiList = tempArray;
    }

    public Cacti findID(int ID) {
        for (Cacti cactus : cactiList) {
            if (cactus.getCatalogID() == ID) {
                return cactus;
            }
        }
        return null;
    }

    public ArrayList<Cacti> findGenus(Genus genus) {
        ArrayList<Cacti> cactiFiltered = new ArrayList();
        for (Cacti cactus : cactiList) {
            if (cactus.getGenus().equals(genus)) {
                cactiFiltered.add(cactus);
            }
        }
        return cactiFiltered;
    }

    public ArrayList<Cacti> findSpecies(String species) {
        ArrayList<Cacti> cactiFiltered = new ArrayList();
        for (Cacti cactus : cactiList) {
            if (cactus.getSpecies().equals(species)) {
                cactiFiltered.add(cactus);
            }
        }
        return cactiFiltered;
    }

    public ArrayList<Cacti> findToPlant() {
        ArrayList<Cacti> cactiFiltered = new ArrayList();
        for (Cacti cactus : cactiList) {
            if (Thing.actualYear - cactus.getPlantingYear() > 3) {
                cactiFiltered.add(cactus);
            }
        }
        return cactiFiltered;
    }

    public void exit() {
        System.out.println("Bye! Beware of spikes!");
    }

    public Cacti[] getCactiList() {
        return cactiList;
    }

    public int getNumOfCacti() {
        return Array.getLength(cactiList);
    }

    public final void saveList() throws IOException {
        FileOutputStream fos = new FileOutputStream("CactiList.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cactiList);
        oos.close();
    }

    public final void loadList() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("CactiList.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        cactiList = (Cacti[]) ois.readObject();
        ois.close();
    }
}
