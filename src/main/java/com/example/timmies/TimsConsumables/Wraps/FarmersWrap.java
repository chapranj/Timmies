package com.example.timmies.TimsConsumables.Wraps;

public class FarmersWrap extends Wrap{
    @Override
    public int getCalories() {
        return 580;
    }

    @Override
    public double getPrice() {
        return 6.79;
    }

    @Override
    public String toString() {
        return "Farmers Egg Wrap "+ this.getCalories() +"Cals for "+ this.getPrice();
    }
}

