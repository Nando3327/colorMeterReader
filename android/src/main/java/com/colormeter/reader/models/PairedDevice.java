package com.colormeter.reader.models;

public class PairedDevice {

    private int id;
    private String name;
    private double batteryLevel;
    private String batteryLevelString;
    private String status;

    private boolean whiteCalibration;
    private boolean blackCalibration;


    public PairedDevice(int id, String name, double batteryLevel, String batteryLevelString, String status, boolean whiteCalibration, boolean blackCalibration) {
        this.id = id;
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.batteryLevelString = batteryLevelString;
        this.status = status;
        this.whiteCalibration = whiteCalibration;
        this.blackCalibration = blackCalibration;
    }
}