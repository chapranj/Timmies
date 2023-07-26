package com.example.timmies.TimsConsumables.Wraps;

public class ChickenWrap extends Wrap{

    @Override
    public int getCalories() {
        return 680;
    }

    @Override
    public double getPrice() {
        return 8.79;
    }

    @Override
    public String toString() {
        return "Crispy Chicken Wrap "+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}
