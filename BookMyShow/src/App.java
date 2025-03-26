import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entity.Booking;
import com.entity.Seat;
import com.entity.Show;
import com.service.BookMyShowService;

public class App {
    public static void main(String[] args) throws Exception {
        BookMyShowService service = new BookMyShowService();
        service.initializeData();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        System.out.println("Available seats for Avengers on 2025-03-26:");
        try {
            Date targetDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-03-26");
            Map<Show, List<Seat>> availableShows = 
                service.getAvailableSeatsForMovieOnDate("M1", targetDate);
            
            for (Map.Entry<Show, List<Seat>> entry : availableShows.entrySet()) {
                Show show = entry.getKey();
                List<Seat> seats = entry.getValue();
                System.out.println("Show ID: " + show.getId() + " at " + 
                    sdf.format(show.getStartTime()) + " - " + 
                    show.getTheater().getName() + " " + show.getScreen().getName());
                System.out.println("Available seats:");
                for (Seat seat : seats) {
                    System.out.println("  Seat: " + seat.getId() + " - $" + seat.getPrice());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nBooking tickets for user U1:");
        List<String> seatIds = Arrays.asList("A1", "A2");
        Booking booking = service.bookTickets("U1", "S1", seatIds);
        if (booking != null) {
            System.out.println("Booking created: " + booking.getId() + 
                " Amount: $" + booking.getPayment().getAmount());
            service.processPayment(booking.getId());
        }

        System.out.println("\nAvailable seats for Avengers on 2025-03-26 after booking:");
        try {
            Date targetDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-03-26");
            Map<Show, List<Seat>> availableShows = 
                service.getAvailableSeatsForMovieOnDate("M1", targetDate);
            
            for (Map.Entry<Show, List<Seat>> entry : availableShows.entrySet()) {
                Show show = entry.getKey();
                List<Seat> seats = entry.getValue();
                System.out.println("Show ID: " + show.getId() + " at " + 
                    sdf.format(show.getStartTime()) + " - " + 
                    show.getTheater().getName() + " " + show.getScreen().getName());
                System.out.println("Available seats:");
                for (Seat seat : seats) {
                    System.out.println("  Seat: " + seat.getId() + " - $" + seat.getPrice());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
