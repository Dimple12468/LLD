package com.service;

import java.util.ArrayList;
import java.util.List;

import com.entity.Parking.BikeParkingSpot;
import com.entity.Parking.CarParkingSpot;
import com.entity.Parking.ParkingSpot;
import com.entity.Vehicle.Vehicle;
import com.util.ParkingSpotType;

public class ParkingLotService {

    private List<ParkingSpot> parkingSpots;
    
    public ParkingLotService() {
        this.parkingSpots = new ArrayList<>();
        initializeParkingSpots();
    }

    private void initializeParkingSpots() {
        parkingSpots.add(new CarParkingSpot("C1", ParkingSpotType.CAR));
        parkingSpots.add(new CarParkingSpot("C2", ParkingSpotType.CAR));
        parkingSpots.add(new BikeParkingSpot("B1", ParkingSpotType.BIKE));
        parkingSpots.add(new BikeParkingSpot("B2", ParkingSpotType.BIKE));
        parkingSpots.add(new CarParkingSpot("H1", ParkingSpotType.HANDICAPPED));
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpotType requiredType = getRequiredSpotType(vehicle);
        for(ParkingSpot spot : parkingSpots){
            if(spot.isFree() && spot.getType() == requiredType){
                spot.setVehicle(vehicle);
                spot.setFree(false);
                System.out.println("Vehicle " + vehicle.getLicenseNumber() + " parked at spot " + spot.getNumber());
                return spot;
            }
        }
        System.out.println("No available spot for vehicle " + vehicle.getLicenseNumber());
        return null;
    }

    public boolean removeVehicle(String licenseNumber) {
        for(ParkingSpot spot : parkingSpots){
            if(!spot.isFree() && spot.getVehicle().getLicenseNumber().equals(licenseNumber)){
                spot.setVehicle(null);
                spot.setFree(true);
                System.out.println("Vehicle " + licenseNumber + " removed from spot " + spot.getNumber());
                return true;
            }
        }
        System.out.println("Vehicle " + licenseNumber + " not found");
        return false;
    }

    public int getAvailableSpotsCount(ParkingSpotType type) {
        int count = 0;
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getType() == type && spot.isFree()) {
                count++;
            }
        }
        return count;
    }

    private ParkingSpotType getRequiredSpotType(Vehicle vehicle) {
        switch (vehicle.getType()) {
            case CAR:
                return ParkingSpotType.CAR;
            case BIKE:
                return ParkingSpotType.BIKE;
            default:
                return ParkingSpotType.LARGE;
        }
    }

}
