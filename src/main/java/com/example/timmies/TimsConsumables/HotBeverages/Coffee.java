package com.example.timmies.TimsConsumables.HotBeverages;

public class Coffee extends HotBeverage{

    @Override
    public int getCalories() {
        return 4;
    }

    @Override
    public double getPrice() {
        return 1.83;
    }

    @Override
    public String toString() {
        return "Coffee"+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}
