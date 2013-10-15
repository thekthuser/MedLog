package com.thekthuser.medlog;

public class Pharmacy {
    private int id;
    private String hours;
    private GeneralInfo general;

    public Pharmacy(int id, String hours, GeneralInfo general) {
        this.id = id;
        this.hours = hours;
        this.general = general;
    }

    public Pharmacy(String hours, GeneralInfo general) {
        this.id = -1;
        this.hours = hours;
        this.general = general;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public GeneralInfo getGeneralInfo() {
        return general;
    }

    public void setGeneralInfo(GeneralInfo general) {
        this.general = general;
    }
}
