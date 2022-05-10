package com.example.cocototo;

public class User {
    String shopname, telephone, address;

    public User(){}

    public User(String shopname, String telephone, String address) {
        this.shopname = shopname;
        this.telephone = telephone;
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
