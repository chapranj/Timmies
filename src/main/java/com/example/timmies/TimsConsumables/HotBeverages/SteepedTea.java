package com.example.timmies.TimsConsumables.HotBeverages;

public class SteepedTea extends HotBeverage{

    @Override
    public int getCalories() {
        return 2;
    }

    @Override
    public double getPrice() {
        return 1.83;
    }

    @Override
    public String toString() {
        return "Medium Steeped Tea double double "+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}
