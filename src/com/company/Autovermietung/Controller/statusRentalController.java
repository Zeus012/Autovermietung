package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;

import java.io.IOException;
import java.util.Scanner;

public class statusRentalController {

    static int rentalId;
    static String status;
    private static boolean fA = false;

    public static void changeStatusRental(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Vermietungen");
        System.out.println("\tVermietungs ID / Fahrzeug ID / Kunden ID / Status");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllRentings().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllRentings().get(i).getRentalId() + " / " + JsonConnection.getListAllRentings().get(i).getVehicleId() + " / " + JsonConnection.getListAllRentings().get(i).getCustomerId() + " / " + JsonConnection.getListAllRentings().get(i).getStatus());
        }

        do {
            System.out.println("Bitte geben Sie die Id der Vermietung ein");
            if (scan.hasNextInt()) {
                rentalId = scan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                scan.next();
            }
        }while(fA == false);

        System.out.println("Status [offen, bezahlt, abgeschlossen]");
        status = scan.next();


        try {
            JsonConnection.changeStatus(rentalId, status);
        }catch(IOException e) {

        }

        if (scan.hasNextLine()){
            Main.mainWindow();
        }
    }
}
