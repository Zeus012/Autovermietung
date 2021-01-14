package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.Bike;
import com.company.Autovermietung.Model.Car;
import com.company.Autovermietung.Model.Transporter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class createVehicleController {

    //Vehicle
    static int vehicleId;
    static String brand;
    static String model;
    static double cubicCapacity;
    static String fuelType;
    static String outerColer;
    static double currentKmValue;
    static String licensePlate;
    static String category;
    static String availableFrom;
    static String availableTill;
    static boolean available;

    //Car
    static int trunkSpace;
    static String construction;
    static boolean navigationSystem;

    //Bike
    static double tankVolume;

    //Transporter
    static int loadingWeight;

    private static boolean fA = false;

    public static void selectVehicle() {
        System.out.println("1. Auto");
        System.out.println("2. Motorrad");
        System.out.println("3. Transporter");
        System.out.println("4. Zurück zum Menu");


        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("[1234]")){
            System.out.println("Das ist keine gültige Option");
            scanner.nextLine();
        }
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case "1":
                captureCar();
                break;
            case "2":
                captureBike();
                break;
            case "3":
                captureTransporter();
                break;
            case "4":
                Main.mainWindow();
                break;
        }
    }

    public static void vehicleInfo(){
        Scanner carScan = new Scanner(System.in);

        brand = "";
        while(brand.equals("")){
            System.out.println("Marke");
            brand = carScan.nextLine();
        }

        model = "";
        while(model.equals("")){
            System.out.println("Modell");
            model = carScan.nextLine();
        }

        do {
            System.out.println("Hubraum [ccm]");
            if(carScan.hasNextDouble()) {
                cubicCapacity = carScan.nextDouble();
                fA = true;
            }else{
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);

        carScan.nextLine();


        fuelType = "";
        while(fuelType.equals("")){
            System.out.println("Treibstoffart [Benzin, Diesel, Elektrisch]");
            fuelType = carScan.nextLine();
        }

        outerColer = "";
        while(outerColer.equals("")){
            System.out.println("Aussenfarbe");
            outerColer = carScan.nextLine();
        }


        do {
            System.out.println("Aktueller Km-Stand");
            if(carScan.hasNextDouble()) {
                currentKmValue = carScan.nextDouble();
                fA = true;
            }else{
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);

        carScan.nextLine();

        licensePlate = "";
        while(licensePlate.equals("")){
            System.out.println("Kennzeichen");
            licensePlate = carScan.nextLine();
        }

        System.out.println("Kategorie [Basic, Medium, Luxus]");
        category = carScan.nextLine();
        while (!category.matches("Basic") && !category.matches("Medium") && !category.matches("Luxus")){
            System.out.println("Die Eingabe war leider ungültig.");
            category = carScan.nextLine();
        }

        dateFrom();

        dateTill();

        do {
            System.out.println("Verfügbar [true/false]");
            if(carScan.hasNextBoolean()) {
                available = carScan.nextBoolean();
                fA = true;
            }else{
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);


        carScan.nextLine();

    }


    public static void captureCar(){
        Scanner carScan = new Scanner(System.in);

        System.out.println("Erfassen Sie ein neues Auto");
        vehicleInfo();

        do {
            System.out.println("Kofferraumplatz für Anzahl Koffer [2,3,4,5]");
            if (carScan.hasNextInt()) {
                trunkSpace = carScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);

        carScan.nextLine();

        construction = "";
        while(construction.equals("")){
            System.out.println("Aufbau [Kleinwagen, Limousine, Kombi, SUV, Cabriolet]");
            construction = carScan.nextLine();
        }

        do {
            System.out.println("Navigationssystem [true/false]");
            if (carScan.hasNextBoolean()){
                navigationSystem = carScan.nextBoolean();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);

        carScan.nextLine();
        Car car = new Car(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available, trunkSpace, construction, navigationSystem);
        try {
            JsonConnection.addCar(car);
        }catch(IOException e) {

        }
        Main.mainWindow();
    }

    public static void captureBike(){
        Scanner carScan = new Scanner(System.in);

        System.out.println("Erfassen Sie ein neues Motorrad");
        vehicleInfo();

        do {
            System.out.println("Tankvolumen [Liter]");
            if (carScan.hasNextDouble()) {
                tankVolume = carScan.nextDouble();
                fA = true;
            }else{
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);

        if (fA == true){
            carScan.nextLine();
            Bike bike = new Bike(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available, tankVolume);
            try {
                JsonConnection.addBike(bike);
            }catch(IOException e) {

            }
            Main.mainWindow();
        }
    }

    public static void captureTransporter(){
        Scanner carScan = new Scanner(System.in);

        System.out.println("Erfassen Sie ein neuen Transporter");
        vehicleInfo();

        do {
            System.out.println("Ladegewicht [Kg]");
            if (carScan.hasNextInt()) {
                loadingWeight = carScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.nextLine();
            }
        }while (fA == false);

        carScan.nextLine();
        Transporter transporter = new Transporter(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available, loadingWeight);
        try {
            JsonConnection.addTransporter(transporter);
        }catch(IOException e) {
        }
        Main.mainWindow();
    }

    public static void dateFrom(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Verfügbar ab Datum [ex: 12-30-1993]");
        do {
            availableFrom = sc.nextLine();

            try {
                Date date = sdf.parse(availableFrom);

                sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                System.out.println("Datum: " + sdf.format(date));
                fA = true;
            } catch (ParseException e) {
                System.out.println("Eingabe war falsch, bitte geben Sie ein gültiges Datum ein");
                fA = false;
            }
        }while(fA == false);
    }

    public static void dateTill(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Verfügbar bis Datum [ex: 12-25-2013]");
        do {
            availableTill = sc.nextLine();

            try {
                Date date = sdf.parse(availableTill);

                sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                System.out.println("Datum: " + sdf.format(date));
                fA = true;
            } catch (ParseException e) {
                System.out.println("Eingabe war falsch, bitte geben Sie ein gültiges Datum ein");
                fA = false;
            }
        }while(fA == false);
    }
}
