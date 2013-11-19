package com.thekthuser.medlog;

import java.util.ArrayList;
import java.util.List;

public class Medication {
    private int id;
    private String scientific_name;
    private String brand_name;

    public Medication(int id, String scientific_name, String brand_name) {
        this.id = id;
        this.scientific_name = scientific_name;
        this.brand_name = brand_name;
    }

    public Medication(String scientific_name, String brand_name) {
        this.id = -1;
        this.scientific_name = scientific_name;
        this.brand_name = brand_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScientificName() {
        return scientific_name;
    }

    public void setScientificName(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getBrandName() {
        return brand_name;
    }

    public void setBrandName(String brand_name) {
        this.brand_name = brand_name;
    }
}
