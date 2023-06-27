package com.example.productcomparison_online;

public class Final_paymentjava {


    String name,phone_number,door_no,street,city,pincode,title,price,app_user_number,purchase_date;

    public Final_paymentjava() {
    }

    public Final_paymentjava(String name, String phone_number, String door_no, String street, String city, String pincode, String title, String price, String app_user_number, String purchase_date) {
        this.name = name;
        this.phone_number = phone_number;
        this.door_no = door_no;
        this.street = street;
        this.city = city;
        this.pincode = pincode;
        this.title = title;
        this.price = price;
        this.app_user_number=app_user_number;
        this.purchase_date=purchase_date;
    }


    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDoor_no() {
        return door_no;
    }

    public void setDoor_no(String door_no) {
        this.door_no = door_no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getApp_user_number() {
        return app_user_number;
    }

    public void setApp_user_number(String app_user_number) {
        this.app_user_number = app_user_number;
    }
}
