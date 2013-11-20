package com.thekthuser.medlog;

public class Prescription {
    private int id;
    private int medication_id;
    private String pill_dosage;
    private String dosage_taken;

    public Prescription(int id, String pill_dosage, String dosage_taken) {
        this.id = id;
        this.pill_dosage = pill_dosage;
        this.dosage_taken = dosage_taken;
    }

    public Prescription(String pill_dosage, String dosage_taken) {
        this.id = -1;
        this.pill_dosage = pill_dosage;
        this.dosage_taken = dosage_taken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPillDosage() {
        return pill_dosage;
    }

    public void setPillDosage(String pill_dosage) {
        this.pill_dosage = pill_dosage;
    }

    public String getDosageTaken() {
        return dosage_taken;
    }

    public void setDosageTaken(String dosage_taken) {
        this.dosage_taken = dosage_taken;
    }
}
