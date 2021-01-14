package com.company.Autovermietung.Model;

import com.company.Autovermietung.DB.JsonConnection;

public class Customer {

    private int customerId;
    private String lastname;
    private String firstname;
    private String streetNum;
    private int postCode;
    private String place;
    private String phone;
    private String emailaddress;
    private String  birthday;

    private static int counter = JsonConnection.getAllCustomersListSize();

    public Customer() {
    }

    public Customer(String lastname, String firstname, String streetNum, int postCode, String place, String phone, String emailaddress, String  birthday) {
        this.customerId = counter++;
        this.lastname = lastname;
        this.firstname = firstname;
        this.streetNum = streetNum;
        this.postCode = postCode;
        this.place = place;
        this.phone = phone;
        this.emailaddress = emailaddress;
        this.birthday = birthday;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return emailaddress;
    }

    public void seteMail(String eMail) {
        this.emailaddress = eMail;
    }

    public String  getBirthday() {
        return birthday;
    }

    public void setBirthday(String  birthday) {
        this.birthday = birthday;
    }

    public static void setCounter(int counter) {
        Customer.counter = counter;
    }
}
