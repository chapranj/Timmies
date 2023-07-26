package com.example.timmies.TimsConsumables.Bagels;

public class PlainBagel extends Bagel {

    @Override
    public int getCalories() {
        return 290;
    }

    @Override
    public double getPrice() {
        return 1.99;
    }

    @Override
    public String toString() {
        return "Plain Bagel "+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}
