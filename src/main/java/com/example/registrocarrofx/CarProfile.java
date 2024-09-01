package com.example.registrocarrofx;


import java.util.ArrayList;
import java.util.List;

public class CarProfile {

    private String modelName;
    private String modelClass;
    private String modelYear;
    private int id;
    private List<Trim> trims;

    public CarProfile(int id, String modelName, String modelClass, String modelYear) {
        this.id = id;
        this.modelName = modelName;
        this.modelClass = modelClass;
        this.modelYear = modelYear;
        this.trims = new ArrayList<>();

    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelClass() {
        return modelClass;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setId(int anInt) {
        this.id = anInt;
    }

    public int getId() {
        return this.id;
    }

    public void addTrim(Trim trim) {
        if (this.trims == null) {
            this.trims = new ArrayList<>();
        }
        this.trims.add(trim);
    }

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }
}
