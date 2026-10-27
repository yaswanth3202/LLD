package models;

import enums.VehicleType;

public class Truck extends Vehicle
{
    public Truck(String number)
    {
        super(number,VehicleType.TRUCK);
    }
}
