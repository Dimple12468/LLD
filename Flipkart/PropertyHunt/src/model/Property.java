package model;

public abstract class Property {
    public int propertyId;
    public String title;
    public String location;
    public String price;
    public String listingType;
    public double size;
    public String sizeUnit;
    public String rooms;
    public boolean isAvailable;

    public Property(int propertyId, String title, String location, String price, String listingType, double size, String sizeUnit, String rooms) {
        this.propertyId = propertyId;
        this.title = title;
        this.location = location;
        this.price = price;
        this.listingType = listingType;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.rooms = rooms;
        this.isAvailable = true;
    }

    public abstract void display();
}