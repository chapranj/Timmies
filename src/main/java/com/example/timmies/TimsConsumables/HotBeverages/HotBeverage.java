package com.example.timmies.TimsConsumables.HotBeverages;

import com.example.timmies.Consumables;

public abstract class HotBeverage implements Consumables {

    @Override
    public abstract int getCalories();
    @Override
    public abstract double getPrice();
}
