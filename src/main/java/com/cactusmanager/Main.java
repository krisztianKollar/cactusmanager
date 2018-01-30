package com.cactusmanager;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static HandleManager hm;

    public static void main(String[] args) {
        System.out.println("Welcome to the Catalog of Cacti!");
        System.out.println("You can use these commands: :list, :add, :findName, :findID, :change, :exit\n");
        while (true) {
            String line = scanner.nextLine();
            if (":exit".equals(line)) {
                break;

            } else if (":list".equals(line)) {
                handleList();

            } else if (":add".equals(line)) {
                handleAdd();

            } else if (":findName".equals(line)) {
                handleFindName();

            } else if (":findID".equals(line)) {
                handleFindID();

            } else if ("::change".equals(line)) {
                handleChange();
            }
        }
    }

    private static void handleFindID() {
        System.out.println("Please enter the ID of the wanted cactus!");
        int id = Integer.parseInt(scanner.nextLine());  // NumberformatException? 

        Cacti cactus = cactiList.findID(); //??? cactilistben keresni forral!
        if (cactus == null) {
            System.out.println("You can find nothing with this ID.\n");
        } else {
            System.out.format("The wanted cactus is:\n", cactus);
        }
    }

    private static void handleList() {
        // Cacti[][] cactiList = ();

        System.out.println("Some string... ");
        if (cactiList.length == 0) {
            System.out.println("You can not find a single cactus at all!");
        } else {
            for (Cacti cactus : cactiList) {
                System.out.println("\t" + cactus);
            }
        }
    }

    private static void handleAdd() {
        Cacti[] cactus = new Cacti[3];
        System.out.println("What is the ID for this plant?");
        String catalogID = scanner.nextLine();
        catalogID = cactus[0];
        System.out.println("What is the Genus of your cactus?");
        Genus genus = scanner.nextLine();
        genus = cactus[1];
        System.out.println("What is the species of your plant?");
        String species = scanner.nextLine();
        species = cactus[2];
        System.out.println("What was the year of planting?");
        String plantingYear = scanner.nextLine();
        plantingYear = cactus[3];
        addToCatalog(cactus);  // változóba kell tenni?   
        //fájlba írás? serialisation?
        System.out.println("The cactus has added to the list.");
    }
}