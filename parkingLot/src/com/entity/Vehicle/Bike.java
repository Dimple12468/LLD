package com.entity.Vehicle;

import com.util.VehicleType;

public class Bike extends Vehicle{

    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleType.BIKE);
    }

}
