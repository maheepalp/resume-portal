package com.mywon.resumeportal.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String company;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection(targetClass=String.class)
    private List<String> responsibilities = new ArrayList<>();
    


    public List<String> getResponsibilities() {
        return this.responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getFormatedStartDate(){
        return startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    public String getFormatedEndDate(){
        return endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", company='" + getCompany() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", responsibilities='" + getResponsibilities() + "'" +
            "}";
    }

}
