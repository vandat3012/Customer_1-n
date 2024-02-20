package com.example.practice2.model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private Province oProvince;

    public Customer() {
    }

    public Customer(String name, String email, String address, Province oProvince) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.oProvince = oProvince;
    }

    public Customer(int id, String name, String email, String address, Province oProvince) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.oProvince = oProvince;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Province getoProvince() {
        return oProvince;
    }

    public void setoProvince(Province oProvince) {
        this.oProvince = oProvince;
    }
}
