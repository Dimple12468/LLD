package com.entity;

import java.util.List;

import com.controller.ElevatorController;
import com.entity.elevator.ElevatorCreator;
import com.util.Direction;

public class ExternalButton {

    List<ElevatorController>  elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public void request(int floor, Direction direction){


        //for simplicity, i am following even odd,
        for(ElevatorController elevatorController : elevatorControllerList) {

           int elevatorID = elevatorController.elevatorCar.id;
           if (elevatorID%2==1 && floor%2==1){
               elevatorController.submitExternalRequest(floor,direction);
           } else if(elevatorID%2==0 && floor%2==0){
               elevatorController.submitExternalRequest(floor,direction);

           }
        }
    }

}
