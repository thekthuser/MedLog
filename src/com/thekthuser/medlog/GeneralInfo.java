package com.thekthuser.medlog;

public class GeneralInfo {
    private long id;
    private String name;
    private String address;
    private String phone;

    public GeneralInfo(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public GeneralInfo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone() {
        this.phone = phone;
    }
}
