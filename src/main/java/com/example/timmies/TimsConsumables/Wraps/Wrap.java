package com.example.timmies.TimsConsumables.Wraps;

import com.example.timmies.Consumables;

public abstract class Wrap implements Consumables {

    @Override
    public abstract int getCalories();

    @Override
    public abstract double getPrice();
}
