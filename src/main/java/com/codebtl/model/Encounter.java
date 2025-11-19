package com.codebtl.model;

import java.sql.Timestamp;

public class Encounter {
    private String encounterId;
    private String appId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String diagnosis;
    private String symptom;
    private String notes;
    private String status;
    private String dPerson;

    public Encounter() {
    }

    public Encounter(String encounterId, String appId, Timestamp startTime, Timestamp endTime, 
                     String diagnosis, String symptom, String notes, String status, String dPerson) {
        this.encounterId = encounterId;
        this.appId = appId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.diagnosis = diagnosis;
        this.symptom = symptom;
        this.notes = notes;
        this.status = status;
        this.dPerson = dPerson;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeString() {
        return startTime != null ? startTime.toString() : "";
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeString() {
        return endTime != null ? endTime.toString() : "";
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDPerson() {
        return dPerson;
    }

    public void setDPerson(String dPerson) {
        this.dPerson = dPerson;
    }
}


