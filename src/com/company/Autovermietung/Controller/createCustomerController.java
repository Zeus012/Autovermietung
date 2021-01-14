package com.company.Autovermietung.Controller;

import com.company.Autovermietung.DB.JsonConnection;
import com.company.Autovermietung.Model.Customer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class createCustomerController {
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

    public static void createCustomer(){
        Scanner custscan = new Scanner(System.in);

        lastname = "";
        while(lastname.equals("")){
            System.out.println("Nachname");
            lastname = custscan.nextLine();
        }

        firstname = "";
        while(firstname.equals("")){
            System.out.println("Vorname");
            firstname = custscan.nextLine();
        }

        streetNum = "";
        while(streetNum.equals("")){
            System.out.println("Strasse und Nummer");
            streetNum = custscan.nextLine();
        }

        do {
            System.out.println("PLZ");
            if (custscan.hasNextInt()) {
                postCode = custscan.nextInt();
                fA = true;
            } else {
                System.out.println("Die Eingabe war leider ungültig.");
                custscan.next();
                fA = false;
            }
        } while (fA == false);

        if (fA == true) {
            custscan.nextLine();

            place = "";
            while(place.equals("")){
                System.out.println("Wohnort");
                place = custscan.nextLine();
            }

            phoneCheck();

            mailCheck();

            dateForm();

            Customer customer = new Customer(lastname, firstname, streetNum, postCode, place, phone, emailaddress, birthday);
            try {
                JsonConnection.addCustomer(customer);
            }catch(IOException e) {

            }
            Main.mainWindow();
        }
    }

    public static void phoneCheck() {
        Scanner custscan = new Scanner(System.in);
        System.out.println("Geben Sie ihre Telefonnummer ein [+41..]");
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
            System.out.println("Bitte geben Sie ihre E-Mail ein. ex:xyz@gmail.com");
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

        System.out.println("Geburtsdatum [ex: 12-30-1993]");
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
