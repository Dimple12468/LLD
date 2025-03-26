package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.entity.Booking;
import com.entity.Movie;
import com.entity.Payment;
import com.entity.Screen;
import com.entity.Seat;
import com.entity.Show;
import com.entity.Theater;
import com.entity.User;
import com.util.PaymentStatus;
import com.util.SeatStatus;
import com.util.SeatType;

public class BookMyShowService {

    private Map<String, Movie> movies;
    private Map<String, Theater> theaters;
    private Map<String, Show> shows;
    private Map<String, Booking> bookings;
    private Map<String, User> users;
    
    public BookMyShowService() {
        this.movies = new HashMap<>();
        this.theaters = new HashMap<>();
        this.shows = new HashMap<>();
        this.bookings = new HashMap<>();
        this.users = new HashMap<>();
    }

    public void initializeData() {
        User u1 = new User("U1", "Dimple", "dimple@abc.com", "1234567890");
        users.put(u1.getId(), u1);

        Movie m1 = new Movie("M1", "AlteryX", "2h 30m", "English");
        movies.put(m1.getId(), m1);

        Theater t1 = new Theater("T1", "LLD", "Banglore");
        Screen screen1 = new Screen("SCR1", "Screen 1", 50);
        Screen screen2 = new Screen("SCR2", "Screen 2", 40);
        t1.addScreen(screen1);
        t1.addScreen(screen2);
        theaters.put(t1.getId(), t1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Show s1 = new Show("S1", m1, t1, screen1, sdf.parse("2025-03-26 14:00"));
            initializeSeats(s1);
            shows.put(s1.getId(), s1);

            Show s2 = new Show("S2", m1, t1, screen2, sdf.parse("2025-03-26 17:00"));
            initializeSeats(s2);
            shows.put(s2.getId(), s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeSeats(Show show) {
        for (int i = 1; i <= 5; i++) {
            show.getSeats().put("A" + i, new Seat("A" + i, SeatType.REGULAR, 10.0));
            show.getSeats().put("B" + i, new Seat("B" + i, SeatType.PREMIUM, 15.0));
        }
    }

    public List<Show> getShowsForMovie(String movieId) {
        List<Show> result = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getMovie().getId().equals(movieId)) {
                result.add(show);
            }
        }
        return result;
    }

    public List<Seat> getAvailableSeats(String showId) {
        Show show = shows.get(showId);
        if (show == null) return new ArrayList<>();
        
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : show.getSeats().values()) {
            if (seat.getStatus() == SeatStatus.AVAILABLE) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public Map<Show, List<Seat>> getAvailableSeatsForMovieOnDate(String movieId, Date date) {
        Map<Show, List<Seat>> availableShows = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String targetDateStr = sdf.format(date);
        for (Show show : shows.values()) {
            if (show.getMovie().getId().equals(movieId)) {
                String showDateStr = sdf.format(show.getStartTime());
                if (showDateStr.equals(targetDateStr)) {
                    List<Seat> availableSeats = new ArrayList<>();
                    for (Seat seat : show.getSeats().values()) {
                        if (seat.getStatus() == SeatStatus.AVAILABLE) {
                            availableSeats.add(seat);
                        }
                    }
                    if (!availableSeats.isEmpty()) {
                        availableShows.put(show, availableSeats);
                    }
                }
            }
        }
        return availableShows;
    }

    public Booking bookTickets(String userId, String showId, List<String> seatIds) {
        User user = users.get(userId);
        Show show = shows.get(showId);
        
        if (user == null || show == null) {
            System.out.println(user == null ? "User not found" : "Show not found");
            return null;
        }

        List<Seat> seatsToBook = new ArrayList<>();
        for (String seatId : seatIds) {
            Seat seat = show.getSeats().get(seatId);
            if (seat == null || seat.getStatus() != SeatStatus.AVAILABLE) {
                System.out.println("Seat " + seatId + " is not available");
                for (Seat bookedSeat : seatsToBook) {
                    bookedSeat.setStatus(SeatStatus.AVAILABLE);
                }
                return null;
            }
            seat.setStatus(SeatStatus.BOOKED);
            seatsToBook.add(seat);
        }

        double totalAmount = seatsToBook.stream().mapToDouble(Seat::getPrice).sum();
        String paymentId = "P" + UUID.randomUUID().toString().substring(0, 8);
        Payment payment = new Payment(paymentId, totalAmount);
        
        String bookingId = "B" + UUID.randomUUID().toString().substring(0, 8);
        Booking booking = new Booking(bookingId, user, show, seatsToBook, payment);
        bookings.put(bookingId, booking);
        
        return booking;
    }

    public boolean processPayment(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            System.out.println("Booking not found");
            return false;
        }

        Payment payment = booking.getPayment();
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(UUID.randomUUID().toString().substring(0, 6));
        
        System.out.println("Payment successful for booking " + bookingId + 
            " by " + booking.getUser().getName());
        return true;
    }
}