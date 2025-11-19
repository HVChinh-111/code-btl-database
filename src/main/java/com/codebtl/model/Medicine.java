package com.codebtl.model;

public class Medicine {
    private String medicineId;
    private String name;
    private String strength;
    private String unit;

    public Medicine() {
    }

    public Medicine(String medicineId, String name, String strength, String unit) {
        this.medicineId = medicineId;
        this.name = name;
        this.strength = strength;
        this.unit = unit;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

