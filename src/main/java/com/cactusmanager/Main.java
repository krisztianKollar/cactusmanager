package com.cactusmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static HandleManager hm = new HandleManager();

    public static void main(String[] args) {
        System.out.println("\nWelcome to the Catalog of Cacti!\n");
        try{
            hm.loadList();
        }catch(IOException e){
            System.out.println("Oops! There is no such a list. You can make one by :add and :exit");
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            System.out.println("Oops! Something went wrong while loading the list_CNFE");
            e.printStackTrace();
        }
        System.out.println("You can use these commands: \n" +
            ":list - you can list your plants \n" +
            ":add - you can add a new cactus to the list \n" +
            ":findID, :findGenus and :findSpecies - you can search in the list with these commands \n" +
            ":change - you can update a plant by a given ID\n" +
            ":findToPlant - you can list your cacti that should be replanted\n" +
            ":exit - you can leave Catalog of Cacti\n");
        while (true) {
            String line = scanner.nextLine();
            if (!(":exit".equals(line) ||
                ":list".equals(line) ||
                ":add".equals(line) ||
                ":findGenus".equals(line) ||
                ":findSpecies".equals(line) ||
                ":findID".equals(line) ||
                ":findToPlant".equals(line) ||
                ":change".equals(line))) {
                System.out.println("Wrong command! You can use :list, :add, :findName, :findID, :change, :exit\n");
            } else if (":exit".equals(line)) {
                try{
                    hm.saveList();
                }catch(IOException e){
                    e.printStackTrace();
                    System.out.println("Oops! Something went wrong while saving the list");
                }
                hm.exit();
                break;

            } else if (":list".equals(line)) {
                handleList();

            } else if (":add".equals(line)) {
                handleAdd();

            } else if (":findSpecies".equals(line)) {
                handleFindSpecies();

            } else if (":findGenus".equals(line)) {
                handleFindGenus();

            } else if (":findID".equals(line)) {
                handleFindID();

            } else if (":findToPlant".equals(line)) {
                handleFindToPlant();

            } else if (":change".equals(line)) {
                handleChange();
            }
        }
    }

    private static void handleFindID() {
        System.out.println("Please enter the ID of the wanted cactus!");
        int id = getIDFromUser();

        Cacti cactus = hm.findID(id);
        if (cactus == null) {
            System.out.println("You can find nothing with this ID.\n");
        } else {
            System.out.format("The wanted cactus is: %s\n", cactus);
        }
    }

    private static void handleFindGenus() {
        System.out.println("Please enter the species of the wanted cactus!");
        Genus genus = getGenusFromUser();
        ArrayList<Cacti> cactiFiltered = hm.findGenus(genus);
        if (cactiFiltered.size() == 0) {
            System.out.println("You can find nothing with this name.\n");
        } else {
            System.out.println("The wanted cactus is (or cacti are):\n");
            for (int i = 0; i < cactiFiltered.size(); i++) {
                System.out.println(cactiFiltered.get(i));
            }
        }
    }

    private static void handleFindSpecies() {
        System.out.println("Please enter the species of the wanted cactus!");
        String species = scanner.nextLine();
        ArrayList<Cacti> cactiFiltered = hm.findSpecies(species);
        if (cactiFiltered.size() == 0) {
            System.out.println("You can find nothing with this name.\n");
        } else {
            System.out.println("The wanted cactus is (or cacti are):\n");
            for (int i = 0; i < cactiFiltered.size(); i++) {
                System.out.println(cactiFiltered.get(i));
            }
        }
    }

    private static void handleChange() {

        System.out.println("Please enter the ID of the wanted cactus!");
        int id = getIDToChangeCactus();
        Cacti cactus = hm.findID(id);
        if (cactus == null) {
            System.out.println("You can find nothing with this ID.\n");
        } else {
            System.out.format("The wanted cactus is: %s\n", cactus);
        }
        System.out.println("What is the Genus of your cactus?");
        Genus genus = getGenusFromUser();
        cactus.setGenus(genus);
        System.out.println("What is the species of your plant?");
        String species = scanner.nextLine();
        cactus.setSpecies(species);
        System.out.println("What was the year of planting?");
        int plantingYear = getYearFromUser();
        cactus.setPlantingYear(plantingYear);
        System.out.println("The cactus has changed with these values: " + cactus.toString());
        save();
    }

    private static void handleFindToPlant() {
        System.out.println("What should be replanted?");
        ArrayList<Cacti> cactiFiltered = hm.findToPlant();
        if (cactiFiltered.size() == 0) {
            System.out.println("It seems your cacti are ok with their soil.\n");
        } else {
            System.out.println("The following cacti could enjoy some new soil:\n");
            for (int i = 0; i < cactiFiltered.size(); i++) {
                System.out.println(cactiFiltered.get(i));
            }
        }
    }

    private static void handleList() {
        Cacti[] cactiList = hm.getCactiList();
        if (cactiList.length == 0) {
            System.out.println("\tYou can not find a single cactus at all in your list!");
        } else {
            System.out.println("You have the following cacti in your manager: \n");
            for (Cacti cactus : cactiList) {
                System.out.println("\t" + cactus);
            }
            System.out.println("\n\tYou have " + hm.getNumOfCacti() + " cacti in your manager now. ");
        }
    }

    private static void handleAdd() {
        int catalogID = hm.getNumOfCacti() + 1;
        System.out.println("The ID for this plant is " + catalogID);
        System.out.println("What is the Genus of your cactus?");
        Genus genus = getGenusFromUser();
        System.out.println("What is the species of your plant?");
        String species = scanner.nextLine();
        System.out.println("What was the year of planting?");
        int plantingYear = getYearFromUser();
        Cacti c = new Cacti(catalogID, genus, species, plantingYear);
        hm.addToCatalog(c);
        System.out.println("The cactus has added to the list with these values: " + c.toString());
        save();
    }

    private static int getIDFromUser() {
        int id;
        Cacti[] cactiList = hm.getCactiList();
        label:
        while (true) {
            try {
            id = Integer.parseInt(scanner.nextLine());
                for (Cacti cactus : cactiList) {
                    if (id == cactus.getCatalogID()) {
                        return id;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Wrong input!(Please enter a number!)");
            }
        }
    }

    private static int getIDToChangeCactus() {
        int id = 0;
        Cacti[] cactiList = hm.getCactiList();
        label:
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                for (Cacti cactus : cactiList) {
                    if (id == cactus.getCatalogID()) {
                        return id;
                    }
                }
                System.out.println("The given ID doesn't exist, please try an other!");
                continue label;
            }
            catch (Exception e) {
                System.out.println("Wrong input!(Please enter a number!)");
            }
        }
//        return id;
    }

    private static int getYearFromUser() {
        int plantingYear;
        while (true) {
            try {
                plantingYear = Integer.parseInt(scanner.nextLine());
                break;

            } catch (Exception e) {
                System.out.println("Wrong input!(Please enter a year!)");
            }
        }
        return plantingYear;
    }

    private static Genus getGenusFromUser() {
        Genus genus;
        while (true) {
            try {
                genus = Genus.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (Exception e) {
                System.out.println("Wrong input!(Please enter a correct genus!)");
            }
        }
        return genus;
    }

    private static void save() {
        try{
            hm.saveList();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Oops! Something went wrong while saving the list");
        }
    }
}
