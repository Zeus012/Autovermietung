package com.company.Autovermietung.Model;

public class Transporter extends Vehicle {

    private int loadingWeight;

    public Transporter() {
    }

    public Transporter(String brand, String model, double cubicCapacity, String fuelType, String outerColer, double currentKmValue, String licensePlate, String category, String availableFrom, String availableTill, boolean available, int loadingWeight) {
        super(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available);
        this.loadingWeight = loadingWeight;
    }

    public int getLoadingWeight() {
        return loadingWeight;
    }

    public void setLoadingWeight(int loadingWeight) {
        this.loadingWeight = loadingWeight;
    }
}
