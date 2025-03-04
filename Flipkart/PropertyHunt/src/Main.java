import java.util.Scanner;

import service.PropertyManager;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PropertyManager propertyManager = new PropertyManager();

        while (true) {
            System.out.println("Choose an option: Register, Login, ListProperty, Search, Shortlist, ViewShortlisted, ViewListed, MarkSold, Logout, Exit");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "register":
                    // Register logic (if needed)
                    break;
                case "login":
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    propertyManager.loginUser(username);
                    break;
                case "listproperty":
                    propertyManager.listProperty();
                    break;
                case "search":
                    System.out.print("Enter search details (location, priceRange, type, sizeRange, rooms, sortBy): ");
                    String searchParams = scanner.nextLine();
                    String[] searchDetails = searchParams.split(", ");
                    propertyManager.searchProperties(searchDetails[0], searchDetails[1], searchDetails[2], searchDetails[3], searchDetails[4], searchDetails[5]);
                    break;
                case "marksold":
                    System.out.print("Enter property ID to mark as sold: ");
                    int propertyId = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    propertyManager.markSold(propertyId);
                    break;
                case "logout":
                    propertyManager.logoutUser();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}