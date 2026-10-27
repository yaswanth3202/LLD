package models;

import enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Vehicle
{
    private final String number;
    private final VehicleType type;
}
