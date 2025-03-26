package com.entity.Vehicle;

import com.util.VehicleType;

public abstract class Vehicle {

    private String licenseNumber;
    private final VehicleType type;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.type = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getType() {
        return type;
    }
    
}
