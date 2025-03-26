package com.entity.elevator;

import com.util.Direction;
import com.util.ElevatorState;

public class ElevatorCar {

    public int id;
    public int currentFloor;
    public Direction elevatorDirection;
    public ElevatorDisplay display;
    public InternalButtons internalButtons;
    public ElevatorState elevatorState;
    public ElevatorDoor elevatorDoor;

    public ElevatorCar() {
        display = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        elevatorState = ElevatorState.IDLE;
        currentFloor = 0;
        elevatorDirection = Direction.UP;
        elevatorDoor = new ElevatorDoor();
    }

    public void showDisplay() {
        display.showDisplay();
    }

    public void pressButton(int destination) {
        internalButtons.pressButton(destination, this);
    }

    public void setDisplay() {
        this.display.setDisplay(currentFloor, elevatorDirection);
    }

    public boolean moveElevator(Direction dir, int destinationFloor) {
        int startFloor = currentFloor;
        if (dir == Direction.UP) {
            while (currentFloor < destinationFloor) {
                currentFloor++;
                setDisplay();
                showDisplay();
                try {
                    Thread.sleep(500); // Simulate movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return currentFloor == destinationFloor;
        }

        if (dir == Direction.DOWN) {
            while (currentFloor > destinationFloor) {
                currentFloor--;
                setDisplay();
                showDisplay();
                try {
                    Thread.sleep(500); // Simulate movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return currentFloor == destinationFloor;
        }
        return false;
    }
}