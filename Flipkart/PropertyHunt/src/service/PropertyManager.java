package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.PropertyActions;
import model.User;


public class PropertyManager implements PropertyActions {
    private static int propertyCounter = 1;
    private List<ListedProperty> properties = new ArrayList<>();
    private Map<String, User> users = new HashMap<>();
    private User loggedInUser;

    public PropertyManager() {}

    @Override
    public void listProperty() {
        if (loggedInUser == null || !loggedInUser.isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the property details:");
        System.out.print("title: ");
        String title = scanner.nextLine();
        System.out.print("location: ");
        String location = scanner.nextLine();
        System.out.print("price: ");
        String price = scanner.nextLine();
        System.out.print("type: ");
        String type = scanner.nextLine();
        System.out.print("size: ");
        double size = scanner.nextDouble();
        System.out.print("sizeUnit (sqft, sqm, sqyard): ");
        String sizeUnit = scanner.next();
        System.out.print("rooms (1BHK, 2BHK, etc.): ");
        String rooms = scanner.next();

        ListedProperty property = new ListedProperty(propertyCounter++, title, location, price, type, size, sizeUnit, rooms);
        properties.add(property);
        System.out.println("Listing created successfully.");
    }

    @Override
    public void markSold(int propertyId) {
        if (loggedInUser == null || !loggedInUser.isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        for (ListedProperty property : properties) {
            if (property.propertyId == propertyId && property.isAvailable) {
                property.isAvailable = false;
                System.out.println("Property marked as sold.");
                return;
            }
        }
        System.out.println("Property not found or already sold.");
    }

    public void searchProperties(String location, String priceRange, String listingType, String sizeRange, String rooms, String sortBy) {
        List<ListedProperty> result = properties.stream()
            .filter(p -> p.location.equalsIgnoreCase(location))
            .filter(p -> p.listingType.equalsIgnoreCase(listingType))
            .filter(p -> matchesPrice(p.price, priceRange))
            .filter(p -> matchesSize(p.size, sizeRange))
            .filter(p -> matchesRooms(p.rooms, rooms))
            .sorted((p1, p2) -> sortBy.equals("price") ? compareByPrice(p1, p2) : compareBySize(p1, p2))
            .collect(Collectors.toList());

        System.out.println("Id Title Location Price Size Rooms AvailableFor");
        result.forEach(ListedProperty::display);
    }

    private boolean matchesPrice(String price, String priceRange) {
        // Price matching logic (parsing price ranges, etc.)
        return price.contains(priceRange);
    }

    private boolean matchesSize(double size, String sizeRange) {
        // Size range matching logic
        return sizeRange.equals("150sqft") || size >= 150;
    }

    private boolean matchesRooms(String rooms, String roomType) {
        return rooms.equals(roomType);
    }

    private int compareByPrice(ListedProperty p1, ListedProperty p2) {
        return Double.compare(Double.parseDouble(p1.price.replaceAll("[^\\d.]", "")), Double.parseDouble(p2.price.replaceAll("[^\\d.]", "")));
    }

    private int compareBySize(ListedProperty p1, ListedProperty p2) {
        return Double.compare(p1.size, p2.size);
    }

    public void loginUser(String username) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username));
        }
        loggedInUser = users.get(username);
        loggedInUser.login();
    }

    public void logoutUser() {
        if (loggedInUser != null) {
            loggedInUser.logout();
            loggedInUser = null;
        }
    }
}