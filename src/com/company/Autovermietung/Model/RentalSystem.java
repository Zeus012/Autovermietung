package com.company.Autovermietung.Model;

import com.company.Autovermietung.DB.JsonConnection;

public class RentalSystem {

    private int rentalId;
    private int customerId;
    private int vehicleId;
    private double rentsDuration;
    private double price;
    private String status;

    private static int counter = JsonConnection.getAllVehicleListSize();

    public RentalSystem() {
    }

    public RentalSystem(int customerId, int vehicleId, double rentsDuration, double price, String status) {
        this.rentalId = counter++;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentsDuration = rentsDuration;
        this.price = price;
        this.status = status;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getRentsDuraction() {
        return rentsDuration;
    }

    public void setRentsDuraction(double rentsDuraction) {
        this.rentsDuration = rentsDuraction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String state) {
        this.status = state;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        RentalSystem.counter = counter;
    }
}
