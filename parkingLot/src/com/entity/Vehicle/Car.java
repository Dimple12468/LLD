package com.entity.Vehicle;

import com.util.VehicleType;

public class Car extends Vehicle {

    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}
