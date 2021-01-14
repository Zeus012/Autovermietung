package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.RentalSystem;

import java.io.IOException;
import java.util.Scanner;

public class editRentalController {

    public static RentalSystem rentalSystem;
    static int rentalId;
    static int customerId;
    static int vehicleId;
    static double rentsDuration;
    static double price;
    static String status;

    private static boolean fA = false;

    public static void editRentals() {
        Scanner rentalScan = new Scanner(System.in);
        System.out.println("Vermietung");
        System.out.println("\tVermietungs ID / Fahrzeug ID / Kunden ID / Status");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllRentings().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllRentings().get(i).getRentalId() + " / " + JsonConnection.getListAllRentings().get(i).getVehicleId() + " / " + JsonConnection.getListAllRentings().get(i).getCustomerId() + " / " + JsonConnection.getListAllRentings().get(i).getStatus());
        }

        do {
            System.out.println("Bitte geben Sie die Id der Vermietung ein, welche zu ändern ist.");
            if (rentalScan.hasNextInt()) {
                rentalId = rentalScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                rentalScan.next();
            }
        }while(fA == false);

        try {
            JsonConnection.showRental(rentalId);
        }catch(IOException e) {

        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Wollen Sie die Fahrzeug ID ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String vehicleIdChoice = scan.nextLine();
        switch (vehicleIdChoice) {
            case "j":
                do {
                    allVehicle();
                    System.out.println("______________________________________________");
                    System.out.println("Alte Fahrzeug ID  " + rentalSystem.getVehicleId());
                    System.out.print("Neue Fahrzeug ID  ");
                    if (scan.hasNextInt()) {
                        vehicleId = scan.nextInt();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        scan.next();
                    }
                }while(fA == false);
                scan.nextLine();
                break;
            case "n":
                try {
                    vehicleId = rentalSystem.getVehicleId();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Kunden ID ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String customerIdChoice = scan.nextLine();
        switch (customerIdChoice) {
            case "j":
                do {
                    allCustomers();
                    System.out.println("______________________________________________");
                    System.out.println("Alte Kunden ID:  " + rentalSystem.getCustomerId());
                    System.out.print("Neue Kunden ID  ");
                    if (scan.hasNextInt()) {
                        customerId = scan.nextInt();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        scan.next();
                    }
                }while(fA == false);
                scan.nextLine();
                break;
            case "n":
                try {
                    customerId = rentalSystem.getCustomerId();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Mietdauer ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String rentsDurationChoice = scan.nextLine();
        switch (rentsDurationChoice) {
            case "j":
                do {
                    System.out.println("Alte Mietdauer [Tage]  " + rentalSystem.getRentsDuraction());
                    System.out.print("Neue Mietdauer [Tage]  ");
                    if (scan.hasNextDouble()) {
                        rentsDuration = scan.nextDouble();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        scan.next();
                    }
                }while(fA == false);
                scan.nextLine();
                break;
            case "n":
                try {
                    rentsDuration = rentalSystem.getRentsDuraction();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie den Preis ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String priceChoice = scan.nextLine();
        switch (priceChoice) {
            case "j":
                do {
                    System.out.println("Alter Preis [CHF]  " + rentalSystem.getPrice());
                    System.out.print("Neuer Preis [CHF]  ");
                    if (scan.hasNextDouble()) {
                        price = scan.nextDouble();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        scan.next();
                    }
                }while (fA == false);
                scan.nextLine();
                break;
            case "n":
                try {
                    price = rentalSystem.getPrice();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie den Status ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String statusChoice = scan.nextLine();
        switch (statusChoice) {
            case "j":
                    status = "";
                    while(status.equals("")){
                        System.out.println("Alter Status  " + rentalSystem.getStatus());
                        System.out.print("Neuer Status [offen, bezahlt, abgeschlossen]  ");
                        status = scan.nextLine();
                    }
                break;
            case "n":
                try {
                    status = rentalSystem.getStatus();
                }catch (NullPointerException e){

                }
                break;
        }

        try{
            rentalId = rentalSystem.getRentalId();
        }catch (NullPointerException e){

        }

        RentalSystem rentalSystem = new RentalSystem(customerId,vehicleId, rentsDuration, price, status);
        try {
            JsonConnection.changeRental(rentalSystem, rentalId);
        }catch(IOException e) {

        }
        Main.mainWindow();
    }



    //Alle Listen
    public static void allCars() {
        System.out.println("Autos");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllCars().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllCars().get(i).getVehicleId() + " / " + JsonConnection.getListAllCars().get(i).getBrand() + " / " + JsonConnection.getListAllCars().get(i).getModel() + " / " + JsonConnection.getListAllCars().get(i).isAvailable());
        }
    }
    public static void allBikes() {
        System.out.println("Motorräder");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllBikes().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllBikes().get(i).getVehicleId() + " / " + JsonConnection.getListAllBikes().get(i).getBrand() + " / " + JsonConnection.getListAllBikes().get(i).getModel() + " / " + JsonConnection.getListAllBikes().get(i).isAvailable());
        }
    }
    public static void allTransporters() {
        System.out.println("Transporter");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllTransporters().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllTransporters().get(i).getVehicleId() + " / " + JsonConnection.getListAllTransporters().get(i).getBrand() + " / " + JsonConnection.getListAllTransporters().get(i).getModel() + " / " + JsonConnection.getListAllTransporters().get(i).isAvailable());
        }
    }

    public static void allVehicle() {
        allCars();
        allBikes();
        allTransporters();
    }

    public static void allCustomers() {
        System.out.println("Kunden");
        System.out.println("\tID / Nachname / Vorname / Wohnort");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllCustomers().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllCustomers().get(i).getCustomerId() + " / " +JsonConnection.getListAllCustomers().get(i).getLastname() + " / " + JsonConnection.getListAllCustomers().get(i).getFirstname() + " / " + JsonConnection.getListAllCustomers().get(i).getPlace());
        }
    }
}
