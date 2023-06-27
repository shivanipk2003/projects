package com.example.productcomparison_online;

public class Accountjava {

    String encrypt_Accno,encrypt_Ifsc,payname,paybranch,paynumber,encrypt_password;

    public Accountjava() {
    }

    public Accountjava(String encrypt_Accno, String encrypt_Ifsc, String payname, String paybranch, String paynumber, String encrypt_password) {
        this.encrypt_Accno = encrypt_Accno;
        this.encrypt_Ifsc = encrypt_Ifsc;
        this.payname = payname;
        this.paybranch = paybranch;
        this.paynumber = paynumber;
        this.encrypt_password = encrypt_password;
    }


    public String getEncrypt_Accno() {
        return encrypt_Accno;
    }

    public void setEncrypt_Accno(String encrypt_Accno) {
        this.encrypt_Accno = encrypt_Accno;
    }

    public String getEncrypt_Ifsc() {
        return encrypt_Ifsc;
    }

    public void setEncrypt_Ifsc(String encrypt_Ifsc) {
        this.encrypt_Ifsc = encrypt_Ifsc;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getPaybranch() {
        return paybranch;
    }

    public void setPaybranch(String paybranch) {
        this.paybranch = paybranch;
    }

    public String getPaynumber() {
        return paynumber;
    }

    public void setPaynumber(String paynumber) {
        this.paynumber = paynumber;
    }

    public String getEncrypt_password() {
        return encrypt_password;
    }

    public void setEncrypt_password(String encrypt_password) {
        this.encrypt_password = encrypt_password;
    }
}
