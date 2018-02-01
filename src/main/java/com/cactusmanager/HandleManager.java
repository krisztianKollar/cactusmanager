package com.cactusmanager;

import java.io.*;
import java.lang.reflect.Array;

public class HandleManager implements Serializable {

    private Cacti[] cactiList;

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

    public Cacti[] findName(String species) { // It is not ready yet.
        int counter = 0;
        Cacti[] cactiFiltered = new Cacti[counter];
        for (Cacti cactus : cactiList) {
            if (cactus.getSpecies().equals(species)) {
                return cactiFiltered;
            }
        }
        return null;
    }

    public Cacti findPlantingYear(int ID) { // Should work as findName: should return an array.
        for (Cacti cactus : cactiList) {
            if (Thing.actualYear - cactus.getPlantingYear() > 3) {
                return cactus;
            }
        }
        return null;
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
