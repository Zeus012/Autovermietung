package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.Vehicle;
import javafx.scene.transform.Scale;

import java.io.IOException;
import java.util.Scanner;

public class avaiableVehicleController {
    static int vehicleId;
    static boolean available;
    private static boolean fA = false;

    public static void markVehicles(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Autos");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllCars().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllCars().get(i).getVehicleId() + " / " + JsonConnection.getListAllCars().get(i).getBrand() + " / " + JsonConnection.getListAllCars().get(i).getModel() + " / " + JsonConnection.getListAllCars().get(i).isAvailable());
        }
        System.out.println("Motorräder");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllBikes().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllBikes().get(i).getVehicleId() + " / " + JsonConnection.getListAllBikes().get(i).getBrand() + " / " + JsonConnection.getListAllBikes().get(i).getModel() + " / " + JsonConnection.getListAllBikes().get(i).isAvailable());
        }
        System.out.println("Transporter");
        System.out.println("\tID / Marke / Model / Verfügbar");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllTransporters().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllTransporters().get(i).getVehicleId() + " / " + JsonConnection.getListAllTransporters().get(i).getBrand() + " / " + JsonConnection.getListAllTransporters().get(i).getModel() + " / " + JsonConnection.getListAllTransporters().get(i).isAvailable());
        }
        do {
            System.out.println("Bitte geben Sie die Id des Fahrzeuges ein");
            if (scan.hasNextInt()) {
                vehicleId = scan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                scan.next();
            }
        }while(fA == false);

        do{
            System.out.println("Verfügbar [true/false]");
            if(scan.hasNextBoolean()) {
                available = scan.nextBoolean();
                fA = true;
            }else{
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                scan.nextLine();
            }
        }while (fA == false);

        try {
            JsonConnection.changeAvailable(vehicleId, available);
        }catch(IOException e) {

        }
        if (scan.hasNextLine()){
            Main.mainWindow();
        }
    }
}
