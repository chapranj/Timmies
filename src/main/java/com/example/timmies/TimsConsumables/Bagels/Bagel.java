package com.example.timmies.TimsConsumables.Bagels;

import com.example.timmies.TimsConsumables.Consumables;

public abstract class Bagel implements Consumables {

    @Override
    public abstract int getCalories();

    @Override
    public abstract double getPrice() ;

}
