import java.util.ArrayList;
import java.util.List;
import com.entity.Building;
import com.entity.Floor;
import com.entity.elevator.ElevatorCreator;
import com.controller.ElevatorController;
import com.util.Direction;

public class App {
    public static void main(String[] args) throws Exception {
        // Initialize floors
        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        floorList.add(floor1);
        floorList.add(floor2);
        floorList.add(floor3);
        floorList.add(floor4);
        floorList.add(floor5);

        // Create building
        Building building = new Building(floorList);

        // Get elevator controllers
        List<ElevatorController> controllers = ElevatorCreator.elevatorControllerList;

        // Simulate external requests
        floor1.pressButton(Direction.UP);  // Floor 1 requests elevator going up
        floor4.pressButton(Direction.DOWN); // Floor 4 requests elevator going down

        // Simulate internal requests
        controllers.get(0).elevatorCar.pressButton(3); // Elevator 1 internal request to floor 3
        controllers.get(1).elevatorCar.pressButton(5); // Elevator 2 internal request to floor 5

        // Start elevator controllers in separate threads
        Thread controllerThread1 = new Thread(() -> controllers.get(0).controlElevator());
        Thread controllerThread2 = new Thread(() -> controllers.get(1).controlElevator());

        controllerThread1.start();
        controllerThread2.start();

        // Keep the main thread alive to observe output
        controllerThread1.join();
        controllerThread2.join();
    }
}