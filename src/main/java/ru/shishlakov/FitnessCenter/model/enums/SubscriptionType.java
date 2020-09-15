package ru.shishlakov.FitnessCenter.model.enums;

public enum SubscriptionType {
    UNLIMITED("Безлимитный", 10000),
    STANDART("Стандартный", 6000),
    SINGLE("Разовый", 1500);

    SubscriptionType(String title, double price) {
        this.title = title;
        this.price = price;
    }

    private String title;
    private double price;

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrentPrice(double discount) {
        return String.format("%.2f", price * (1 - discount));
    }
}
