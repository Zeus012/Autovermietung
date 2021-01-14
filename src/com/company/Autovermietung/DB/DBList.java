package com.company.Autovermietung.DB;

import com.company.Autovermietung.Model.*;

import java.util.List;

public class DBList {
    private List<Customer> allCustomersList;
    private List<RentalSystem> allRentingsList;
    private List<Car> allCarsList;
    private List<Bike> allBikesList;
    private List<Transporter> allTransportersList;

    public DBList() {
    }

    public DBList(List<Customer> allCustomersList, List<RentalSystem> allRentingsList, List<Car> allCarsList, List<Bike> allBikesList, List<Transporter> allTransportersList) {
        this.allCustomersList = allCustomersList;
        this.allRentingsList = allRentingsList;
        this.allCarsList = allCarsList;
        this.allBikesList = allBikesList;
        this.allTransportersList = allTransportersList;
    }

    public List<Customer> getAllCustomersList() {
        return allCustomersList;
    }

    public void setAllCustomersList(List<Customer> allCustomersList) {
        this.allCustomersList = allCustomersList;
    }

    public List<RentalSystem> getAllRentingsList() {
        return allRentingsList;
    }

    public void setAllRentingsList(List<RentalSystem> allRentingsList) {
        this.allRentingsList = allRentingsList;
    }

    public List<Car> getAllCarsList() {
        return allCarsList;
    }

    public void setAllCarsList(List<Car> allCarsList) {
        this.allCarsList = allCarsList;
    }

    public List<Bike> getAllBikesList() {
        return allBikesList;
    }

    public void setAllBikesList(List<Bike> allBikesList) {
        this.allBikesList = allBikesList;
    }

    public List<Transporter> getAllTransportersList() {
        return allTransportersList;
    }

    public void setAllTransportersList(List<Transporter> allTransportersList) {
        this.allTransportersList = allTransportersList;
    }
}
