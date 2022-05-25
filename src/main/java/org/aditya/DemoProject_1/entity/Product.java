package org.aditya.DemoProject_1.entity;

public class Product {
    public Product(String name, Double price) {
        this.name = name;
        this.price=price;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
}
