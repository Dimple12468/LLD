package com.controller;

import java.util.PriorityQueue;
import com.entity.elevator.ElevatorCar;
import com.util.Direction;
import com.util.ElevatorState;

public class ElevatorController {
    public PriorityQueue<Integer> upMinPQ;
    public PriorityQueue<Integer> downMaxPQ;
    public ElevatorCar elevatorCar;

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>((a, b) -> b - a);
    }

    public void submitExternalRequest(int floor, Direction direction) {
        if (direction == Direction.DOWN) {
            downMaxPQ.offer(floor);
        } else {
            upMinPQ.offer(floor);
        }
    }

    public void submitInternalRequest(int floor) {
        if (floor > elevatorCar.currentFloor) {
            upMinPQ.offer(floor);
        } else if (floor < elevatorCar.currentFloor) {
            downMaxPQ.offer(floor);
        }
    }

    public void controlElevator() {
        while (!upMinPQ.isEmpty() || !downMaxPQ.isEmpty()) {
            if (elevatorCar.elevatorState == ElevatorState.IDLE) {
                if (!upMinPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.UP;
                    elevatorCar.elevatorState = ElevatorState.MOVING;
                } else if (!downMaxPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.DOWN;
                    elevatorCar.elevatorState = ElevatorState.MOVING;
                }
            }

            if (elevatorCar.elevatorDirection == Direction.UP && !upMinPQ.isEmpty()) {
                int nextFloor = upMinPQ.peek();
                if (elevatorCar.moveElevator(Direction.UP, nextFloor)) {
                    upMinPQ.poll(); // Request served
                    elevatorCar.elevatorDoor.openDoor();  // Correct: camelCase
                    elevatorCar.elevatorDoor.closeDoor(); // Correct: camelCase
                }
            } else if (elevatorCar.elevatorDirection == Direction.DOWN && !downMaxPQ.isEmpty()) {
                int nextFloor = downMaxPQ.peek();
                if (elevatorCar.moveElevator(Direction.DOWN, nextFloor)) {
                    downMaxPQ.poll(); // Request served
                    elevatorCar.elevatorDoor.openDoor();  // Correct: camelCase
                    elevatorCar.elevatorDoor.closeDoor(); // Correct: camelCase
                }
            }

            // Direction switching logic
            if (elevatorCar.elevatorDirection == Direction.UP && upMinPQ.isEmpty()) {
                if (!downMaxPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.DOWN;
                } else {
                    elevatorCar.elevatorState = ElevatorState.IDLE;
                }
            } else if (elevatorCar.elevatorDirection == Direction.DOWN && downMaxPQ.isEmpty()) {
                if (!upMinPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.UP;
                } else {
                    elevatorCar.elevatorState = ElevatorState.IDLE;
                }
            }

            try {
                Thread.sleep(1000); // Simulate time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elevatorCar.elevatorState = ElevatorState.IDLE;
    }
}