package com.example.finapp.model;

import java.util.Date;

public class Extrato implements Comparable<Extrato> {

    private String type;
    private String date;
    private double value;
    private Date dateDate;

    public Extrato(){}


    public Extrato(String type, String date, double value) {
        this.type = type;
        this.date = date;
        this.value = value;
    }

    public Extrato(String type, String date, Date dateDate, double value) {
        this.type = type;
        this.date = date;
        this.value = value;
        this.dateDate = dateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    @Override
    public int compareTo(Extrato extrato) {
        return getDateDate().compareTo(extrato.getDateDate());
    }
}
