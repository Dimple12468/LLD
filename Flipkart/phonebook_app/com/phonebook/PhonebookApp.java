package com.phonebook;

import java.util.*;

public class PhonebookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Phonebook phonebook = new Phonebook();

        while (true) {
            System.out.println("\nPhonebook Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone (+CountryCode-Number): ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Email (Optional): ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Address (Optional): ");
                    String address = scanner.nextLine();
                    try {
                        phonebook.addContact(new Contact(name, phone, email, address));
                        System.out.println("Contact added successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter Phone Number of Contact to Edit: ");
                    String oldPhone = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter New Address: ");
                    String newAddress = scanner.nextLine();
                    try {
                        phonebook.editContact(oldPhone, newName, newPhone, newEmail, newAddress);
                        System.out.println("Contact updated successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter Name or Phone Number to Delete: ");
                    String identifier = scanner.nextLine();
                    phonebook.deleteContact(identifier);
                    System.out.println("Contact deleted successfully!");
                    break;
                case 4:
                    System.out.print("Enter Search Query: ");
                    String query = scanner.nextLine();
                    List<Contact> results = phonebook.searchContact(query);
                    for (Contact contact : results) {
                        System.out.println(contact);
                    }
                    break;
                case 5:
                    phonebook.displayContacts();
                    break;
                case 6:
                    System.out.println("Exiting Phonebook.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
