package com.pathfinder.entity;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;

    private String ipAddress;

    public Customer() {
    }
    public Customer(String name, String email, String phoneNumber, String ipAddress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.ipAddress = ipAddress;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
