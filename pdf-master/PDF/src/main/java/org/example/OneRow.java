package org.example;

public class OneRow
{
    String name;
    double hours;
    double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OneRow(String name, double hours, double price) {
        this.name = name;
        this.hours = hours;
        this.price = price;
    }

    public String toString() {
        return (name + " " + hours + "h " + price + "PLN/h\n");
    }
}
