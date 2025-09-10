package com.javatechie.dto;

import lombok.Data;
import lombok.ToString;


public class Customer{

    private String custId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public String getCustId(){
        return custId;
    }

    public void setCustId(String custId){
        this.custId = custId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
