package com.example;


public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
       setName(name);
       setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (name.length() > 100) throw new IllegalArgumentException("Name cannot be longer than 100 characters");
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than 0");
        this.price = price;
    }
}
