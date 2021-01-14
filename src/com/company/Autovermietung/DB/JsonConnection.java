package com.company.Autovermietung.DB;

import com.company.Autovermietung.Controller.editCustomerController;
import com.company.Autovermietung.Controller.editRentalController;
import com.company.Autovermietung.Controller.editVehicleController;
import com.company.Autovermietung.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonConnection {

    private static String JSONString = "";
    private static File JSONFile = new File("JSONFile.json");

    private static List<Customer> listAllCustomers = new ArrayList<>();
    private static List<RentalSystem> listAllRentings = new ArrayList<>();
    private static List<Car> listAllCars = new ArrayList<>();
    private static List<Bike> listAllBikes = new ArrayList<>();
    private static List<Transporter> listAllTransporters = new ArrayList<>();
    private static List<Vehicle> listAllVehicles = new ArrayList<>();


    public static void readJson() throws IOException {
        JSONFile.createNewFile();
        Scanner fileScan = new Scanner(JSONFile);
        while (fileScan.hasNextLine()){
            JSONString += (fileScan.nextLine());
        }
        if (!JSONString.isEmpty()){
            ObjectMapper objectMapper = new ObjectMapper();
            DBList DBList = objectMapper.readValue(JSONString, DBList.class);
            listAllCars = DBList.getAllCarsList();
            listAllBikes = DBList.getAllBikesList();
            listAllCustomers = DBList.getAllCustomersList();
            listAllRentings = DBList.getAllRentingsList();
            listAllTransporters = DBList.getAllTransportersList();
            listAllVehicles.addAll(listAllBikes);
            listAllVehicles.addAll(listAllCars);
            listAllVehicles.addAll(listAllTransporters);
        }
    }

    public static void updateFile() throws IOException {
        DBList DBList = new DBList(listAllCustomers, listAllRentings, listAllCars, listAllBikes, listAllTransporters);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(JSONFile, DBList);
    }

    public static void addCar(Car car) throws IOException {
        listAllCars.add(car);
        updateFile();
    }

    public static void addBike(Bike bike) throws IOException {
        listAllBikes.add(bike);
        updateFile();
    }

    public static void addTransporter(Transporter transporter) throws IOException {
        listAllTransporters.add(transporter);
        updateFile();
    }

    public static void addRental(RentalSystem rentalSystem) throws IOException {
        listAllRentings.add(rentalSystem);
        updateFile();
    }

    public static void addCustomer(Customer customer) throws IOException {
        listAllCustomers.add(customer);
        updateFile();
    }

    public static void changeAvailable(int vehicleId, boolean available) throws IOException{
        for (int i = 0; i < getListAllVehicles().size(); i++) {
            if (getListAllVehicles().get(i).getVehicleId() == vehicleId) {
                listAllVehicles.get(i).setAvailable(available);
                updateFile();
            }
        }
    }

    public static void changeStatus(int rentalId, String status) throws IOException{
        for (int i = 0; i < getListAllRentings().size(); i++) {
            if (getListAllRentings().get(i).getRentalId() == rentalId) {
                listAllRentings.get(i).setStatus(status);
                updateFile();
            }
        }
    }

    public static void changeCar(Car car, int vehicleId) throws IOException{
        listAllCars.remove(editVehicleController.car);
        car.setVehicleId(vehicleId);
        listAllCars.add(car);
        updateFile();
    }

    public static void changeBike(Bike bike, int vehicleId) throws IOException{
        listAllBikes.remove(editVehicleController.bike);
        bike.setVehicleId(vehicleId);
        listAllBikes.add(bike);
        updateFile();
    }

    public static void changeTransporter(Transporter transporter, int vehicleId) throws IOException{
        listAllTransporters.remove(editVehicleController.transporter);
        transporter.setVehicleId(vehicleId);
        listAllTransporters.add(transporter);
        updateFile();
    }

    public static void changeRental(RentalSystem rentalSystem, int rentalId) throws IOException{
        listAllRentings.remove(editRentalController.rentalSystem);
        rentalSystem.setRentalId(rentalId);
        listAllRentings.add(rentalSystem);
        updateFile();
    }

    public static void changeCustomer(Customer customer, int customerId) throws IOException{
        listAllCustomers.remove(editCustomerController.customer);
        customer.setCustomerId(customerId);
        listAllCustomers.add(customer);
        updateFile();
    }

    public static void showVehicle(int vehicleId) throws IOException{
        for (int i = 0; i < getListAllVehicles().size(); i++) {
            if (getListAllVehicles().get(i).getVehicleId() == vehicleId) {
                editVehicleController.vehicle = getListAllVehicles().get(i);
            }
        }
    }

    public static void showCar(int vehicleId) throws IOException{
        for (int i = 0; i < getListAllCars().size(); i++) {
            if (getListAllCars().get(i).getVehicleId() == vehicleId) {
                editVehicleController.car = getListAllCars().get(i);
            }
        }
    }

    public static void showBike(int vehicleId) throws IOException{
        for (int i = 0; i < getListAllBikes().size(); i++) {
            if (getListAllBikes().get(i).getVehicleId() == vehicleId) {
                editVehicleController.bike = getListAllBikes().get(i);
            }
        }
    }

    public static void showRental(int rentalId) throws IOException{
        for (int i = 0; i < getListAllRentings().size(); i++) {
            if (getListAllRentings().get(i).getRentalId() == rentalId) {
                editRentalController.rentalSystem = getListAllRentings().get(i);
            }
        }
    }

    public static void showCustomer(int customerId) throws IOException{
        for (int i = 0; i < getListAllCustomers().size(); i++) {
            if (getListAllCustomers().get(i).getCustomerId() == customerId) {
                editCustomerController.customer = getListAllCustomers().get(i);
            }
        }
    }

    public static void showTransporter(int vehicleId) throws IOException{
        for (int i = 0; i < getListAllTransporters().size(); i++) {
            if (getListAllTransporters().get(i).getVehicleId() == vehicleId) {
                editVehicleController.transporter = getListAllTransporters().get(i);
            }
        }
    }

    public static List<Customer> getListAllCustomers() {
        return listAllCustomers;
    }

    public static List<RentalSystem> getListAllRentings() {
        return listAllRentings;
    }

    public static List<Car> getListAllCars() {
        return listAllCars;
    }

    public static List<Bike> getListAllBikes() {
        return listAllBikes;
    }

    public static List<Transporter> getListAllTransporters() {
        return listAllTransporters;
    }

    public static List<Vehicle> getListAllVehicles() {
        listAllVehicles.clear();
        listAllVehicles.addAll(listAllCars);
        listAllVehicles.addAll(listAllBikes);
        listAllVehicles.addAll(listAllTransporters);
        return listAllVehicles;
    }

    public static int getAllVehicleListSize(){
        int size = listAllCars.size() + listAllBikes.size() + listAllTransporters.size();
        return size;
    }

    public static int getAllCustomersListSize(){
        int size = listAllCustomers.size();
        return size;
    }

    public static int getListAllRentingsSize(){
        int size = listAllRentings.size();
        return  size;
    }
}
