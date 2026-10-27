package factory;

import enums.VehicleType;
import models.Bike;
import models.Car;
import models.Truck;
import models.Vehicle;

public class VehicleFactory
{
    public static Vehicle produce(VehicleType type,String number)
    {
        return switch (type)
        {
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);
        };
    }

}