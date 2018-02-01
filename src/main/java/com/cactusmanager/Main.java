package com.cactusmanager;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
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
        System.out.println("You can use these commands: :list, :add, :findName, :findID, :change, :exit\n");
        while (true) {
            String line = scanner.nextLine();
            if (!(":exit".equals(line) ||
                ":list".equals(line) ||
                ":add".equals(line) ||
                ":findName".equals(line) ||
                ":findID".equals(line) ||
                ":change".equals(line))) {
                System.out.println("Wrong command! You can use :list, :add, :findName, :findID, :change, :exit\n");
            } else if (":exit".equals(line)) {
                try{
                    hm.saveList();
                }catch(IOException e){
                    e.printStackTrace();
                    System.out.println("Oops! Something went wrong while saving the list");
                }
                break;

            } else if (":list".equals(line)) {
                handleList();

            } else if (":add".equals(line)) {
                handleAdd();

            } else if (":findName".equals(line)) {
                handleFindName();

            } else if (":findID".equals(line)) {
                handleFindID();

            } else if (":change".equals(line)) {
                handleChange();
            }
        }
    }

    private static void handleFindID() {
        System.out.println("Please enter the ID of the wanted cactus!");
        int id = getNumberFromUser();

        Cacti cactus = hm.findID(id);
        if (cactus == null) {
            System.out.println("You can find nothing with this ID.\n");
        } else {
            System.out.format("The wanted cactus is: %s\n", cactus);
        }
    }

    private static void handleFindName() {
        System.out.println("Please enter the species of the wanted cactus!");
        String species = scanner.nextLine();
        Cacti[] cactiFiltered = hm.findName(species);
        if (cactiFiltered.length == 0) {
            System.out.println("You can find nothing with this name.\n");
        } else {
            System.out.println("The wanted cactus is (or cacti are):\n");
            for (int i = 0; i < cactiFiltered.length; i++) {
                System.out.println(cactiFiltered[i]);
            }
        }
    }

    private static void handleChange() {

        System.out.println("Please enter the ID of the wanted cactus!");
        int id = getNumberFromUser();
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
        System.out.println("What is the ID for this plant?");
        int catalogID = getNumberFromUser();
        System.out.println("What is the Genus of your cactus?");
        Genus genus = getGenusFromUser();
        System.out.println("What is the species of your plant?");
        String species = scanner.nextLine();
        System.out.println("What was the year of planting?");
        int plantingYear = getYearFromUser();
        Cacti c = new Cacti(catalogID, genus, species, plantingYear);
        hm.addToCatalog(c);
        System.out.println("The cactus has added to the list with these values: " + c.toString());
    }

    private static int getNumberFromUser() {
        int id;
        Cacti[] cactiList = hm.getCactiList();
        label:
        while (true) {

            try {
            id = Integer.parseInt(scanner.nextLine());

                for (Cacti cactus : cactiList) {
                    if (id == cactus.getCatalogID()) {
                        System.out.println("The given ID is taken, please try an other!");
                        continue label;
                    }
                }
                break;

            }
            catch (Exception e) {
                System.out.println("Wrong input!(Please enter a number!)");
            }
        }
        return id;
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
}
