package com.thekthuser.medlog;

public class Prescription {
    private int id;
    private int medication_id;
    private String pill_dosage;
    private String dosage_taken;

    public Prescription(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
