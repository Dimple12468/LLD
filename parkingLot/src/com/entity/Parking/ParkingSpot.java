package com.entity.Parking;

import com.entity.Vehicle.Vehicle;
import com.util.ParkingSpotType;

public abstract class ParkingSpot {

    private String number;
    private boolean free;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public ParkingSpot(String number, ParkingSpotType type) {
        this.number = number;
        this.free = true;
        this.type = type;
    }

    public String getNumber() { return number; }
    
    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) { this.free = free; }
    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public ParkingSpotType getType() { return type; }

}
