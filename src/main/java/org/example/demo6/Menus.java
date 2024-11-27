package org.example.demo6;

public class Menus {
    private String item;
    private String category;
    private double price;
    private boolean available;

    public Menus() {
        // Default constructor needed for JSON deserialization
    }

    public Menus(String item, double price, String category, boolean available) {
        this.item = item;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    // Getters and Setters for JSON
    public String getItemname() {
        return item;
    }

    public void setItemname(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return item + "~" + category + "~  :" + price + " - " + (available ? "available" : "unavailable");
    }
}
