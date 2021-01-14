package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;

import java.util.Scanner;

public class searchVehicleController {

    static String vehicleSelect;

    public static void searchVehicle(){
        System.out.println("Nach was suchen Sie?");
        System.out.println("\t1. Fahrzeugtyp");
        System.out.println("\t2. Fahrzeug Kategorie");
        System.out.println("\t3. Verfügbare Fahrzeuge");
        System.out.println("\t4. Zurück zum Menu");


        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("[1234]")){
            System.out.println("Das ist keine gültige Option");
            scanner.nextLine();
        }
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case "1":
                selectVehicle();
                break;
            case "2":
                selectCategory();
                break;
            case "3":
                selectavailableVehicle();
                break;
            case "4":
                Main.mainWindow();
                break;
        }
    }

    public static void selectavailableVehicle() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tID / Marke / Model / Km-Stand");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllVehicles().size(); i++) {
            if (JsonConnection.getListAllVehicles().get(i).isAvailable()) {
                System.out.println("\t" + JsonConnection.getListAllVehicles().get(i).getVehicleId() + " / " + JsonConnection.getListAllVehicles().get(i).getBrand() + " / " + JsonConnection.getListAllVehicles().get(i).getModel() + " / " + JsonConnection.getListAllVehicles().get(i).getCurrentKmValue());
            }
        }
        if (scan.hasNextLine()){
            Main.mainWindow();
        }
    }

    public static void selectVehicle() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Suchen Sie nach Fahrzeugtyp [Auto, Motorrad, Transporter]");
        vehicleSelect = scan.nextLine();
        while (!vehicleSelect.matches("Auto") && !vehicleSelect.matches("Motorrad") && !vehicleSelect.matches("Transporter")){
            System.out.println("Dieser Fahrzeugtyp existiert nicht.");
            vehicleSelect = scan.nextLine();
        }
        if (vehicleSelect.matches("Auto")){
            System.out.println("\tID / Marke / Model / Verfügbar");
            System.out.println("\t___________________________________");
            for (int i = 0; i < JsonConnection.getListAllCars().size(); i++) {
                System.out.println("\t" + JsonConnection.getListAllCars().get(i).getVehicleId() + " / " + JsonConnection.getListAllCars().get(i).getBrand() + " / " + JsonConnection.getListAllCars().get(i).getModel() + " / " + JsonConnection.getListAllCars().get(i).isAvailable());
            }
            if (scan.hasNextLine()){
                Main.mainWindow();
            }
        }
        if(vehicleSelect.matches("Motorrad")){
            System.out.println("\tID / Marke / Model / Verfügbar");
            System.out.println("\t___________________________________");
            for (int i = 0; i < JsonConnection.getListAllBikes().size(); i++) {
                System.out.println("\t" + JsonConnection.getListAllBikes().get(i).getVehicleId() + " / " + JsonConnection.getListAllBikes().get(i).getBrand() + " / " + JsonConnection.getListAllBikes().get(i).getModel() + " / " + JsonConnection.getListAllBikes().get(i).isAvailable());
            }
            if (scan.hasNextLine()){
                Main.mainWindow();
            }
        }
        if (vehicleSelect.matches("Transporter")){
            System.out.println("\tID / Marke / Model / Verfügbar");
            System.out.println("\t___________________________________");
            for (int i = 0; i < JsonConnection.getListAllTransporters().size(); i++) {
                System.out.println("\t" + JsonConnection.getListAllTransporters().get(i).getVehicleId() + " / " + JsonConnection.getListAllTransporters().get(i).getBrand() + " / " + JsonConnection.getListAllTransporters().get(i).getModel() + " / " + JsonConnection.getListAllTransporters().get(i).isAvailable());
            }
            if (scan.hasNextLine()){
                Main.mainWindow();
            }
        }

    }

    public static void selectCategory(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Suchen Sie nach Fahrzeug Kategorie [Basic, Medium, Luxus]");
        vehicleSelect = scan.nextLine();
        while (!vehicleSelect.matches("Basic") && !vehicleSelect.matches("Medium") && !vehicleSelect.matches("Luxus")){
            System.out.println("Dieser Fahrzeugtyp existiert nicht.");
            vehicleSelect = scan.nextLine();
        }
        if (vehicleSelect.matches("Basic")){
            System.out.println("\tID / Marke / Model / Verfügbar");
            System.out.println("\t___________________________________");
            for (int i = 0; i < JsonConnection.getListAllVehicles().size(); i++) {
                if (JsonConnection.getListAllVehicles().get(i).getCategory().matches("Basic")) {
                    System.out.println("\t" + JsonConnection.getListAllVehicles().get(i).getVehicleId() + " / " + JsonConnection.getListAllVehicles().get(i).getBrand() + " / " + JsonConnection.getListAllVehicles().get(i).getModel() + " / " + JsonConnection.getListAllVehicles().get(i).isAvailable());
                }
            }
            if (scan.hasNextLine()){
                Main.mainWindow();
            }
        }
        if(vehicleSelect.matches("Medium")){
            System.out.println("\tID / Marke / Model / Verfügbar");
            System.out.println("\t___________________________________");
            for (int i = 0; i < JsonConnection.getListAllBikes().size(); i++) {
                if (JsonConnection.getListAllVehicles().get(i).getCategory().matches("Medium")) {
                    System.out.println("\t" + JsonConnection.getListAllVehicles().get(i).getVehicleId() + " / " + JsonConnection.getListAllVehicles().get(i).getBrand() + " / " + JsonConnection.getListAllVehicles().get(i).getModel() + " / " + JsonConnection.getListAllVehicles().get(i).isAvailable());
                }            }
            if (scan.hasNextLine()){
                Main.mainWindow();
            }
        }
        if (vehicleSelect.matches("Luxus")){
            System.out.println("\tID / Marke / Model / Verfügbar");
            System.out.println("\t___________________________________");
            for (int i = 0; i < JsonConnection.getListAllTransporters().size(); i++) {
                if (JsonConnection.getListAllVehicles().get(i).getCategory().matches("Luxus")) {
                    System.out.println("\t" + JsonConnection.getListAllVehicles().get(i).getVehicleId() + " / " + JsonConnection.getListAllVehicles().get(i).getBrand() + " / " + JsonConnection.getListAllVehicles().get(i).getModel() + " / " + JsonConnection.getListAllVehicles().get(i).isAvailable());
                }             }
            if (scan.hasNextLine()){
                Main.mainWindow();
            }
        }

    }
}