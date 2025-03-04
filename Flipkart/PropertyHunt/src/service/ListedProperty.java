package service;

import model.Property;

public class ListedProperty extends Property {

    public ListedProperty(int propertyId, String title, String location, String price, String listingType, double size, String sizeUnit, String rooms) {
        super(propertyId, title, location, price, listingType, size, sizeUnit, rooms);
    }

    @Override
    public void display() {
        System.out.printf("%d %s %s %s %s %s %s %s\n", propertyId, title, location, price, size + sizeUnit, rooms, listingType, (isAvailable ? "Available" : "Sold"));
    }
}
