package com.example.timmies.TimsConsumables.Bagels;

public class EverythingBagel extends Bagel {

    @Override
    public int getCalories() {
        return 297;
    }

    @Override
    public double getPrice() {
        return 1.99;
    }

    @Override
    public String toString() {
        return "Everything Bagel "+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}
