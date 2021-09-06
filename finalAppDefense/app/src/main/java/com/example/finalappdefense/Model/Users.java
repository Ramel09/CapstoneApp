package com.example.finalappdefense.Model;

public class Users {

    private String fullname, phone, password, image, address;

    public Users() {

    }

    public Users(String fullname, String phone, String password, String image, String address) {
        this.fullname = fullname;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}