package com.example.timmies.TimsConsumables.Bagels;

public class FourCheeseBagel extends Bagel {
    @Override
    public int getCalories() {
        return 323;
    }

    @Override
    public double getPrice() {
        return 2.79;
    }

    @Override
    public String toString() {
        return "Four Cheese Bagel "+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}
