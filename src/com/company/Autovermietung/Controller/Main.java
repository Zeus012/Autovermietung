package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.Customer;
import com.company.Autovermietung.Model.RentalSystem;
import com.company.Autovermietung.Model.Vehicle;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        JsonConnection.readJson();
        Vehicle.setCounter(JsonConnection.getAllVehicleListSize());
        Customer.setCounter(JsonConnection.getAllCustomersListSize());
        RentalSystem.setCounter(JsonConnection.getListAllRentingsSize());
        mainWindow();
    }

    public static void mainWindow() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Willkommen in der Autovermietungs Applikation.\nBitte wählen Sie eine Option");
            System.out.println("\t 1. Fahrzeug erfassen");
            System.out.println("\t 2. Fahrzeug ändern");
            System.out.println("\t 3. Kunde erfassen");
            System.out.println("\t 4. Kunde ändern");
            System.out.println("\t 5. Fahrzeug Verfügbarkeit ändern");
            System.out.println("\t 6. Vermietung erfassen");
            System.out.println("\t 7. Vermietung ändern");
            System.out.println("\t 8. Vermietung Status");
            System.out.println("\t 9. Fahrzeug suchen");
            System.out.println("\t E. Applikation schliessen");

            String userChoice = scanner.nextLine();

            while(!userChoice.matches("[123456789Ee]")){
                System.out.println("Das ist keine gültige Option");
                userChoice = scanner.nextLine();
            }

            switch (userChoice) {
                case "1":
                    createVehicleController.selectVehicle();
                    break;
                case "2":
                    editVehicleController.editVehicle();
                    break;
                case "3":
                    createCustomerController.createCustomer();
                    break;
                case "4":
                    editCustomerController.editCustomers();
                    break;
                case "5":
                    avaiableVehicleController.markVehicles();
                    break;
                case "6":
                    createRentalController.captureRental();
                    break;
                case "7":
                    editRentalController.editRentals();
                    break;
                case "8":
                    statusRentalController.changeStatusRental();
                    break;
                case "9":
                    searchVehicleController.searchVehicle();
                    break;
                case "E":
                    System.exit(0);
                    break;
            }
    }
}
