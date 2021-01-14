package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.Bike;
import com.company.Autovermietung.Model.Car;
import com.company.Autovermietung.Model.Transporter;
import com.company.Autovermietung.Model.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class editVehicleController {

    public static Vehicle vehicle;
    public static Car car;
    public static Bike bike;
    public static Transporter transporter;
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

    public static void editVehicle() {
        System.out.println("Welches Fahrzeugtyp möchten Sie ändern?");
        System.out.println("\t1. Auto");
        System.out.println("\t2. Motorrad");
        System.out.println("\t3. Transporter");
        System.out.println("\t4. Zurück zum Menu");


        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("[1234]")){
            System.out.println("Das ist keine gültige Option");
            scanner.nextLine();
        }
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case "1":
                editCars();
                break;
            case "2":
                editBikes();
                break;
            case "3":
                editTransporters();
                break;
            case "4":
                Main.mainWindow();
                break;
        }
    }

    public static void editCars() {
        Scanner carScan = new Scanner(System.in);
        System.out.println("Autos");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllCars().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllCars().get(i).getVehicleId() + " / " + JsonConnection.getListAllCars().get(i).getBrand() + " / " + JsonConnection.getListAllCars().get(i).getModel() + " / " + JsonConnection.getListAllCars().get(i).isAvailable());
        }

        do {
            System.out.println("Bitte geben Sie die Id des Fahrzeuges ein");
            if (carScan.hasNextInt()) {
                vehicleId = carScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                carScan.next();
            }
        }while(fA == false);

        try {
            JsonConnection.showCar(vehicleId);
            JsonConnection.showVehicle(vehicleId);
        }catch(IOException e) {

        }

        allVehiclesAtributes();

        carScan.nextLine();
        System.out.println("Wollen Sie den Kofferraumplatz ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String trunkSpaceChoice = carScan.nextLine();
        switch (trunkSpaceChoice) {
            case "j":
                do {
                    System.out.println("Kofferraumplatz für Anzahl Koffer [2,3,4,5]  " + car.getTrunkSpace());
                    System.out.print("Neuer Kofferraumplatz für Anzahl Koffer [2,3,4,5]  ");
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
                break;
            case "n":
                try {
                    trunkSpace = car.getTrunkSpace();
                }catch (NullPointerException e){

                }

                break;
        }
        System.out.println("Wollen Sie den Aufbau ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String constructionChoice = carScan.nextLine();
        switch (constructionChoice) {
            case "j":
                construction = "";
                while(construction.equals("")){
                    System.out.println("Aufbau [Kleinwagen, Limousine, Kombi, SUV, Cabriolet]  " + car.getConstruction());
                    System.out.print("Neuer Aufbau [Kleinwagen, Limousine, Kombi, SUV, Cabriolet]  ");
                    construction = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    construction = car.getConstruction();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie das Navigationssystem ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String brandChoice = carScan.nextLine();
        switch (brandChoice) {
            case "j":
                do {
                    System.out.println("Navigationssystem [true/false]  " + car.isNavigationSystem());
                    System.out.print("Neuer Navigationssystem [true/false]  ");
                    if (carScan.hasNextBoolean()){
                        navigationSystem = carScan.nextBoolean();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        carScan.nextLine();
                    }
                }while (fA == false);
                break;
            case "n":
                try {
                    navigationSystem = car.isNavigationSystem();
                }catch (NullPointerException e){

                }
                break;
        }
        try{
            vehicleId = vehicle.getVehicleId();
        }catch (NullPointerException e){

        }

        Car car = new Car(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available, trunkSpace, construction, navigationSystem);
        try {
            JsonConnection.changeCar(car, vehicleId);
        }catch(IOException e) {

        }
        Main.mainWindow();
    }


    public static void editBikes() {
        Scanner bikescan = new Scanner(System.in);
        System.out.println("Motorräder");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllBikes().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllBikes().get(i).getVehicleId() + " / " + JsonConnection.getListAllBikes().get(i).getBrand() + " / " + JsonConnection.getListAllBikes().get(i).getModel() + " / " + JsonConnection.getListAllBikes().get(i).isAvailable());
        }

        do {
            System.out.println("Bitte geben Sie die Id des Fahrzeuges ein");
            if (bikescan.hasNextInt()) {
                vehicleId = bikescan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                bikescan.next();
            }
        }while(fA == false);

        try {
            JsonConnection.showBike(vehicleId);
            JsonConnection.showVehicle(vehicleId);
        }catch(IOException e) {

        }

        allVehiclesAtributes();

        System.out.println("Wollen Sie den Tankvolumen ändern? [j/n]");
        while (!bikescan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            bikescan.nextLine();
        }
        String tankVolumeChoice = bikescan.nextLine();
        switch (tankVolumeChoice) {
            case "j":
                do {
                    System.out.println("Tankvolumen [Liter]  " + bike.getTankVolume());
                    System.out.print("Neuer Tankvolumen [Liter]  ");
                    if (bikescan.hasNextDouble()) {
                        tankVolume = bikescan.nextDouble();
                        fA = true;
                    }else{
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        bikescan.nextLine();
                    }
                }while (fA == false);
                bikescan.nextLine();
                break;
            case "n":
                try {
                    tankVolume = bike.getTankVolume();
                }catch (NullPointerException e){

                }
                break;
        }

        try{
            vehicleId = vehicle.getVehicleId();
        }catch (NullPointerException e){

        }

        Bike bike = new Bike(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available, tankVolume);
        try {
            JsonConnection.changeBike(bike, vehicleId);
        }catch(IOException e) {

        }
        Main.mainWindow();
    }


    public static void editTransporters() {
        Scanner transporterScan = new Scanner(System.in);
        System.out.println("Transporter");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllTransporters().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllTransporters().get(i).getVehicleId() + " / " + JsonConnection.getListAllTransporters().get(i).getBrand() + " / " + JsonConnection.getListAllTransporters().get(i).getModel() + " / " + JsonConnection.getListAllTransporters().get(i).isAvailable());
        }

        do {
            System.out.println("Bitte geben Sie die Id des Fahrzeuges ein");
            if (transporterScan.hasNextInt()) {
                vehicleId = transporterScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                transporterScan.next();
            }
        }while(fA == false);

        try {
            JsonConnection.showTransporter(vehicleId);
            JsonConnection.showVehicle(vehicleId);
        }catch(IOException e) {

        }

        allVehiclesAtributes();

        System.out.println("Wollen Sie das Ladegewicht ändern? [j/n]");
        while (!transporterScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            transporterScan.nextLine();
        }
        String loadingWeightChoice = transporterScan.nextLine();
        switch (loadingWeightChoice) {
            case "j":
                do {
                    System.out.println("Ladegewicht [Kg]  " + transporter.getLoadingWeight());
                    System.out.print("Ladegewicht [Kg]  ");
                    if (transporterScan.hasNextInt()) {
                        loadingWeight = transporterScan.nextInt();
                        fA = true;
                    }else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        fA = false;
                        transporterScan.nextLine();
                    }
                }while (fA == false);
                transporterScan.nextLine();
                break;
            case "n":
                try {
                    loadingWeight = transporter.getLoadingWeight();
                }catch (NullPointerException e){

                }
                break;
        }
        try{
            vehicleId = vehicle.getVehicleId();
        }catch (NullPointerException e){

        }

        Transporter transporter = new Transporter(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available, loadingWeight);
        try {
            JsonConnection.changeTransporter(transporter, vehicleId);
        }catch(IOException e) {

        }
        Main.mainWindow();
    }



    public static void allVehiclesAtributes(){
        Scanner carScan = new Scanner(System.in);
        System.out.println("Wollen Sie die Marke ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String brandChoice = carScan.nextLine();
        switch (brandChoice) {
            case "j":
                brand = "";
                while(brand.equals("")){
                    System.out.println("Marke:  " + vehicle.getBrand());
                    System.out.print("Neue Marke:  ");
                    brand = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    brand = vehicle.getBrand();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie das Model ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String modelChoice = carScan.nextLine();
        switch (modelChoice) {
            case "j":
                model = "";
                while(model.equals("")){
                    System.out.println("Model:  " + vehicle.getModel());
                    System.out.print("Neues Model:  ");
                    model = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    model = vehicle.getModel();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Hubraumgrösse ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String cubicCapacityChoice = carScan.nextLine();
        switch (cubicCapacityChoice) {
            case "j":
                do {
                    System.out.println("Hubraum [ccm]:  " + vehicle.getCubicCapacity());
                    System.out.print("Neues Hubraum [ccm]:  ");
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
                break;
            case "n":
                try {
                    cubicCapacity = vehicle.getCubicCapacity();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Triebstoffart ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String fuelTypeChoice = carScan.nextLine();
        switch (fuelTypeChoice) {
            case "j":
                fuelType = "";
                while(fuelType.equals("")){
                    System.out.println("Treibstoffart [Benzin, Diesel, Elektrisch]  " + vehicle.getFuelType());
                    System.out.print("Neue Treibstoffart [Benzin, Diesel, Elektrisch]  ");
                    fuelType = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    fuelType = vehicle.getFuelType();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Aussenfarbe ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String outerColerChoice = carScan.nextLine();
        switch (outerColerChoice) {
            case "j":
                outerColer = "";
                while(outerColer.equals("")){
                    System.out.println("Aussenfarbe:  " + vehicle.getOuterColor());
                    System.out.print("Neue Aussenfarbe:  ");
                    outerColer = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    outerColer = vehicle.getOuterColor();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie den Aktuellen Km-Stand ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String currentKmValueChoice = carScan.nextLine();
        switch (currentKmValueChoice) {
            case "j":
                do {
                    System.out.println("Aktueller Km-Stand:  " + vehicle.getCurrentKmValue());
                    System.out.print("Neuer Aktueller Km-Stand:  ");
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
                break;
            case "n":
                try {
                    currentKmValue = vehicle.getCurrentKmValue();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie das Kennzeichen ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String licensePlateChoice = carScan.nextLine();
        switch (licensePlateChoice) {
            case "j":
                licensePlate = "";
                while(licensePlate.equals("")){
                    System.out.println("Kennzeichen:  " + vehicle.getLicensePlate());
                    System.out.print("Kennzeichen:  ");
                    licensePlate = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    licensePlate = vehicle.getLicensePlate();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Kategorie ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String categoryChoice = carScan.nextLine();
        switch (categoryChoice) {
            case "j":
                System.out.println("Kategorie [Basic, Medium, Luxus]  " + vehicle.getCategory());
                System.out.print("Neue Kategorie [Basic, Medium, Luxus]  ");
                category = carScan.nextLine();
                while (!category.matches("Basic") && !category.matches("Medium") && !category.matches("Luxus")){
                    System.out.println("Die Eingabe war leider ungültig.");
                    category = carScan.nextLine();
                }
                break;
            case "n":
                try {
                    category = vehicle.getCategory();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie das Verfügbar ab Datum ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String dateFromChoice = carScan.nextLine();
        switch (dateFromChoice) {
            case "j":
                dateFrom();
                break;
            case "n":
                try {
                    availableFrom = vehicle.getAvailableFrom();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie das Verfügbar bis Datum ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String dateTillChoice = carScan.nextLine();
        switch (dateTillChoice) {
            case "j":
                dateTill();
                break;
            case "n":
                try {
                    availableTill = vehicle.getAvailableTill();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Verfügbarkeit ändern? [j/n]");
        while (!carScan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            carScan.nextLine();
        }
        String availableChoice = carScan.nextLine();
        switch (availableChoice) {
            case "j":
                do {
                    System.out.println("Verfügbar [true/false]  " + vehicle.isAvailable());
                    System.out.print("Neue Verfügbarkeit [true/false]  ");
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
                break;
            case "n":
                try {
                    available = vehicle.isAvailable();
                }catch (NullPointerException e){

                }
                break;
        }
    }

    public static void dateFrom(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Verfügbar ab Datum [ex: 12-30-1993]  " + vehicle.getAvailableFrom());
        System.out.print("Neue Verfügbarkeit ab Datum [ex: 12-30-1993]  ");
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

        System.out.println("Verfügbar bis Datum [ex: 12-25-2013]  " + vehicle.getAvailableTill());
        System.out.print("Neue Verfügbarkeit bis Datum [ex: 12-25-2013]  ");
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
