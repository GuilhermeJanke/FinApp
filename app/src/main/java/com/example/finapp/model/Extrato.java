package com.example.finapp.model;

public class Extrato {

    private String type;
    private String date;
    private double value;

    public Extrato(){}


    public Extrato(String type, String date, double value) {
        this.type = type;
        this.date = date;
        this.value = value;
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
}
