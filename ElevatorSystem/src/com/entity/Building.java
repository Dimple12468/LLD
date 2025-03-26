package com.entity;

import java.util.List;

public class Building {

    public List<Floor> floorList;

    public Building(List<Floor> floors){
        this.floorList = floors;
    }

    //addFloor
    public void addFloor(Floor floor){
        floorList.add(floor);
    }

    public void removeFloor(Floor floor){
        floorList.remove(floor);
    }

    public List<Floor> getAllFloors(){
        return floorList;
    }

}
