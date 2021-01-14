package com.company.Autovermietung.Model;

import com.company.Autovermietung.DB.JsonConnection;

public class Vehicle {
    protected int vehicleId;
    protected String brand;
    protected String model;
    protected double cubicCapacity;
    protected String fuelType;
    protected String outerColor;
    protected double currentKmValue;
    protected String licensePlate;
    protected String category;
    protected String availableFrom;
    protected String availableTill;
    protected boolean available;

    private static int counter = JsonConnection.getAllVehicleListSize();

    public Vehicle() {
    }

    public Vehicle(String brand, String model, double cubicCapacity, String fuelType, String outerColor, double currentKmValue, String licensePlate, String category, String availableFrom, String availableTill, boolean available) {
        this.vehicleId = counter++;
        this.brand = brand;
        this.model = model;
        this.cubicCapacity = cubicCapacity;
        this.fuelType = fuelType;
        this.outerColor = outerColor;
        this.currentKmValue = currentKmValue;
        this.licensePlate = licensePlate;
        this.category = category;
        this.availableFrom = availableFrom;
        this.availableTill = availableTill;
        this.available = available;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCubicCapacity() {
        return cubicCapacity;
    }

    public void setCubicCapacity(double cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getOuterColor() {
        return outerColor;
    }

    public void setOuterColor(String outerColor) {
        this.outerColor = outerColor;
    }

    public double getCurrentKmValue() {
        return currentKmValue;
    }

    public void setCurrentKmValue(double currentKmValue) {
        this.currentKmValue = currentKmValue;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String avaiableFrom) {
        this.availableFrom = avaiableFrom;
    }

    public String getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(String avaiableTill) {
        this.availableTill = avaiableTill;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean avaiable) {
        this.available = avaiable;
    }

    public static void setCounter(int counter) {
        Vehicle.counter = counter;
    }
}
