package com.company.gherkin_file_appender.pojo.available_values_tab;

public class PriceInformation {

    private double price;

    public PriceInformation(double price) {
        this.price = price;
    }

    public PriceInformation() {}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
