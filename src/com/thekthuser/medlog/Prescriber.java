package com.thekthuser.medlog;

public class Prescriber {
    private int id;
    private GeneralInfo general;
    private String specialty;

    public Prescriber(int id, String specialty, GeneralInfo general) {
        this.id = id;
        this.specialty = specialty;
        this.general = general;
    }

    public Prescriber(String specialty, GeneralInfo general) {
        this.id = -1;
        this.specialty = specialty;
        this.general = general;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public GeneralInfo getGeneralInfo() {
        return general;
    }

    public void setGeneralInfo(GeneralInfo general) {
        this.general = general;
    }


}
