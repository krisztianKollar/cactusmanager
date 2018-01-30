package com.cactusmanager;

public class HandleManager {

    private Cacti[][] cactiList;  
    
    public HandleManager() {
        cactiList = new Cacti[0][];
    }

    public void addToCatalog(Cacti cactus) {
        Cacti[][] tempArray = new Cacti[cactiList.length + 1];
        for (int i = 0; i < cactiList.length; i++) {
            tempArray[i] = cactiList[i]; //How to implement: Array/2DArray?
        }
        tempArray[tempArray.length - 1] = cactus;
        cactiList = tempArray;
    }

    public Cacti findID(int ID) { //INT?!
        for (Cacti cactus : cactiList) {
            if (cactus.getCatalogID().equals(ID)) {  
                return cactus;
            }
        }
        return null;
    }

    public void exit() {
        System.out.println("Bye! Beware of spikes!");
    }
    
}