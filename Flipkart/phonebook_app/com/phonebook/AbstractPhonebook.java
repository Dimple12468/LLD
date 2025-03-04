package com.phonebook;

import java.util.*;

public abstract class AbstractPhonebook {
    protected Map<String, Contact> contacts = new HashMap<>();

    public abstract void addContact(Contact contact);
    public abstract void editContact(String phoneNumber, String newName, String newPhoneNumber, String newEmail, String newAddress);
    public abstract void deleteContact(String identifier);
    public abstract List<Contact> searchContact(String query);
    public abstract List<Contact> filterContactsByPrefix(char prefix);

    public void displayContacts() {
        List<Contact> sortedContacts = new ArrayList<>(contacts.values());
        sortedContacts.sort(Comparator.comparing(Contact::getName));
        for (Contact contact : sortedContacts) {
            System.out.println(contact);
        }
    }
}
