package com.thekthuser.medlog;

public class Emergency {
    private int id;
    private GeneralInfo general;
    private String relation;

    public Emergency(int id, String relation, GeneralInfo general) {
        this.id = id;
        this.relation = relation;
        this.general = general;
    }

    public Emergency(String relation, GeneralInfo general) {
        this.id = -1;
        this.relation = relation;
        this.general = general;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public GeneralInfo getGeneralInfo() {
        return general;
    }

    public void setGeneralInfo(GeneralInfo general) {
        this.general = general;
    }
}
