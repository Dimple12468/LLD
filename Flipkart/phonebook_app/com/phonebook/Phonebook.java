package com.phonebook;

import java.util.*;

public class Phonebook extends AbstractPhonebook {
    @Override
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists!");
        }
        contacts.put(contact.getPhoneNumber(), contact);
    }

    @Override
    public void editContact(String phoneNumber, String newName, String newPhoneNumber, String newEmail, String newAddress) {
        if (!contacts.containsKey(phoneNumber)) {
            throw new NoSuchElementException("Contact not found!");
        }
        Contact contact = contacts.get(phoneNumber);
        contacts.remove(phoneNumber);
        contact = new Contact(newName, newPhoneNumber, newEmail, newAddress);
        contacts.put(newPhoneNumber, contact);
    }

    @Override
    public void deleteContact(String identifier) {
        Iterator<Map.Entry<String, Contact>> iterator = contacts.entrySet().iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next().getValue();
            if (contact.getName().equalsIgnoreCase(identifier) || contact.getPhoneNumber().equals(identifier)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public List<Contact> searchContact(String query) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts.values()) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) || contact.getPhoneNumber().contains(query)) {
                results.add(contact);
            }
        }
        return results;
    }

    @Override
    public List<Contact> filterContactsByPrefix(char prefix) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts.values()) {
            if (Character.toLowerCase(contact.getName().charAt(0)) == Character.toLowerCase(prefix)) {
                results.add(contact);
            }
        }
        return results;
    }
}
