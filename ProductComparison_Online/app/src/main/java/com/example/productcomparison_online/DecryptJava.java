package com.example.productcomparison_online;

public class DecryptJava {

    String payaccno,payifsc,paypass;

    public DecryptJava(String payaccno, String payifsc, String paypass) {
        this.payaccno = payaccno;
        this.payifsc = payifsc;
        this.paypass = paypass;
    }

    public DecryptJava() {

    }

    public String getPayaccno() {
        return payaccno;
    }

    public void setPayaccno(String payaccno) {
        this.payaccno = payaccno;
    }

    public String getPayifsc() {
        return payifsc;
    }

    public void setPayifsc(String payifsc) {
        this.payifsc = payifsc;
    }

    public String getPaypass() {
        return paypass;
    }

    public void setPaypass(String paypass) {
        this.paypass = paypass;
    }
}
