package com.company.Autovermietung.Model;

public class Bike extends Vehicle {

    private double tankVolume;

    public Bike() {
    }

    public Bike(String brand, String model, double cubicCapacity, String fuelType, String outerColer, double currentKmValue, String licensePlate, String category, String availableFrom, String availableTill, boolean available, double tankVolume) {
        super(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available);
        this.tankVolume = tankVolume;
    }

    public double getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(double tankVolume) {
        this.tankVolume = tankVolume;
    }
}
