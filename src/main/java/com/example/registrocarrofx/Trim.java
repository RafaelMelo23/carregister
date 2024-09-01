package com.example.registrocarrofx;

public class Trim {

    private String modelTransmission;
    private String modelColor;
    private String modelOptionals;
    private String modelHp;
    private String selectedEngineFuel;
    private String selectedEngineLitre;
    private String selectedPowertrain;
    private String selectedEngineCylinders;
    private String engineAspiration;
    private String trimType;

    public Trim(String modelTransmission, String modelColor, String modelOptionals, String modelHp, String selectedEngineFuel, String selectedEngineLitre, String selectedPowertrain, String trimType) {
        this.modelTransmission = modelTransmission;
        this.modelColor = modelColor;
        this.modelOptionals = modelOptionals;
        this.modelHp = modelHp;
        this.selectedEngineFuel = selectedEngineFuel;
        this.selectedEngineLitre = selectedEngineLitre;
        this.selectedPowertrain = selectedPowertrain;
        this.trimType = trimType;
    }

    public Trim() {

    }

    public String getEngineAspiration() {
        return engineAspiration;
    }

    public void setEngineAspiration(String engineAspiration) {
        this.engineAspiration = engineAspiration;
    }

    public String getModelTransmission() {
        return modelTransmission;
    }

    public void setModelTransmission(String modelTransmission) {
        this.modelTransmission = modelTransmission;
    }

    public String getModelColor() {
        return modelColor;
    }

    public void setModelColor(String modelColor) {
        this.modelColor = modelColor;
    }

    public String getModelOptionals() {
        return modelOptionals;
    }

    public void setModelOptionals(String modelOptionals) {
        this.modelOptionals = modelOptionals;
    }

    public String getModelHp() {
        return modelHp;
    }

    public void setModelHp(String modelHp) {
        this.modelHp = modelHp;
    }

    public String getSelectedEngineFuel() {
        return selectedEngineFuel;
    }

    public void setSelectedEngineFuel(String selectedEngineFuel) {
        this.selectedEngineFuel = selectedEngineFuel;
    }

    public String getSelectedEngineLitre() {
        return selectedEngineLitre;
    }

    public void setSelectedEngineLitre(String selectedEngineLitre) {
        this.selectedEngineLitre = selectedEngineLitre;
    }

    public String getSelectedPowertrain() {
        return selectedPowertrain;
    }

    public void setSelectedPowertrain(String selectedPowertrain) {
        this.selectedPowertrain = selectedPowertrain;
    }

    public String getTrimType() {
        return trimType;
    }

    public void setTrimType(String trimType) {
        this.trimType = trimType;
    }

    public String getSelectedEngineCylinders() {
        return selectedEngineCylinders;
    }

    public void setSelectedEngineCylinders(String selectedEngineCylinders) {
        this.selectedEngineCylinders = selectedEngineCylinders;
    }
}
