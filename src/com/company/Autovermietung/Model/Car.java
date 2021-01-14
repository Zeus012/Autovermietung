package com.company.Autovermietung.Model;

public class Car extends Vehicle {

    private int trunkSpace;
    private String construction;
    private boolean navigationSystem;

    public Car() {
    }

    public Car(String brand, String model, double cubicCapacity, String fuelType, String outerColer, double currentKmValue, String licensePlate, String category, String availableFrom, String availableTill, boolean available, int trunkSpace, String construction, boolean navigationSystem) {
        super(brand, model, cubicCapacity, fuelType, outerColer, currentKmValue, licensePlate, category, availableFrom, availableTill, available);
        this.trunkSpace = trunkSpace;
        this.construction = construction;
        this.navigationSystem = navigationSystem;
    }

    public int getTrunkSpace() {
        return trunkSpace;
    }

    public void setTrunkSpace(int trunkSpace) {
        this.trunkSpace = trunkSpace;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public boolean isNavigationSystem() {
        return navigationSystem;
    }

    public void setNavigationSystem(boolean navigationSystem) {
        this.navigationSystem = navigationSystem;
    }
}
