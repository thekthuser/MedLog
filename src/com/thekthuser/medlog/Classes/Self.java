package com.thekthuser.medlog;

public class Self {
    private int id;
    private GeneralInfo general;

    public Self(int id, GeneralInfo general) {
        this.id = id;
        this.general = general;
    }

    public Self(GeneralInfo general) {
        this.id = -1;
        this.general = general;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GeneralInfo getGeneralInfo() {
        return general;
    }

    public void setGeneralInfo(GeneralInfo general) {
        this.general = general;
    }
}
