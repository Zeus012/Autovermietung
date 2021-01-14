package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.RentalSystem;

import java.io.IOException;
import java.util.Scanner;

public class createRentalController {
    static int customerId;
    static int vehicleId;
    static double rentsDuration;
    static double price;
    static String status;

    private static boolean fA = false;

    public static void allCustomers() {
        System.out.println("Kunden");
        System.out.println("\tID / Nachname / Vorname / Wohnort");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllCustomers().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllCustomers().get(i).getCustomerId() + " / " +JsonConnection.getListAllCustomers().get(i).getLastname() + " / " + JsonConnection.getListAllCustomers().get(i).getFirstname() + " / " + JsonConnection.getListAllCustomers().get(i).getPlace());
        }
    }

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

    public static void captureRental(){
        Scanner rentalScan = new Scanner(System.in);
        System.out.println("Hier sind alle Fahrzeuge Aufgelistet");

        allVehicle();

        do {
            System.out.println("Bitte geben Sie die Id des Fahrzeuges ein");
            if (rentalScan.hasNextInt()) {
                vehicleId = rentalScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                rentalScan.next();
            }
        }while(fA == false);

        if(fA == true) {
            rentalScan.nextLine();
            System.out.println("Hier sind alle Kunden Aufgelistet");

            allCustomers();

            do {
                System.out.println("Bitte geben Sie die Id des Kunden ein");
                if (rentalScan.hasNextInt()) {
                    customerId = rentalScan.nextInt();
                    fA = true;
                }else {
                    System.out.println("Die Eingabe war leider ungültig.");
                    fA = false;
                    rentalScan.next();
                }
            }while(fA == false);

            if (fA == true) {
                rentalScan.nextLine();

                do {
                    System.out.println("Mietdauer [Tage]");
                    if (rentalScan.hasNextDouble()) {
                        rentsDuration = rentalScan.nextDouble();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        rentalScan.next();
                    }
                }while(fA == false);

                if (fA == true) {
                    rentalScan.nextLine();

                    do {
                        System.out.println("Preis [CHF]");
                        if (rentalScan.hasNextDouble()) {
                            price = rentalScan.nextDouble();
                            fA = true;
                        }else {
                            System.out.println("Die Eingabe war leider ungültig.");
                            fA = false;
                            rentalScan.next();
                        }
                    }while (fA == false);

                    if (fA == true) {
                        rentalScan.nextLine();

                        status = "";
                        while(status.equals("")){
                            System.out.println("Status [offen, bezahlt, abgeschlossen]");
                            status = rentalScan.nextLine();
                        }

                        RentalSystem rentalSystem = new RentalSystem(customerId,vehicleId, rentsDuration, price, status);
                        try {
                            JsonConnection.addRental(rentalSystem);
                        }catch(IOException e) {

                        }
                        Main.mainWindow();
                    }
                }
            }
        }
    }


}
