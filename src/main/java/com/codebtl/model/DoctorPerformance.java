package com.codebtl.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DoctorPerformance {
    private String doctorId;
    private String doctorName;
    private LocalDate dateOfBirth;
    private int numberOfPatients;
    private int numberOfCompletedExaminations;
    private BigDecimal totalRevenue;

    public DoctorPerformance() {
    }

    public DoctorPerformance(String doctorId, String doctorName, LocalDate dateOfBirth, 
                            int numberOfPatients, int numberOfCompletedExaminations, 
                            BigDecimal totalRevenue) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.dateOfBirth = dateOfBirth;
        this.numberOfPatients = numberOfPatients;
        this.numberOfCompletedExaminations = numberOfCompletedExaminations;
        this.totalRevenue = totalRevenue;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public int getNumberOfCompletedExaminations() {
        return numberOfCompletedExaminations;
    }

    public void setNumberOfCompletedExaminations(int numberOfCompletedExaminations) {
        this.numberOfCompletedExaminations = numberOfCompletedExaminations;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
