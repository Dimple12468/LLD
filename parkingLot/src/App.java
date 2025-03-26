import com.entity.Vehicle.Bike;
import com.entity.Vehicle.Car;
import com.entity.Vehicle.Vehicle;
import com.service.ParkingLotService;
import com.util.ParkingSpotType;

public class App {
    public static void main(String[] args) throws Exception {
        ParkingLotService parkingService = new ParkingLotService();

        Vehicle car1 = new Car("ABC123");
        Vehicle car2 = new Car("XYZ789");
        Vehicle bike1 = new Bike("BIKE001");

        System.out.println("Parking vehicles:");
        parkingService.parkVehicle(car1);
        parkingService.parkVehicle(car2);
        parkingService.parkVehicle(bike1);

        System.out.println("\nAvailable spots:");
        System.out.println("Car spots: " + parkingService.getAvailableSpotsCount(ParkingSpotType.CAR));
        System.out.println("Bike spots: " + parkingService.getAvailableSpotsCount(ParkingSpotType.BIKE));
        System.out.println("Handicapped spots: " + parkingService.getAvailableSpotsCount(ParkingSpotType.HANDICAPPED));

        System.out.println();
        System.out.println("Removing vehicle:");
        parkingService.removeVehicle("ABC123");

        System.out.println("\nAvailable spots after removal:");
        System.out.println("Car spots: " + parkingService.getAvailableSpotsCount(ParkingSpotType.CAR));
    }
}
