package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.Customer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class editCustomerController {

    public static Customer customer;
    static int customerId;
    static String lastname;
    static String firstname;
    static String streetNum;
    static int postCode;
    static String place;
    static String phone;
    static String emailaddress;
    static String  birthday;

    private static String testString;

    private static boolean fA = false;

    public static void editCustomers() {
        Scanner custScan = new Scanner(System.in);
        System.out.println("Kunden");
        System.out.println("\tID / Nachname / Vorname / Wohnort");
        System.out.println("\t___________________________________");
        for (int i = 0; i < JsonConnection.getListAllCustomers().size(); i++) {
            System.out.println("\t" + JsonConnection.getListAllCustomers().get(i).getCustomerId() + " / " +JsonConnection.getListAllCustomers().get(i).getLastname() + " / " + JsonConnection.getListAllCustomers().get(i).getFirstname() + " / " + JsonConnection.getListAllCustomers().get(i).getPlace());
        }

        do {
            System.out.println("Bitte geben Sie die Id des Kunden ein, welche zu ändern ist.");
            if (custScan.hasNextInt()) {
                customerId = custScan.nextInt();
                fA = true;
            }else {
                System.out.println("Die Eingabe war leider ungültig.");
                fA = false;
                custScan.next();
            }
        }while(fA == false);

        try {
            JsonConnection.showCustomer(customerId);
        }catch(IOException e) {

        }

        Scanner scan = new Scanner(System.in);

        System.out.println("Wollen Sie den Nachnamen ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String lastnameChoice = scan.nextLine();
        switch (lastnameChoice) {
            case "j":
                lastname = "";
                while(lastname.equals("")){
                    System.out.println("Alter Nachname:  " + customer.getLastname());
                    System.out.print("Neuer Nachname:  ");
                    lastname = scan.nextLine();
                }
                break;
            case "n":
                try {
                    lastname = customer.getLastname();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie den Vornamen ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String firstnameChoice = scan.nextLine();
        switch (firstnameChoice) {
            case "j":
                firstname = "";
                while(firstname.equals("")){
                    System.out.println("Alter Vorname:  " + customer.getFirstname());
                    System.out.print("Neuer Vorname:  ");
                    firstname = scan.nextLine();
                }
                break;
            case "n":
                try {
                    firstname = customer.getFirstname();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Strasse und Nummer ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String streetNumChoice = scan.nextLine();
        switch (streetNumChoice) {
            case "j":
                streetNum = "";
                while(streetNum.equals("")){
                    System.out.println("Alte Strasse und Nummer:  " + customer.getStreetNum());
                    System.out.print("Neue Strasse und Nummer:  ");
                    streetNum = scan.nextLine();
                }
                break;
            case "n":
                try {
                    streetNum = customer.getStreetNum();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Postleitzahlt ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String postCodeChoice = scan.nextLine();
        switch (postCodeChoice) {
            case "j":
                do {
                    System.out.println("Alter PLZ:  " + customer.getPostCode());
                    System.out.print("Neuer PLZ:  ");
                    if (scan.hasNextInt()) {
                        postCode = scan.nextInt();
                        fA = true;
                    } else {
                        System.out.println("Die Eingabe war leider ungültig.");
                        scan.next();
                        fA = false;
                    }
                } while (fA == false);
                scan.nextLine();
                break;
            case "n":
                try {
                    postCode = customer.getPostCode();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie den Wohnort ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String placeChoice = scan.nextLine();
        switch (placeChoice) {
            case "j":
                place = "";
                while(place.equals("")){
                    System.out.println("Alter Wohnort:  " + customer.getPlace());
                    System.out.print("Neuer Wohnort:  ");
                    place = scan.nextLine();
                }
                break;
            case "n":
                try {
                    place = customer.getPlace();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die Telefonnummer ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String phoneChoice = scan.nextLine();
        switch (phoneChoice) {
            case "j":
                phoneCheck();
                break;
            case "n":
                try {
                    phone = customer.getPhone();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie die E-Mail ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String mailChoice = scan.nextLine();
        switch (mailChoice) {
            case "j":
                mailCheck();
                break;
            case "n":
                try {
                    emailaddress = customer.geteMail();
                }catch (NullPointerException e){

                }
                break;
        }
        System.out.println("Wollen Sie den Geburtstagsdatum ändern? [j/n]");
        while (!scan.hasNext("[jn]")){
            System.out.println("Das ist keine gültige Option");
            scan.nextLine();
        }
        String dateChoice = scan.nextLine();
        switch (dateChoice) {
            case "j":
                dateForm();
                break;
            case "n":
                try {
                    birthday = customer.getBirthday();
                }catch (NullPointerException e){

                }
                break;
        }

        try{
            customerId = customer.getCustomerId();
        }catch (NullPointerException e){

        }

        Customer customer = new Customer(lastname, firstname, streetNum, postCode, place, phone, emailaddress, birthday);
        try {
            JsonConnection.changeCustomer(customer, customerId);
        }catch(IOException e) {

        }
        Main.mainWindow();
    }

    public static void phoneCheck() {
        Scanner custscan = new Scanner(System.in);
        System.out.println("Alte Telefonnummer:  " + customer.getPhone());
        System.out.print("Geben Sie die neue Telefonnummer ein [+41..]  ");
        do {

            if (custscan.hasNextLong()) {
                phone = Long.toString(custscan.nextLong());
                if (Pattern.matches("\\d{10,15}", phone)) {
                    System.out.println("Format: " + phone.replaceFirst("(\\d{1,2})(\\d{2,2})(\\d{3})(\\d{2,6})", "(+$1) $2-$3-$4"));
                    fA = true;
                } else {
                    System.out.println("Bitte stellen Sie sicher, dass Sie zwichen 10-15 Zahlen sind.");
                    custscan.next();
                    fA = false;
                }
            }else {
                System.out.println("Bitte stellen Sie sicher, dass Sie zwichen 10-15 Zahlen sind.");
                custscan.next();
                fA = false;
            }
        }while (fA == false);

        if (fA == true){
            custscan.nextLine();
        }

    }


    public static void mailCheck(){
        do {
            System.out.println("Alte E-Mail:  " + customer.geteMail());
            System.out.print("Bitte geben Sie die neue E-Mail ein. ex:xyz@gmail.com  ");
            Scanner mailCheck = new Scanner(System.in);
            emailaddress = mailCheck.nextLine();

            String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            testString = emailaddress;
            fA = testString.matches(email_regex);
        } while (!fA);
    }

    public static void dateForm(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("ALter Geburtsdatum:  " + customer.getBirthday());
        System.out.print("Neuer Geburtsdatum [ex: 12-30-1993]  ");
        do {
            birthday = sc.nextLine();

            try {
                Date date = sdf.parse(birthday);

                sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                fA = true;
            } catch (ParseException e) {
                System.out.println("Eingabe war falsch, bitte geben Sie ein gültiges Datum ein");
                fA = false;
            }
        }while(fA == false);
    }
}
